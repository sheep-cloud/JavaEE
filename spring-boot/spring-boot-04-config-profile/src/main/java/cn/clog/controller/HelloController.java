package cn.clog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.hutool.core.lang.Dict;

/**
 * HelloController
 *
 * @author colg
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public Dict hello() {
        return Dict.create().set("Hello", "World!");
    }
}
