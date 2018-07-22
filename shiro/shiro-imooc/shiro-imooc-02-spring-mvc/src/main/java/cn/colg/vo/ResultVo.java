package cn.colg.vo;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

import cn.colg.core.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * http 请求返回的最外层对象
 *
 * @author colg
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class ResultVo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /// ----------------------------------------------------------------------------------------------------

    /** 成功 */
    public static final int SUCCESS = 0;

    /** 字段校验，参数非法异常 */
    public static final int CHECK_FAIL = 1;
    /** 没有权限 */
    public static final int NO_PERMISSION = 2;

    /** 没有登录 */
    public static final int NO_LOGIN = -1;
    /** 未知异常 */
    public static final int UNKNOWN_FAIL = -99;
    /** 通信异常 */
    public static final int CONNECTION_FAIL = -100;

    /// ----------------------------------------------------------------------------------------------------

    /** 成功，默认提示消息 {@value} */
    public static final String MSG_SUCCESS = "success";
    /** 失败，默认提示消息 {@value} */
    public static final String MSG_FAIL = "fail";

    /** 通信异常提示 {@value} */
    public static final String MSG_FAIL_CONNECTION = "服务通信异常，客户端服务已降级";

    /// ----------------------------------------------------------------------------------------------------

    /**
     * 接口返回码：
     * 
     * <pre>
     *  0： 成功；
     * >0：表示已知的异常（例如业务异常，校验异常等，需要单独处理）;
     * <0：表示未知的异常（例如没有登录，网络异常等，需要统一处理）。
     * </pre>
     */
    private Integer code;

    /**
     * 返回的信息（出错的时候使用）
     */
    private String message;

    /**
     * 返回的数据
     */
    @JSONField(ordinal = 1)
    private Object data;

    /**
     * 返回的集合总行数（分页的时候使用）
     * 
     * <pre>
     * -1：非集合
     * </pre>
     */
    private Long total = -1L;

}
