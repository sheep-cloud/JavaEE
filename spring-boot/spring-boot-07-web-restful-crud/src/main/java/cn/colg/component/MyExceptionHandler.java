package cn.colg.component;

import org.springframework.web.bind.annotation.ExceptionHandler;

import cn.colg.exception.UserNotExistException;
import cn.hutool.core.lang.Dict;

/**
 * 异常处理器
 * 
 * //@RestControllerAdvice 禁用
 *
 * @author colg
 */
public class MyExceptionHandler {

    /**
     * 1. 没有自适应效果，浏览器和客户端返回的都是json
     *
     * @param e
     * @return
     * @author colg
     */
    @ExceptionHandler(UserNotExistException.class)
    public Dict handleException(Exception e) {
        Dict dict = Dict.create()
                        .set("code", "user.notexist")
                        .set("message", e.getMessage());
        return dict;
    }
}
