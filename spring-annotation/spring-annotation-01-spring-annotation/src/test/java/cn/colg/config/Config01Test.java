package cn.colg.config;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cn.colg.bean.Person;
import cn.hutool.core.lang.Console;

/**
 * 配置类 - 测试
 *
 * @author colg
 */
public class Config01Test {

    private AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config01.class);

    @Test
    public void testPerson() {
        Person person = applicationContext.getBean(Person.class);
        Console.log(person);

        assertNotNull(person);
    }

    @Test
    public void testPerson2() throws Exception {
        String[] beanNamesForType = applicationContext.getBeanNamesForType(Person.class);
        Arrays.asList(beanNamesForType).stream().forEach(System.out::println);
        for (String beanName : beanNamesForType) {
            Console.log(beanName);
        }
    }

}
