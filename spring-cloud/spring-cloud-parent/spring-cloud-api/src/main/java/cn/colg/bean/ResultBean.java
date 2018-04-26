package cn.colg.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * API 响应
 *
 * @author colg
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class ResultBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 没有登录 */
    public static final int NO_LOGIN = -1;

    /** 成功 */
    public static final int SUCCESS = 0;

    /** 校验出错，参数非法 */
    public static final int CHECK_FAIL = 1;

    /** 没有权限 */
    public static final int NO_PERMISSION = 2;

    /** 未知异常 */
    public static final int UNKNOWN_EXCEPTION = -99;

    /**
     * 返回的信息（主要出错的时候使用）
     */
    private String msg = "success";

    /**
     * 接口返回码，0代表成功，其他看对应的定义
     * 
     * <pre>
     * 推荐做法：
     *   0： 成功 
     *  >0： 表示已知的异常（例如业务异常，提示错误等，需要单独处理）
     *  <0： 表示未知的异常（例如没有登录，网络异常等，需要统一处理）
     * </pre>
     */
    private int code = SUCCESS;

    /**
     * 返回的数据
     */
    private Object data;

    public ResultBean(Throwable e) {
        this.msg = e.toString();
        this.code = UNKNOWN_EXCEPTION;
    }
}
