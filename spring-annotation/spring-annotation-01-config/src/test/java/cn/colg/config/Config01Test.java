package cn.colg.config;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cn.colg.bean.Person;
import lombok.extern.slf4j.Slf4j;

/**
 * 配置类 测试
 *
 * @author colg
 */
@Slf4j
public class Config01Test {

    private AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config01.class);

    /**
     * Test method for {@link cn.colg.config.Config01#person()}.
     */
    @Test
    public void testPerson() {
        Person person = (Person)applicationContext.getBean("person01");
        log.info("testPerson() >> person : {}", person);
    }

}
