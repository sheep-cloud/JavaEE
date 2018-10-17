package cn.colg.shiro.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

/**
 * jdbc realm 测试
 *
 * @author colg
 */
@Slf4j
public class JdbcRealmTest {

    /** 数据源 */
    private static DruidDataSource dataSource = new DruidDataSource();

    static {
        dataSource.setUrl("jdbc:mysql://localhost:3306/shiro?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
    }

    @Test
    public void testAuthentication() throws Exception {
        // 注入数据源
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
        // 允许在授权期间查找权限，默认为false
        jdbcRealm.setPermissionsLookupEnabled(true);

        // 认证查询
        String authenticationQuery = "SELECT u.password FROM users u WHERE u.username = ?";
        jdbcRealm.setAuthenticationQuery(authenticationQuery);

        // 用户角色查询
        String userRolesQuery = "SELECT ur.role_name FROM user_roles ur WHERE ur.username = ?";
        jdbcRealm.setUserRolesQuery(userRolesQuery);

        // 角色权限查询
        String permissionsQuery = "SELECT rp.permission FROM roles_permissions rp WHERE rp.role_name = ?";
        jdbcRealm.setPermissionsQuery(permissionsQuery);

        // 1. 构建 SecurityManager 环境
        DefaultSecurityManager securityManager = new DefaultSecurityManager(jdbcRealm);

        // 2. 主体提交认证
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        log.info("subject: {}", JSON.toJSONString(subject));

        UsernamePasswordToken token = new UsernamePasswordToken("jack", "697be5a0ad36807e6ed6637ee3fc60b3");
        log.info("token: {}", JSON.toJSONString(token));

        // 登录
        subject.login(token);

        // 校验帐号密码
        log.info("是否登录: {}", subject.isAuthenticated());

        // 校验权限
        boolean[] permitted = subject.isPermitted("user:insert", "user:delete", "user:update", "user:select");
        log.info("权限列表: {}", permitted);

        // 注销
        subject.logout();
        log.info("是否登录: {}", subject.isAuthenticated());
    }

}
