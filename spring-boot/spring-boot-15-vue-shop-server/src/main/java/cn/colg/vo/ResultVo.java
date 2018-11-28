package cn.colg.vo;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

import cn.colg.core.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * http 请求返回的最外层对象
 *
 * @author colg
 */
@Getter
@Setter
@Accessors(chain = true)
public class ResultVo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 成功 */
    public static final int SUCCESS = 0;

    /** 失败 */
    public static final int FAIL = 1;

    /** 
     * 接口返回码
     */
    private Integer code;

    /**
     * 返回的信息（出错的时候使用）
     */
    private String msg;
    
    /**
     * 返回的数据
     */
    @JSONField(ordinal = 1)
    private Object data;

}
