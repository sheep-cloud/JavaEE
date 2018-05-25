package cn.colg;

import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.colg.config.RedisConfig;
import lombok.extern.slf4j.Slf4j;

/**
 * 测试基类
 *
 * @author colg
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RedisConfig.class})
public abstract class BaseTest {

    @After
    public void tearDown() throws Exception {
        log.info("tearDown() : {}", "----------------------------------------------------------------------------------------------------\n");
    }
}
