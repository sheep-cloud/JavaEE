package cn.colg.web;

import org.junit.Test;

import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpUtil;

/**
 * 部门Controller http测试
 *
 * @author colg
 */
public class ConsumerDeptControllerTest {

    private int sendCount = 1;

    @Test
    public void testGet() {
        for (int i = 0; i < sendCount ; i++) {
            String result = HttpUtil.get("http://localhost/consumer/dept/get/10");
            Console.log(result);
        }
    }

    @Test
    public void testList() {
        for (int i = 0; i < sendCount ; i++) {
            String result = HttpUtil.get("http://localhost/consumer/dept/list");
            Console.log(result);
        }
    }

}
