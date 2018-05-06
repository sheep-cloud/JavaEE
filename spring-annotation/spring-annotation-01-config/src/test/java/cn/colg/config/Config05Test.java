package cn.colg.config;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 配置类；'FactoryBean<T>' 测试
 *
 * @author colg
 */
@Slf4j
public class Config05Test {

    /** 创建一个新的AnnotationConfigApplicationContext，从给定的注释类派生bean定义并自动刷新上下文。 */
    private AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config05.class);

    @Test
    public void testApplicationContext() throws Exception {
        // 返回此工厂中定义的所有Bean的名称。
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        CollUtil.newArrayList(definitionNames).forEach(definitionName -> log.info("testApplicationContext() >> name : {}", definitionName));
    }

    /**
     * Test method for {@link cn.colg.config.Config05#colorFactoryBean()}.
     */
    @Test
    public void testColorFactoryBean() {
        Object object = applicationContext.getBean("colorFactoryBean");
        log.info("testColorFactoryBean() >> object.getClass() : {}", object.getClass());
        // 工厂Bean获取的是调用getObject创建的对象
        // testColorFactoryBean() >> object.getClass() : class cn.colg.bean.Color

        Object object2 = applicationContext.getBean("&colorFactoryBean");
        log.info("testColorFactoryBean() >> object2.getClass() : {}", object2.getClass());
        // testColorFactoryBean() >> object2.getClass() : class cn.colg.factory.ColorFactoryBean
    }

}
