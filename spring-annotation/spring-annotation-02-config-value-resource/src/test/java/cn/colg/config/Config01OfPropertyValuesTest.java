package cn.colg.config;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import cn.colg.bean.Person;
import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 配置类；'@PropertySource'： 属性赋值 测试
 *
 * @author colg
 */
@Slf4j
public class Config01OfPropertyValuesTest {

    /** 创建一个新的AnnotationConfigApplicationContext，从给定的注释类派生bean定义并自动刷新上下文。 */
    private AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config01OfPropertyValues.class);

    @Test
    public void testApplicationContext() throws Exception {
        // 返回此工厂中定义的所有Bean的名称。
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        CollUtil.newArrayList(definitionNames).forEach(definitionName -> log.info("testApplicationContext() >> name : {}", definitionName));
        
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String value = environment.getProperty("person.nickName");
        log.info("testApplicationContext() >> value : {}", value);
    }

    /**
     * Test method for {@link cn.colg.config.Config01OfPropertyValues#person()}.
     */
    @Test
    public void testPerson() {
        Person person = (Person)applicationContext.getBean("person");
        log.info("testPerson() >> person : {}", person);
    }

}
