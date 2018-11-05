package cn.colg.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cn.colg.starter.ColgService;

/**
 * HelloController
 *
 * @author colg
 */
@RestController
public class HelloController {

    @Autowired
    private ColgService colgService;

    @GetMapping("/hello/{name}")
    public Map<String, Object> hello(@PathVariable String name) {
        String msg = colgService.sayHello(name);
        return Collections.singletonMap("msg", msg);
    }
}
