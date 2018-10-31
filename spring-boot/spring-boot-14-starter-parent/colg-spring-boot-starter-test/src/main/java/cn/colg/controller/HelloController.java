package cn.colg.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.colg.starter.HelloService;

/**
 * HelloController
 *
 * @author colg
 */
@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/hello")
    public Map<String, Object> hello() {
        String msg = helloService.sayHelloColg("jack");
        return Collections.singletonMap("msg", msg);
    }
}
