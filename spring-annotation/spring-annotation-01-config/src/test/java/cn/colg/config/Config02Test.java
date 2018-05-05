package cn.colg.config;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cn.colg.bean.Person;
import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 配置类；'@Bean' 测试
 *
 * @author colg
 */
@Slf4j
public class Config02Test {

    /** 创建一个新的AnnotationConfigApplicationContext，从给定的注释类派生bean定义并自动刷新上下文。 */
    private AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config02.class);

    @Test
    public void testApplicationContext() throws Exception {
        // 返回此工厂中定义的所有Bean的名称。
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        CollUtil.newArrayList(definitionNames).forEach(name -> log.info("testApplicationContext() >> name : {}", name));
    }

    /**
     * Test method for {@link cn.colg.config.Config02#person()}.
     */
    @Test
    public void testPerson() {
        log.info("testPerson() >> : {}", "IOC容器创建完成。。。");
        Person person = (Person)applicationContext.getBean("person");
        Person person2 = (Person)applicationContext.getBean("person");
        log.info("testPerson() >> person == person2 : {}", person == person2);
    }

}
