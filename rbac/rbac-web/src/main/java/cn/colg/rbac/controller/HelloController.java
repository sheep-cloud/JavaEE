package cn.colg.rbac.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hutool.core.lang.Dict;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }
    
    @RequestMapping("/json")
    @ResponseBody
    public Dict json() {
        return new Dict().set("hello", "Json");
    }
}
