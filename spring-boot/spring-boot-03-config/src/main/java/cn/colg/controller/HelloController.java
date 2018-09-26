package cn.colg.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloController
 *
 * @author colg
 */
@RestController
public class HelloController {

    @Value("${person.last-name}")
    private String name;

    @GetMapping("/hello")
    public String hello() {
        return "hello " + name;
    }

    @GetMapping("/hello2")
    public String hello2(@Value("${person.last-name}") String name2) {
        return "hello " + name2;
    }
}
