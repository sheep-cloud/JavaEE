package cn.colg.exception;

/**
 * 校验错误异常
 *
 * @author colg
 */
public class CheckException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CheckException(String message) {
        super(message);
    }

}
