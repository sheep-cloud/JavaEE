package cn.colg.config;

import java.util.Map;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cn.colg.bean.Person;
import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 配置类；'@Conditional' 测试
 *
 * @author colg
 */
@Slf4j
public class Config03Test {

    /** 创建一个新的AnnotationConfigApplicationContext，从给定的注释类派生bean定义并自动刷新上下文。 */
    private AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config03.class);

    @Test
    public void testApplicationContext() throws Exception {
        // 返回此工厂中定义的所有Bean的名称。
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        CollUtil.newArrayList(definitionNames).forEach(definitionName -> log.info("testApplicationContext() >> name : {}", definitionName));
        
        Map<String, Person> beansOfType = applicationContext.getBeansOfType(Person.class);
        beansOfType.forEach((key, value) -> {
            log.info("testApplicationContext() >> {} : {}", key, value);
        });
    }

    /**
     * Test method for {@link cn.colg.config.Config03#person01()}.
     */
    @Test
    public void testPerson01() {
        Person person = applicationContext.getBean(Person.class);
        log.info("testPerson01() >> person : {}", person);
    }

}
