package cn.colg.bean;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * API 响应
 *
 * @author colg
 */
@ApiModel(description = "API 响应")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class ResultBean implements Serializable {

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

    /** 成功，默认提示消息 */
    public static final String MSG_SUCCESS = "success";
    /** 失败，默认提示消息 */
    public static final String MSG_FAIL = "fail";

    /// ----------------------------------------------------------------------------------------------------

    @ApiModelProperty("返回的信息（主要出错的时候使用）")
    private String msg = MSG_SUCCESS;

    @ApiModelProperty("接口返回码， 0：成功；>0：表示已知的异常（例如业务异常，提示错误等，需要单独处理）；<0：表示未知的异常（例如没有登录，网络异常等，需要统一处理）")
    private int code = SUCCESS;

    @ApiModelProperty("返回的数据")
    private Object data;

    /// ----------------------------------------------------------------------------------------------------

    /**
     * 未知异常
     *
     * @param e
     */
    public ResultBean(Throwable e) {
        this.msg = e.toString();
        this.code = UNKNOWN_FAIL;
    }

    /**
     * 成功时的响应：无返回数据
     *
     * @return
     */
    public static ResultBean success() {
        return new ResultBean();
    }

    /**
     * 成功时的响应：有返回数据
     *
     * @param data
     * @return
     */
    public static ResultBean success(Object data) {
        return success().setData(data);
    }

    /**
     * 失败时的响应：通信异常
     *
     * @param msg 通信异常提示信息
     * @return
     */
    public static ResultBean fail(String msg) {
        return new ResultBean().setCode(CONNECTION_FAIL).setMsg(msg);
    }
}