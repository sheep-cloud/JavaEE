package cn.colg;

import org.junit.Test;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpUtil;

/**
 * 监控测试
 *
 * @author colg
 */
public class SpringCloudConsumerHystrixDashboard9001ApplicationTest {

    @Test
    public void test() {
        for (int i = 0; i < 10000; i++) {
            String id = RandomUtil.randomString("12345", 1);
            String result = HttpUtil.get("http://localhost:8001/dept/get/" + id);
            Console.log(result);
        }
    }

}
