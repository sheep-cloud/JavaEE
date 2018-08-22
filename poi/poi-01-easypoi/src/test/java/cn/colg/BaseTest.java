package cn.colg;

import org.junit.After;
import org.junit.Before;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;

/**
 * 测试基类
 *
 * @author colg
 */
public abstract class BaseTest {

    private long time;

    @Before
    public void Before() throws Exception {
        time = System.currentTimeMillis();
    }

    @After
    public void after() throws Exception {
        Console.log("Junit : [" + DateUtil.spendMs(time) + "ms]");
        Console.log("----------------------------------------------------------------------------------------------------");
    }

}
