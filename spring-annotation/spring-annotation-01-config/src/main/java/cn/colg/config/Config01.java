package cn.colg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.colg.bean.Person;

/**
 * 配置类
 * 
 * <pre>
 * `@Configuration`：    告诉Spring这是一个配置类
 * </pre>
 *
 * @author colg
 */
@Configuration
public class Config01 {

    /**
     * 给容器注册一个Bean；类型为返回值的类型，id默认是用方法名作为id
     *
     * @return
     */
    @Bean("person01")
    public Person person() {
        return new Person("Rose", 20);
    }
}
