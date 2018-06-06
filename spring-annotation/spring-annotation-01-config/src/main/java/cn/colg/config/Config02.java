package cn.colg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.colg.bean.Person;
import lombok.extern.slf4j.Slf4j;

/**
 * 配置类；'@Bean'
 *
 * @author colg
 */
@Slf4j
@Configuration
public class Config02 {

    /**
     * '@Bean'：相关注解
     * 
     * <pre>
     *  '@Scope'：默认是单实例
     *      `ConfigurableBeanFactory.SCOPE_SINGLETON`：  单实例（默认值），ioc容器启动会调用方法创建对象放到容器中。以后每次获取就是直接从容器（map.get()）中拿。
     *      `ConfigurableBeanFactory.SCOPE_PROTOTYPE`：  多实例：    ioc容器启动并不会去调用方法创建对象放到容器中。以后每次获取的时候才会调用方法创建对象。
     *  
     *  `@Lazy`：
     *      单实例bean：默认在容器启动的时候创建对象
     *      懒加载：容器启动不创建对象，第一次使用（获取）Bean创建对象，并初始化。
     * </pre>
     * 
     * @return
     */
    @Bean
    public Person person() {
        log.info("person() >> : {}", "给容器中添加Person...");
        return new Person().setName("Tom").setAge(29);
    }
    
}
