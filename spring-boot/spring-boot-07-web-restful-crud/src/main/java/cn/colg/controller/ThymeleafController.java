package cn.colg.controller;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ThymeleafController
 *
 * @author colg
 */
@Controller
public class ThymeleafController {

    @GetMapping("/success")
    public String success() {
        // classpath:/templates/success.html
        return "success";
    }

    /**
     * 查出一些数据，在页面展示
     *
     * @param model
     * @return
     * @author colg
     */
    @GetMapping("/success2")
    public String success2(Model model) {
        model.addAttribute("hello", "<h1>thymeleaf</h1>");
        model.addAttribute("users", Arrays.asList("Jack", "Rose", "汤姆"));
        // classpath:/templates/success2.html
        return "success2";
    }
}
