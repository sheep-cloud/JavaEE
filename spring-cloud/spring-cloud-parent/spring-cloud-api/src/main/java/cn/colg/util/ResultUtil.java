package cn.colg.util;

import cn.colg.bean.ResultBean;

/**
 * API 响应 工具类
 *
 * @author colg
 */
public class ResultUtil {

    private ResultUtil() {}

    /**
     * 成功时的响应
     *
     * @param data
     * @return
     */
    public static ResultBean success(Object data) {
        ResultBean resultBean = new ResultBean();
        resultBean.setData(data);
        return resultBean;
    }
}
