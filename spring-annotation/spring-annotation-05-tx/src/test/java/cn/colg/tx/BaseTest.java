package cn.colg.tx;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.colg.tx.config.TxConfig;

/**
 * 测试基础类
 *
 * @author colg
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TxConfig.class)
public abstract class BaseTest {

}
