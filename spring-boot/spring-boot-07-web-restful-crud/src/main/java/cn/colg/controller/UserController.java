package cn.colg.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import cn.hutool.core.util.StrUtil;

/**
 * UserController
 *
 * @author colg
 */
@Controller
public class UserController {

    /**
     * 登录
     *
     * @return
     * @author colg
     */
    @PostMapping("/user/login")
    public String login(String username, String password, Model model, HttpSession session) {
        if (StrUtil.isNotBlank(username) && "123456".equals(password)) {
            // 登录成功
            session.setAttribute("loginUser", username);
            
            // return "dashboard";
            // 防止表单重复提交，使用重定向到主页
            return "redirect:/main.html";
        } else {
            // 登录失败
            model.addAttribute("msg", "用户名或密码错误");
            return "login";
        }
    }
}
