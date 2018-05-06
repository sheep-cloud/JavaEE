package cn.colg.config;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cn.colg.bean.Car;
import cn.colg.bean.Cat;
import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 配置类；Bean的生命周期 测试
 *
 * @author colg
 */
@Slf4j
public class Config06Test {

    /** 创建一个新的AnnotationConfigApplicationContext，从给定的注释类派生bean定义并自动刷新上下文。 */
    private AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config06.class);

    @Test
    public void testApplicationContext() throws Exception {
        // 返回此工厂中定义的所有Bean的名称。
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        CollUtil.newArrayList(definitionNames).forEach(definitionName -> log.info("testApplicationContext() >> name : {}", definitionName));
    }

    /**
     * Test method for {@link cn.colg.config.Config06#car()}.
     */
    @Test
    public void testCar() {
        Car car = applicationContext.getBean(Car.class);
        log.info("testCar() >> car : {}", car);
        applicationContext.close();
    }

    /**
     * Test method for {@link cn.colg.config.Config06#cat()}.
     */
    @Test
    public void testCat() {
        Cat cat = applicationContext.getBean(Cat.class);
        log.info("testCar() >> cat : {}", cat);
        applicationContext.close();
    }

}
