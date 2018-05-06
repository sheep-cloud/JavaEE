package cn.colg.config;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 配置类；`@Profile` 测试
 *
 * @author colg
 */
@Slf4j
public class Config01OfProfileTest {

    private AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

    @Test
    public void test() {
        // 设置需要激活的环境
        applicationContext.getEnvironment().setActiveProfiles("test", "dev");
        // 注册配置类
        applicationContext.register(Config01OfProfile.class);
        // 启动刷新容器
        applicationContext.refresh();
        log.info("test() >> 当前激活的环境 : {}", CollUtil.newArrayList(applicationContext.getEnvironment().getActiveProfiles()));

        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        CollUtil.newArrayList(definitionNames).forEach(name -> log.info("test() >> name : {}", name));
    }

    /**
     * Test method for {@link cn.colg.config.Config01OfProfile#dataSourceTest(java.lang.String)}.
     */
    @Test
    public void testDataSourceTest() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link cn.colg.config.Config01OfProfile#dataSourceDev(java.lang.String)}.
     */
    @Test
    public void testDataSourceDev() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link cn.colg.config.Config01OfProfile#dataSourceProd(java.lang.String)}.
     */
    @Test
    public void testDataSourceProd() {
        fail("Not yet implemented"); // TODO
    }

}
