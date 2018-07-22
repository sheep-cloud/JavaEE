package cn.colg.shiro.realm;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Dict;
import lombok.extern.slf4j.Slf4j;

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
        if (password == null) {
            return null;
        }
        log.info("password: {}", password);
        
        // 3. 认证
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, getName());
        
        // 加盐
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("colg"));
        return authenticationInfo;
    }
    
    /// ----------------------------------------------------------------------------------------------------

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

    /// ----------------------------------------------------------------------------------------------------

    @Test
    public void testMd5() throws Exception {
        Md5Hash md5Hash = new Md5Hash("123456");
        log.info("md5Hash: {}", md5Hash);
        
        Md5Hash md5HashSalt = new Md5Hash("123456", "colg");
        log.info("md5HashSalt: {}", md5HashSalt);
    }
}
