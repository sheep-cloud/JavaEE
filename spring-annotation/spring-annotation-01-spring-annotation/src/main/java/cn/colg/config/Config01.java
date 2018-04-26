package cn.colg.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

import cn.colg.bean.Person;

/**
 * 配置类 == 配置文件
 *
 * @author colg
 */
@Configurable
public class Config01 {

    /*
         @Configurable： 告诉Srping这是一个配置类
     */

    // ----------------------------------------------------------------------------------------------------

    @Bean
    public Person person() {
        /*
             @Bean： 给容器注册一个Bean；类型为返回值的类型，id默认就是用方法名作为id
         */
        return new Person("Rose", 20);
    }
}
