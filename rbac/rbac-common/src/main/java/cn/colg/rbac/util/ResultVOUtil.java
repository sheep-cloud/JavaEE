package cn.colg.rbac.util;

import cn.colg.rbac.vo.ResultVO;

/**
 * http请求 工具类
 *
 * @author colg
 */
public class ResultVOUtil {

    public static ResultVO success(Object data) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(ResultVO.SUCCESS)
                .setMsg(ResultVO.MSG_SUCCESS)
                .setData(data);
        return resultVO;
    }

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO fail(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code)
                .setMsg(msg);
        return resultVO;
    }
}
