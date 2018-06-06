package cn.colg.rbac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.colg.rbac.entity.User;
import cn.colg.rbac.service.UserService;

@Controller
public class LoginController {
    
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 执行登录
     *
     * @return
     */
    @PostMapping("/doLogin")
    public String doLogin(User user, Model model) {
        /*
         * colg [获取表单数据]
         *  1. 获取表单数据
         *      1). HttpServletRequest
         *      2). 在方法参数列表中增加表单对应的参数，名称相同
         *      3). 将表单数据封装为实体类对象
         *  2. 查询用户信息
         *  3. 判断用户信息是否存在
         */
        User dbUser = userService.findUser(user);
        if (dbUser != null) {
            // 登录成功，跳转到主页
            return "main";
        } else {
            // 登录失败，跳转回到登录页面，提示错误信息
            String errorMsg = "登录帐号或密码不能正确，请重新输入";
            model.addAttribute("errorMsg", errorMsg);
            return "redirect:login";
        }
    }
}
