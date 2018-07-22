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
import org.springframework.beans.factory.annotation.Autowired;

import cn.colg.service.UsersService;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义 realm
 *
 * @author colg
 */
@Slf4j
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UsersService usersService;

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
        Set<String> roles = usersService.selectRolesByUserName(username);

        log.info("从数据库获取权限数据");
        // 3. 根据角色名获取权限列表
        Set<String> permissions = usersService.selectPermissionsRoles(roles);

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
        String password = usersService.findPasswordBuUserName(username);
        if (StrUtil.isEmpty(password)) {
            return null;
        }

        // 3. 认证
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, getName());

        // 4. 加盐
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("colg"));
        return authenticationInfo;
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
