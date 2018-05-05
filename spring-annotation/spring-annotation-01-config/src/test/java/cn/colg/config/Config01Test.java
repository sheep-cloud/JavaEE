package cn.colg.config;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cn.colg.bean.Person;
import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 配置类；'@ComponentScan' 测试
 *
 * @author colg
 */
@Slf4j
public class Config01Test {

    /** 创建一个新的AnnotationConfigApplicationContext，从给定的注释类派生bean定义并自动刷新上下文。 */
    private AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config01.class);

    /**
     * Test method for {@link cn.colg.config.Config01#person()}.
     */
    @Test
    public void testPerson() {
        Person person = (Person)applicationContext.getBean("person");
        log.info("testPerson() >> person : {}", person);

        /// 返回与给定类型（包括子类）相匹配的bean的名称，根据Bean定义或FactoryBeans的getObjectType的值来判断。
        // 注意：此方法仅反映顶级Bean。它不会检查可能与指定类型匹配的嵌套bean。
        String[] namesForType = applicationContext.getBeanNamesForType(Person.class);
        CollUtil.newArrayList(namesForType).forEach(name -> log.info("testPerson() >> name : {}", name));
    }

}
