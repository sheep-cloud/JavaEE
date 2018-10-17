package cn.colg.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.colg.exception.UserNotExistException;

/**
 * HelloController
 *
 * @author colg
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public Map<String, Object> hello() {
        return Collections.singletonMap("Hello", "web-restful-crud!");
    }
    
    /**
     * 测试自定义异常
     *
     * @param user
     * @return
     * @author colg
     */
    @GetMapping("/hello/user")
    public Map<String, Object> helloUser(@RequestParam("user") String user) {
        String name = "aaa";
        if (name.equals(user)) {
            throw new UserNotExistException();
        }
        return Collections.singletonMap("Hello", user);
    }
}
