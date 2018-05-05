package cn.colg.config;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cn.colg.bean.Bule;
import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 配置类；'@Import' 测试
 *
 * @author colg
 */
@Slf4j
public class Config04Test {

    /** 创建一个新的AnnotationConfigApplicationContext，从给定的注释类派生bean定义并自动刷新上下文。 */
    private AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config04.class);

    @Test
    public void testApplicationContext() throws Exception {
        // 返回此工厂中定义的所有Bean的名称。
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        CollUtil.newArrayList(definitionNames).forEach(definitionName -> log.info("testApplicationContext() >> name : {}", definitionName));
    }

    @Test
    public void test() {
        Bule bule = applicationContext.getBean(Bule.class);
        log.info("test() >> bule : {}", bule);
    }

}
