package cn.colg.rbac.util;

import cn.colg.rbac.vo.ResultVo;
import com.github.pagehelper.Page;

import java.util.Collection;
import java.util.List;

/**
 * http 请求工具类
 *
 * @author colg
 */
public class ResultVoUtil {

    /**
     * 成功响应，无数据
     *
     * @return
     * @author colg
     */
    public static ResultVo success() {
        return new ResultVo(ResultVo.SUCCESS, ResultVo.MSG_SUCCESS);
    }

    /**
     * 成功响应，无数据，自定义消息
     *
     * @param message
     * @return
     * @author colg
     */
    public static ResultVo success(String message) {
        return new ResultVo(ResultVo.SUCCESS, message);
    }

    /**
     * 成功响应，有数据
     *
     * @param data
     * @return
     * @author colg
     */
    public static ResultVo success(Object data) {
        ResultVo resultVo = success();
        convertData(data, resultVo);
        return resultVo;
    }

    /**
     * 转换分页信息
     *
     * @param data
     * @param resultVo
     * @author colg
     */
    private static void convertData(Object data, ResultVo resultVo) {
        if (data instanceof Page<?>) {
            Page<?> page = (Page<?>) data;
            resultVo.setData(page.getResult())
                    .setTotal(page.getTotal());

        } else if (data instanceof Collection<?>) {
            List<?> list = (List<?>) data;
            resultVo.setData(list)
                    .setTotal((long) list.size());
        } else {
            resultVo.setData(data);
        }
    }

    /**
     * 失败响应
     *
     * @param code
     * @param message
     * @return
     * @author colg
     */
    public static ResultVo fail(Integer code, String message) {
        return new ResultVo(code, message);
    }

    /**
     * 通信异常
     *
     * @return
     * @author colg
     */
    public static ResultVo fail() {
        return new ResultVo(ResultVo.CONNECTION_FAIL, ResultVo.MSG_FAIL_CONNECTION);
    }

}
