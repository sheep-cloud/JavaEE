package cn.colg.rbac.config;

import cn.colg.rbac.exception.CheckException;
import cn.colg.rbac.vo.ResultVo;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.naming.NoPermissionException;
import javax.servlet.http.HttpServletRequest;

import static cn.colg.rbac.util.ResultVoUtil.fail;

/**
 * 异常处理
 *
 * @author colg
 */
@RestControllerAdvice
public class HandlerException {

    /**
     * 未知异常
     *
     * @param e
     * @param request
     * @return
     * @author colg
     */
    @ResponseStatus
    @ExceptionHandler(Exception.class)
    public Object systemExceptionHandler(Exception e, HttpServletRequest request) {
        return fail(ResultVo.UNKNOWN_FAIL, "未知异常: " + request.getRequestURL().toString() + ": " + e.getMessage());
    }

    /**
     * 接口不存在 - Not Found
     *
     * @param e
     * @param request
     * @return
     * @author colg
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResultVo notFoundExceptionHandler(NoHandlerFoundException e, HttpServletRequest request) {
        return fail(HttpStatus.NOT_FOUND.value(), "无效接口: " + request.getRequestURL().toString());
    }

    /**
     * 请求方法错误 - Method Not Allowed
     *
     * @param e
     * @param request
     * @return
     * @author colg
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultVo requestMethodExcpetionHandler(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        return fail(HttpStatus.METHOD_NOT_ALLOWED.value(), "请求异常: " + request.getMethod());
    }

    /**
     * 缺少参数 - Payment Required
     *
     * @param e
     * @return
     * @author colg
     */
    @ResponseStatus(HttpStatus.PAYMENT_REQUIRED)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResultVo missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException e) {
        return fail(HttpStatus.PAYMENT_REQUIRED.value(), "缺少参数: " + e.getParameterName() + ":" + e.getParameterType() + "");
    }

    /**
     * 方法参数类型不匹配
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.PAYMENT_REQUIRED)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResultVo methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException e) {
        return fail(HttpStatus.PAYMENT_REQUIRED.value(), "参数错误: " + e.getName() + ":" + e.getRequiredType().getName());
    }

    /**
     * sql 异常
     *
     * @param e
     * @return
     * @author colg
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadSqlGrammarException.class)
    public ResultVo badSqlGrammarExceptionHandler(BadSqlGrammarException e) {
        return fail(HttpStatus.BAD_REQUEST.value(), "SQL异常: " + e.getCause().getLocalizedMessage());
    }

    /**
     * 数据绑定异常
     *
     * @param e
     * @return
     * @author colg
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ResultVo bindExceptionHandler(BindException e) {
        return fail(HttpStatus.BAD_REQUEST.value(), "绑定异常: " + e.getBindingResult().getFieldError().getDefaultMessage());
    }

    /**
     * 校验异常
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(CheckException.class)
    public ResultVo checkExceptionHandler(CheckException e) {
        return fail(HttpStatus.OK.value(), e.getMessage());
    }

    /**
     * 没有权限
     *
     * @param e
     * @return
     * @author colg
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(NoPermissionException.class)
    public ResultVo checkExceptionHandler(NoPermissionException e) {
        return fail(ResultVo.NO_PERMISSION, e.getMessage());
    }

}