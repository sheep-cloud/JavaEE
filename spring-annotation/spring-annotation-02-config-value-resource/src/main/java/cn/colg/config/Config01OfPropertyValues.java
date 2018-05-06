package cn.colg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import cn.colg.bean.Person;

/**
 * 配置类；'@PropertySource'： 属性赋值
 * 
 * <pre>
 * '@PropertySource'：   读取配置文件中的k/v保存到运行的换件变量中；加载完外部的配置文件以使用${}取出配置文件的值
 * </pre>
 *
 * @author colg
 */
@PropertySource(value = {"classpath:person.properties"})
@Configuration
public class Config01OfPropertyValues {

    @Bean
    public Person person() {
        return new Person();
    }
}
