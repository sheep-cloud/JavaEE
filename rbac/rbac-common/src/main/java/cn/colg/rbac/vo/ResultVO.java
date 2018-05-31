package cn.colg.rbac.vo;

import com.alibaba.fastjson.annotation.JSONField;

import cn.colg.rbac.core.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * http请求返回的最外层对象
 *
 * @author colg
 */
@Getter
@Setter
@Accessors(chain = true)
public class ResultVO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 成功 */
    public static final int SUCCESS = 0;
    /** 字段校验，参数非法异常 */
    public static final int CHECK_FAIL = 1;
    /** 未知异常 */
    public static final int UNKNOWN_FAIL = -99;

    /** 成功，默认提示消息 */
    public static final String MSG_SUCCESS = "success";
    /** 失败，默认提示消息 */
    public static final String MSG_FAIL = "fail";

    /// ----------------------------------------------------------------------------------------------------

    /** 错误码 */
    private Integer code;
    /** 提示信息 */
    private String msg;
    /** 具体内容 */
    @JSONField(ordinal = 1)
    private Object data;
}
