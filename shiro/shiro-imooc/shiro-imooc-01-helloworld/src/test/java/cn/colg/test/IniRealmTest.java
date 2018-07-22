package cn.colg.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

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
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager(iniRealm);
        
        // 2. 主体提交认证
        SecurityUtils.setSecurityManager(defaultSecurityManager);
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
