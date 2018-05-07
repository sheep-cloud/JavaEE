package cn.clog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.clog.bean.Cat;
import cn.clog.bean.Color;
import cn.clog.bean.Person;
import cn.clog.bean.Phone;

/**
 * 配置类；'@Configuration'： 指定当前类是一个配置类；就是来替代之前的Spring配置文件
 *
 * @author colg
 */
@Configuration
public class AppConfig {

    /**
     * 将方法的返回值添加到容器中，容器中这个组件默认的id就是方法名
     *
     * @return
     */
    @Bean
    public Person person() {
        return new Person();
    }

    @Bean
    public Cat cat() {
        return new Cat();
    }

    @Bean
    public Color color() {
        return new Color();
    }

    @Bean
    public Phone phone() {
        return new Phone();
    }
}
