package cn.colg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.colg.factory.ColorFactoryBean;

/**
 * 配置类；'FactoryBean<T>'
 * 
 * <pre>
 * 'FactoryBean<T>'：    使用Spring提供的FactoryBean（工厂Bean）
 *  1. 默认获取到的是工厂bean调用getObject创建的对象
 *  2. 要获取工厂Bean本身，我们需要给id前面加一个标识"&"
 * </pre>
 * 
 * 
 * @author colg
 */
@Configuration
public class Config05 {

    @Bean
    public ColorFactoryBean colorFactoryBean() {
        return new ColorFactoryBean();
    }
}
