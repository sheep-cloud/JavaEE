package cn.colg.component;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import cn.colg.exception.UserNotExistException;
import cn.hutool.core.lang.Dict;

/**
 * 异常处理器2
 *
 * @author colg
 */
@ControllerAdvice
public class MyExceptionHandler2 {

    /**
     * 2. 转发到/error进行自定义响应效果处理
     *
     * @param e
     * @return
     * @author colg
     */
    @ExceptionHandler(UserNotExistException.class)
    public String handleException(Exception e, HttpServletRequest request) {
        Dict dict = Dict.create()
                        .set("code", "user.notexist")
                        .set("message", e.getMessage());
        // 传入自己定义的错误状态码 4xx 5xx，否则就不会进入定制错误页面的解析流程
        request.setAttribute("javax.servlet.error.status_code", HttpStatus.BAD_REQUEST.value());
        
        request.setAttribute("ext", dict);
        // 转发到/error
        return "forward:/error";
    }
}
