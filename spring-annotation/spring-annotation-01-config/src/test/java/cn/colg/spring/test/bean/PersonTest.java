package cn.colg.spring.test.bean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.colg.bean.Person;
import lombok.extern.slf4j.Slf4j;

/**
 * xml 测试; 使用spring-test
 * 
 * <pre>
 * `@RunWith(SpringJUnit4ClassRunner.class)`:   用于指定junit运行环境, 是junit提供给其他框架测试环境接口扩展, 为了便于使用spring的依赖注入,
 *                                              spring提供了`org.springframework.test.context.junit4.SpringJUnit4ClassRunner`作为junit测试环境
 * 
 * `@ContextConfiguration(locations = "classpath:beans.xml")`:  导入配置文件
 * </pre>
 *
 * @author colg
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class PersonTest {

    @Autowired
    private Person person;

    /**
     * Test method for {@link cn.colg.bean.Person}.
     */
    @Test
    public void test() {
        log.info("test() >> person : {}", person);
    }

}
