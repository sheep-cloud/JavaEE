package cn.colg.shiro.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

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
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager(customRealm);

        // 加密
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        // 加密一次
        matcher.setHashIterations(1);
        customRealm.setCredentialsMatcher(matcher);

        // 2. 主体提交认证
        SecurityUtils.setSecurityManager(defaultSecurityManager);
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