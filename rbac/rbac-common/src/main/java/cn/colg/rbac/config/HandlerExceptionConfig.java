package cn.colg.rbac.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cn.colg.rbac.exception.CheckException;
import cn.colg.rbac.util.ResultVOUtil;
import cn.colg.rbac.vo.ResultVO;

/**
 * 异常处理
 *
 * @author colg
 */
@RestControllerAdvice
public class HandlerExceptionConfig {

    /**
     * 校验异常
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(CheckException.class)
    public Object handleCheckException(CheckException e) {
        return ResultVOUtil.fail(ResultVO.CHECK_FAIL, e.getMessage());
    }
}
