package cn.colg.exception;

/**
 * 用户不存在异常
 *
 * @author colg
 */
public class UserNotExistException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserNotExistException() {
        super("用户不存在");
    }

}
