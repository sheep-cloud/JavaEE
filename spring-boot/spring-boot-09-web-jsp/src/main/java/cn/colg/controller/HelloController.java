package cn.colg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * HelloController
 *
 * @author colg
 */
@Controller
public class HelloController {

    @GetMapping("success")
    public String hello(Model model) {
        model.addAttribute("msg", "你好！");
        return "success";
    }
}
