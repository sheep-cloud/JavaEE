package cn.colg.config;

import org.junit.Test;

import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpUtil;

/**
 * 负载均衡 测试
 *
 * @author colg
 */
public class RestTemplateBeanTest {

    /**
     * 测试 负载均衡规则
     *
     */
    @Test
    public void testRule() {
        int sendCount = 10;
        for (int i = 0; i < sendCount; i++) {
            String result = HttpUtil.get("http://localhost/consumer/dept/get/1 ");
            Console.log(result);
        }
    }

}
