package cn.colg.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.colg.exception.UserNotExistException;
import lombok.extern.slf4j.Slf4j;

/**
 * HelloController
 *
 * @author colg
 */
@Slf4j
@RestController
public class HelloController {

    /** 是否是第一次调用 */
    private boolean isFstCall = true;
    /** 调用次数 */
    private Integer count = 1;

    @GetMapping("/hello")
    public Map<String, Object> hello() {
        log.info("是否是第一次调用: {}", isFstCall);
        log.info("调用次数: {}", count);
        isFstCall = false;
        count++;
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
