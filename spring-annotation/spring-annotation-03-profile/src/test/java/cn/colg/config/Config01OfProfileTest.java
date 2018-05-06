package cn.colg.config;

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

}
