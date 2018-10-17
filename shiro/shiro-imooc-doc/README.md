# Shiro 安全框架简介

## 1. Shiro 简介

### 1.1. 什么是Shiro？

- Apache的强大灵活的开源安全框架
- 认证、授权、企业会话管理、安全加密

## 2. Shiro 整体架构

### 2.1. Shiro与Spring Security比较

- Apache Shiro
    - 简单、灵活
    - 可脱离Spring
    - 粒度较粗
- Spring Security
    - 复杂、笨重
    - 不可脱离Spring
    - 粒度更细

### 2.2 Shiro 整体架构

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fti3l730jtj30on0it4b1.jpg)

- **Subject**：主体，可以看到主体可以是任何可以与应用交互的“用户”；
- **SecurityManager**：安全管理器；相当于SpringMVC中的DispatcherServlet或者Struts2中的FilterDispatcher；是Shiro的心脏；所有具体的交互都通过SecurityManager进行控制；它管理着所有Subject、且负责进行认证和授权、及会话、缓存的管理。
- **Authenticator**：认证器，负责主体认证的，这是一个扩展点，如果用户觉得Shiro默认的不好，可以自定义实现；其需要认证策略（Authentication Strategy），即什么情况下算用户认证通过了；
- **Authrizer**：授权器，或者访问控制器，用来决定主体是否有权限进行相应的操作；即控制着用户能访问应用中的哪些功能；
- **Realm**：可以有1个或多个Realm，可以认为是安全实体数据源，即用于获取安全实体的；可以是JDBC实现，也可以是LDAP实现，或者内存实现等等；由用户提供；注意：Shiro不知道你的用户/权限存储在哪及以何种格式存储；所以我们一般在应用中都需要实现自己的Realm；
- **SessionManager**：如果写过Servlet就应该知道Session的概念，Session呢需要有人去管理它的生命周期，这个组件就是SessionManager；而Shiro并不仅仅可以用在Web环境，也可以用在如普通的JavaSE环境、EJB等环境；所有呢，Shiro就抽象了一个自己的Session来管理主体与应用之间交互的数据；这样的话，比如我们在Web环境用，刚开始是一台Web服务器；接着又上了台EJB服务器；这时想把两台服务器的会话数据放到一个地方，这个时候就可以实现自己的分布式会话（如把数据放到Memcached服务器）；
- **SessionDAO**：DAO大家都用过，数据访问对象，用于会话的CRUD，比如我们想把Session保存到数据库，那么可以实现自己的SessionDAO，通过如JDBC写到数据库；比如想把Session放到Memcached中，可以实现自己的Memcached SessionDAO；另外SessionDAO中可以使用Cache进行缓存，以提高性能；
- **CacheManager**：缓存控制器，来管理如用户、角色、权限等的缓存的；因为这些数据基本上很少去改变，放到缓存中后可以提高访问的性能
- **Cryptography**：密码模块，Shiro提高了一些常见的加密组件用于如密码加密/解密的。

## 3. Shiro 认证、授权、Realm

### 3.1. ini 配置文件

```ini
# 提供了对用户/密码与角色之间的配置，用户名=密码, 角色1, 角色2
[users]
jack=123456, admin

# 提供了角色与权限之间的配置，角色=权限1, 权限2
[roles]
admin=user:insert, user:delete, user:update
```

### 3.2. Shiro 认证

```java
/**
 * imi realm 测试
 *
 * @author colg
 */
@Slf4j
public class IniRealmTest {

    private IniRealm iniRealm = new IniRealm("classpath:user.ini");
    
    @Test
    public void testAuthentication() throws Exception {
        // 1. 创建 SecurityManager
        DefaultSecurityManager securityManager = new DefaultSecurityManager(iniRealm);
        
        // 2. 主体提交认证
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        log.info("subject: {}", JSON.toJSONString(subject));
        
        UsernamePasswordToken token = new UsernamePasswordToken("jack", "123456");
        log.info("token: {}", JSON.toJSONString(token));
        
        // 登录
        subject.login(token);
        
        // 校验账号密码
        log.info("是否登录: {}", subject.isAuthenticated());

        // 注销
        subject.logout();
        log.info("是否登录: {}", subject.isAuthenticated());
    }

}
```

### 3.2 Shiro 授权

```java
/**
 * imi realm 测试
 *
 * @author colg
 */
@Slf4j
public class IniRealmTest {

    private IniRealm iniRealm = new IniRealm("classpath:user.ini");
    
    @Test
    public void testAuthentication() throws Exception {
        // 1. 创建 SecurityManager
        DefaultSecurityManager securityManager = new DefaultSecurityManager(iniRealm);
        
        // 2. 主体提交认证
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        log.info("subject: {}", JSON.toJSONString(subject));
        
        UsernamePasswordToken token = new UsernamePasswordToken("jack", "123456");
        log.info("token: {}", JSON.toJSONString(token));
        
        // 登录
        subject.login(token);
        
        // 校验账号密码
        log.info("是否登录: {}", subject.isAuthenticated());
        
        // 校验角色
        subject.checkRoles("admin");
        
        // 校验权限
        boolean[] permitted = subject.isPermitted("user:insert", "user:delete", "user:update", "user:select");
        log.info("权限列表: {}", permitted);

        // 注销
        subject.logout();
        log.info("是否登录: {}", subject.isAuthenticated());
    }

}
```

### 3.3 自定义 Realm

```java
/**
 * 自定义 realm
 *
 * @author colg
 */
@Slf4j
public class CustomRealm extends AuthorizingRealm {
    
    /** 模拟用户数据 */
    private Dict userDict = Dict.create().set("jack", "697be5a0ad36807e6ed6637ee3fc60b3")
                                         .set("rose", "123456");

    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 1. 从认证信息中获取用户名
        String username = (String)principals.getPrimaryPrincipal();
        
        // 2. 根据用户名获取角色列表
        Set<String> roles = this.getRolesByUserName(username);
        
        // 3. 根据用户名获取权限列表
        Set<String> permissions = this.getPermissionsByUserName(username);
        
        // 4. 授权
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }


    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 1. 从主体传过来的认证信息中获得用户名
        String username = (String)token.getPrincipal();
        log.info("username: {}", username);
        
        // 2. 根据用户名到数据库获取凭证
        String password = this.getPasswordByUserName(username);
        if (StrUtil.isEmpty(password)) {
            return null;
        }
        log.info("password: {}", password);
        
        // 3. 认证
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, getName());
        
        // 加盐
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("colg"));
        return authenticationInfo;
    }
    
    /// ---------------------------------------------------------------------------------

    /**
     * 根据用户名查询权限列表
     *
     * @param username
     * @return
     * @author colg
     */
    private Set<String> getPermissionsByUserName(String username) {
        return CollUtil.newHashSet("user:select", "user:insert", "user:update");
    }
    
    /**
     * 根据用户名查询角色列表
     *
     * @param username
     * @return
     * @author colg
     */
    private Set<String> getRolesByUserName(String username) {
        return CollUtil.newHashSet("admin", "manager");
    }

    /**
     * 根据用户名查询用户密码
     *
     * @param username
     * @return
     * @author colg
     */
    private String getPasswordByUserName(String username) {
        return userDict.getStr(username);
    }

    /// ---------------------------------------------------------------------------------

    @Test
    public void testMd5() throws Exception {
        Md5Hash md5Hash = new Md5Hash("123456");
        log.info("md5Hash: {}", md5Hash);
        
        Md5Hash md5HashSalt = new Md5Hash("123456", "colg");
        log.info("md5HashSalt: {}", md5HashSalt);
    }
    
}
```

#### 3.4 Shiro 加密

```java
/**
 * 自定义 realm 测试
 *
 * @author colg
 */
@Slf4j
public class CustomRealmTest {

    @Test
    public void testCustomRealm() throws Exception {
        // 1. 构建 SecurityManager 环境
        CustomRealm customRealm = new CustomRealm();
        DefaultSecurityManager securityManager = new DefaultSecurityManager(customRealm);

        // 加密
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        // 加密一次
        matcher.setHashIterations(1);
        customRealm.setCredentialsMatcher(matcher);

        // 2. 主体提交认证
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        log.info("subject: {}", JSON.toJSONString(subject));

        UsernamePasswordToken token = new UsernamePasswordToken("jack", "123456");
        log.info("token: {}", JSON.toJSONString(token));

        // 登录
        subject.login(token);

        // 校验帐号密码
        log.info("是否登录: {}", subject.isAuthenticated());

        // 校验角色
        subject.checkRoles("admin");

        // 校验权限列表
        boolean[] permitted = subject.isPermitted("user:insert", "user:delete", "user:update", "user:select");
        log.info("权限校验: {}", permitted);

        // 注销
        subject.logout();
        log.info("是否登录: {}", subject.isAuthenticated());
    }
    
}
```

## 4. Shiro 集成 Spring

### 4.1 配置

- pom.xml

```xml
        <!-- shiro -->
        <dependency>
            <groupId>org.apache.shiro</groupId>
            <artifactId>shiro-spring</artifactId>
            <version>${shiro.version}</version>
        </dependency>
        <dependency>
            <groupId>org.apache.shiro</groupId>
            <artifactId>shiro-web</artifactId>
            <version>${shiro.version}</version>
        </dependency>
```

- spring-shiro.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    <bean id="shiroFilter" class="org.apache.shiro.spring.web.ShiroFilterFactoryBean">
        <property name="securityManager" ref="securityManager" />
        <property name="loginUrl" value="login.html" />
        <property name="unauthorizedUrl" value="403.html" />
        <!-- 过滤器链 -->
        <property name="filterChainDefinitions">
            <value>
                /login.html = anon
                /subLogin = anon
                /* = authc
            </value>
        </property>
    </bean>
    
    <!-- 构建 SecurityManager 环境 -->
    <bean id="securityManager" class="org.apache.shiro.web.mgt.DefaultWebSecurityManager">
        <property name="realm" ref="realm" />
    </bean>

    <!-- 自定义 realm -->
    <bean id="realm" class="cn.colg.shiro.realm.CustomRealm">
        <property name="credentialsMatcher" ref="credentialsMatcher" />
    </bean>
    
    <!-- 加密管理器 -->
    <bean id="credentialsMatcher" class="org.apache.shiro.authc.credential.HashedCredentialsMatcher">
        <!-- 加密算法 -->
        <property name="hashAlgorithmName" value="md5" />
        <!-- 加密次数 -->
        <property name="hashIterations" value="1" />
    </bean>

    <!-- 使用注解 -->
    <bean id="lifecycleBeanPostProcessor" class="org.apache.shiro.spring.LifecycleBeanPostProcessor" />
    <bean id="authorizationAttributeSourceAdvisor" class="org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor">
        <property name="securityManager" ref="securityManager" />
    </bean>
</beans>

```

```java
    /**
     * 登录
     *
     * @param users
     * @return
     * @author colg
     */
    @PostMapping("/subLogin")
    public ResultVo subLogin(Users users) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(users.getUsername(), users.getPassword());
        // 登录
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            return fail(ResultVo.CHECK_FAIL, e.getMessage());
        }
        if (subject.hasRole("admin")) {
            return success("登录成功，有admin权限");
        }
        return success("登录成功");
    }
```

### 4.2 Shiro 过滤器

- Shiro 内置过滤器
  - 认证：anon, authBasic, authc, user, logout
  - 授权：perms, roles, ssl, port
- 自定义过滤器

```java
/**
 * 自定义过滤器 - 需要导入 javax.servlet-api
 *
 * @author colg
 */
public class RolesOrFilter extends AuthorizationFilter {

    /**
     * 自定义过滤器，有其中一个角色就返回true
     *
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Subject subject = getSubject(request, response);
        String[] roles = (String[])mappedValue;
        // 如果 角色为空，返回true
        if (ArrayUtil.isEmpty(roles)) {
            return true;
        }

        for (String role : roles) {
            if (subject.hasRole(role)) {
                return true;
            }
        }
        return false;
    }
}
```

```xml
    <bean id="shiroFilter" class="org.apache.shiro.spring.web.ShiroFilterFactoryBean">
        <property name="securityManager" ref="securityManager" />
        <property name="loginUrl" value="login.html" />
        <property name="unauthorizedUrl" value="403.html" />
        <!-- 过滤器链 -->
        <property name="filterChainDefinitions">
            <value>
                /login.html = anon
                /subLogin = anon
                <!-- 
                /testRoles = roles["admin"]
                /testRoles2 = roles["admin", "manager"]
                /testPerms = perms["user:delete"]
                /testPerms = perms["user:delete", "user:update"]
                 -->
                /testRoles = roles["admin", "manager"]
                /testRoles2 = rolesOr["admin", "manager"]
                /* = authc
            </value>
        </property>
        <!-- 注入自定义过滤器 -->
        <property name="filters">
            <util:map>
                <entry key="rolesOr" value-ref="rolesOrFilter"></entry>
            </util:map>
        </property>
    </bean>
    
    <bean id="rolesOrFilter" class="cn.colg.filter.RolesOrFilter" />
```

### 4. Shiro Session 管理 和 Cache 管理

#### 4.1 Shiro Session 管理

##### 4.1.1 SessionManager、SessionDAO

```java
/**
 * 自定义 SessionManager
 *
 * @author colg
 */
public class CustomSessionManager extends DefaultWebSessionManager {

    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        Serializable sessionId = getSessionId(sessionKey);
        // 把 session 放到request里
        ServletRequest request = null;
        if (sessionKey instanceof WebSessionKey) {
            request = ((WebSessionKey)sessionKey).getServletRequest();
        }
        // 如果request里存在，则直接从request里获取
        if (request != null && sessionId != null) {
            Session session = (Session)request.getAttribute(sessionId.toString());
            if (session != null) {
                return session;
            }
        }

        // 从 redis 中取
        Session session = super.retrieveSession(sessionKey);
        if (session != null && sessionId != null) {
            request.setAttribute(sessionId.toString(), session);
        }
        return session;
    }
}
```

```java
/**
 * 使用 redis 缓存session
 *
 * @author colg
 */
@Slf4j
public class RedisSessionDao extends AbstractSessionDAO {

    @Autowired
    private JedisClient jedisClient;

    private static final String SHIRO_SESSION_PREFIX = "imocck-session";

    // 使用 byte[] 保存
    private byte[] getKey(String key) {
        return (SHIRO_SESSION_PREFIX + key).getBytes();
    }

    private void saveSession(Session session) {
        /// 使用 byte[] 保存
        if (session != null && session.getId() != null) {
            byte[] key = getKey(session.getId().toString());
            // 序列化
            byte[] value = SerializationUtils.serialize(session);
            jedisClient.set(key, value);
            jedisClient.expire(key, 600);
        }
    }

    /**
     * 更新session
     *
     * @param session
     * @throws UnknownSessionException
     */
    @Override
    public void update(Session session) throws UnknownSessionException {
        saveSession(session);
    }

    /**
     * 删除session
     *
     * @param session
     */
    @Override
    public void delete(Session session) {
        if (session == null || session.getId() == null) {
            return;
        }
        byte[] key = getKey(session.getId().toString());
        jedisClient.del(key);
    }

    /**
     * 获取有效的session
     *
     * @return
     */
    @Override
    public Collection<Session> getActiveSessions() {
        Set<byte[]> keys = jedisClient.keys(getKey("*"));
        Set<Session> sessions = new HashSet<>();
        if (CollUtil.isEmpty(keys)) {
            return sessions;
        }
        for (byte[] key : keys) {
            // 反序列化
            byte[] value = jedisClient.get(key);
            Session session = (Session)SerializationUtils.deserialize(value);
            sessions.add(session);
        }
        return sessions;
    }

    /**
     * 保存session
     *
     * @param session
     * @return
     */
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        // 把sessionId和session捆绑
        assignSessionId(session, sessionId);
        saveSession(session);
        return sessionId;
    }

    /**
     * 通过sessionId获取session
     *
     * @param sessionId
     * @return
     */
    @Override
    protected Session doReadSession(Serializable sessionId) {
        log.info("当前 sessionId: {}", sessionId);
        if (sessionId == null) {
            return null;
        }
        byte[] key = getKey(sessionId.toString());
        byte[] value = jedisClient.get(key);
        // 反序列化
        return (Session)SerializationUtils.deserialize(value);
    }
}
```

##### 4.1.2 Redis实现Session共享

```xml
    <!-- 使用自定义sessionManager -->
    <bean id="sessionManager" class="cn.colg.session.CustomSessionManager">
        <property name="sessionDAO" ref="redisSessionDao" />
    </bean>
    <bean id="redisSessionDao" class="cn.colg.session.RedisSessionDao" />
```

#### 4.2 Shiro 缓存管理

##### 4.2.1 CacheManager、Cache

```java
/**
 * redis 缓存管理
 *
 * @author colg
 * @param <K>
 * @param <V>
 */
@Getter
@Setter
public class RedisCacheManager<K, V> implements CacheManager{
    
    private RedisCache<K, V> redisCache;

    @SuppressWarnings({"unchecked", "hiding"})
    @Override
    public <K, V> Cache<K, V> getCache(String arg0) throws CacheException {
        return (Cache<K, V>)redisCache;
    }

}
```

```java
/**
 * redis 缓存
 *
 * @author colg
 */
@Slf4j
public class RedisCache<K, V> implements Cache<K, V> {

    @Autowired
    private JedisClient jedisClient;

    /** 缓存前缀 */
    private static final String CACHE_PREFIX = "imooc-cache:";

    /**
     * 获取带前缀的key
     *
     * @param k
     * @return
     * @author colg
     */
    private byte[] getKey(K k) {
        log.info("从 redis 获取权限数据");
        if (k instanceof String) {
            return (CACHE_PREFIX + k).getBytes();
        }
        return SerializationUtils.serialize(k);
    }

    /**
     * 清空缓存
     *
     * @throws CacheException
     */
    @Override
    public void clear() throws CacheException {}

    /**
     * 
     * 从缓存中获得对象，当对象不在缓存中或已经过期返回<code>null</code>
     * 
     * @param k 键
     * @return 键对应的对象
     * @throws CacheException
     */
    @SuppressWarnings("unchecked")
    @Override
    public V get(K k) throws CacheException {
        byte[] value = jedisClient.get(getKey(k));
        if (value != null) {
            return (V)SerializationUtils.deserialize(value);
        }
        return null;
    }

    /**
     * 缓存的key的集合
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public Set<K> keys() {
        byte[] key = getKey((K)"*");
        return (Set<K>)jedisClient.keys(key);
    }

    /**
     * 
     * 将对象加入到缓存，使用默认失效时长
     * 
     * @param k 键
     * @param v 缓存的对象
     * @return 缓存的对象
     * @throws CacheException
     */
    @Override
    public V put(K k, V v) throws CacheException {
        byte[] key = getKey(k);
        byte[] value = SerializationUtils.serialize(v);
        jedisClient.set(key, value);
        jedisClient.expire(key, 600);
        return v;
    }

    /**
     * 
     * 从缓存中移除对象
     * 
     * @param k 键
     * @return 键对应的对象
     * @throws CacheException
     */
    @SuppressWarnings("unchecked")
    @Override
    public V remove(K k) throws CacheException {
        byte[] key = getKey(k);
        byte[] value = getKey(k);
        jedisClient.del(key);
        return (V)value;
    }

    /**
     * 缓存的对象数量
     *
     * @return 缓存的对象数量
     */
    @Override
    public int size() {
        return keys().size();
    }

    /**
     * 缓存的对象集合
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public Collection<V> values() {
        Set<K> keys = keys();
        Collection<V> values = new ArrayList<>();
        for (K k : keys) {
            byte[] value = jedisClient.get(getKey(k));
            values.add((V)value);
        }
        return values;
    }
}
```

##### 4.2.2 Redis实现CacheManager

```xml
    <!-- 使用自定义cacheManager -->
    <bean id="cacheManager" class="cn.colg.cache.RedisCacheManager" >
        <property name="redisCache" ref="redisCache" />
    </bean>
    <bean id="redisCache" class="cn.colg.cache.RedisCache" />
```

## 5. Shrio 自动登录

```xml
    <!-- 自动登录 -->    
    <bean id="rememberMeManager" class="org.apache.shiro.web.mgt.CookieRememberMeManager">
        <property name="cookie" ref="cookie" />
    </bean>
    <bean id="cookie" class="org.apache.shiro.web.servlet.SimpleCookie">
        <constructor-arg value="rememberMe" />
        <property name="maxAge" value="3600" />
    </bean>
```

```java
    @PostMapping("/subLogin")
    public ResultVo subLogin(String username, String password, boolean rememberMe) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 登录
        try {
            token.setRememberMe(rememberMe);
            subject.login(token);
        } catch (AuthenticationException e) {
            return fail(ResultVo.CHECK_FAIL, e.getMessage());
        }
        if (subject.hasRole("admin")) {
            return success("登录成功，有admin权限");
        }
        return success("登录成功");
    }
```

