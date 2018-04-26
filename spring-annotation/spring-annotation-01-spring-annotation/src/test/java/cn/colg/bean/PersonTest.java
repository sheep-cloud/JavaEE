package cn.colg.bean;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.hutool.core.lang.Console;

/**
 * 人 - 测试
 *
 * @author colg
 */
public class PersonTest {

    private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");

    @Test
    public void testPerson() {
        Person person = (Person)applicationContext.getBean("person");
        Console.log(person);

        assertNotNull(person);
    }

}
