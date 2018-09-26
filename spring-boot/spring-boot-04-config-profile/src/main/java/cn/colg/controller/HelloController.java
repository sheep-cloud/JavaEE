package cn.colg.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloController
 *
 * @author colg
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public Map<String, Object> hello() {
        return Collections.singletonMap("Hello", "Profile!");
    }
}
