package cn.colg.controller;

import static cn.colg.util.ResultVoUtil.fail;
import static cn.colg.util.ResultVoUtil.success;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.colg.cache.JedisClient;
import cn.colg.core.BaseController;
import cn.colg.vo.ResultVo;

/**
 * 用户管理
 *
 * @author colg
 */
@RestController
public class UserController extends BaseController {

    @Autowired
    private JedisClient jedisClient;

    /**
     * 登录
     *
     * @param users
     * @return
     * @author colg
     */
    @PostMapping("/subLogin")
    public ResultVo subLogin(String username, String password, boolean rememberMe) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 登录
        token.setRememberMe(rememberMe);
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            return fail(ResultVo.CHECK_FAIL, "帐号不存在");
        } catch (IncorrectCredentialsException e) {
            return fail(ResultVo.CHECK_FAIL, "密码错误");
        }
        String admin = "admin";
        if (subject.hasRole(admin)) {
            return success("登录成功，有admin权限");
        }
        return success("登录成功");
    }

    /**
     * 注销
     *
     * @return
     * @author colg
     */
    @PostMapping("/subLogout")
    public ResultVo subLogout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return success("注销成功");
    }

    /// ----------------------------------------------------------------------------------------------------

    // shiro 注解

    /**
     * 必须具备 admin 角色才可以访问
     *
     * @return
     * @author colg
     */
    @RequiresRoles("admin")
    @GetMapping("/testRole")
    public ResultVo testRole() {
        return success();
    }

    /**
     * 必须具备 admin1 角色才可以访问
     *
     * @return
     * @author colg
     */
    @RequiresRoles("admin1")
    @GetMapping("/testRole1")
    public ResultVo testRole1() {
        return success();
    }

    /**
     * 必须具备 {"user:delete", "user:insert"} 权限才可以访问
     *
     * @return
     * @author colg
     */
    @RequiresPermissions({"user:delete", "user:insert"})
    @GetMapping("/testPermission")
    public ResultVo testPermission() {
        return success();
    }

    /// ----------------------------------------------------------------------------------------------------

    // 内置过滤器

    @GetMapping("/testAnon")
    public ResultVo testAnon() {
        return success();
    }

    @GetMapping("/testRoles")
    public ResultVo testRoles() {
        return success();
    }

    @GetMapping("/testRoles2")
    public ResultVo testRoles2() {
        return success();
    }

    @GetMapping("/testPerms")
    public ResultVo testPerms() {
        return success();
    }

    @GetMapping("/testPerms2")
    public ResultVo testPerms2() {
        return success();
    }

    @GetMapping("/jedis")
    public ResultVo jedis() {
        String key = "demo";
        jedisClient.set(key, "001");
        jedisClient.expire(key, 600);
        Long ttl = jedisClient.ttl(key);
        return success(ttl);
    }
}
