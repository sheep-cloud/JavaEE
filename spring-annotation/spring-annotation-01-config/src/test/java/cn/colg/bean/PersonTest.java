package cn.colg.bean;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

/**
 * Person 测试
 *
 * @author colg
 */
@Slf4j
public class PersonTest {

    /** 创建一个新的ClassPathXmlApplicationContext, 从给定的XML文件加载定义并自动刷新上下文 */
    private ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");

    @Test
    public void test() {
        Person person = (Person)applicationContext.getBean("person");
        log.info("test() >> person : {}", person);
    }

}
