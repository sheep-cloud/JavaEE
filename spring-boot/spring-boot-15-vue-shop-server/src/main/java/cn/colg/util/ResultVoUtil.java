package cn.colg.util;

import cn.colg.vo.ResultVo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * http 请求工具类
 *
 * @author colg
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResultVoUtil {

    /**
     * 成功响应
     *
     * @param data 数据
     * @return {code: 0, data: data}
     * @author colg
     */
    public static ResultVo success(Object data) {
        return new ResultVo().setCode(ResultVo.SUCCESS)
                             .setData(data);
    }

    /**
     * 失败响应
     *
     * @param msg 提示信息
     * @return {code: 1, msg: msg}
     * @author colg
     */
    public static ResultVo fail(String msg) {
        return new ResultVo().setCode(ResultVo.FAIL)
                             .setMsg(msg);
    }

}
