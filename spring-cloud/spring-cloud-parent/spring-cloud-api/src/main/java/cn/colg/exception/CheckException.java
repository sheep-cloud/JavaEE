package cn.colg.exception;

import lombok.NoArgsConstructor;

/**
 * 校验错误异常
 *
 * @author colg
 */
@NoArgsConstructor
public class CheckException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CheckException(String message) {
        super(message);
    }

}
