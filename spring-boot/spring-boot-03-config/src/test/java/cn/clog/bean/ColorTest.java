package cn.clog.bean;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.clog.SpringBoot03ConfigApplicationTest;
import lombok.extern.slf4j.Slf4j;

/**
 * Color 实体 '@PropertySource' 加载指定的配置文件 测试
 *
 * @author colg
 */
@Slf4j
public class ColorTest extends SpringBoot03ConfigApplicationTest {

    @Autowired
    private Color color;

    /**
     * Test method for {@link cn.clog.bean.Color#Color()}.
     */
    @Test
    public void testColor() {
        log.info("testColor() >> color : {}", color);
    }

}
