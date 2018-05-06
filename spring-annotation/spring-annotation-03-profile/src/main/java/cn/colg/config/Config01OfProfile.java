package cn.colg.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import com.alibaba.druid.pool.DruidDataSource;

import cn.colg.bean.Yellow;

/**
 * 配置类；`@Profile`
 * 
 * <pre>
 * `@Profile`： Spring提供的可以根据当前环境，动态的激活和切换一些列组件的功能。指定组件在哪个环境下才能耐被注册到容器中。
 *  不指定环境的情况下，任何环境下都能注册这个组件
 *  1. 加了环境标识的bean，只有这个环境被激活的时候才能注册到容器中，默认是default环境
 * 
 * 开发环境、测试环境、生产环境；
 * 数据源：(/A)(/B)(/C)
 * </pre>
 *
 * @author colg
 */
@PropertySource(value = {"classpath:dbconfig.properties"})
@Configuration
public class Config01OfProfile {

    @Value("${db.user}")
    private String username;
    @Value("${db.password}")
    private String password;

    @Profile("test")
    @Bean
    public Yellow yellow() {
        return new Yellow();
    }

    @Profile("test")
    @Bean("testDataSource")
    public DataSource dataSourceTest(@Value("${db.driverClass}") String driverClass) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/spring_cloud_8001?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false");
        dataSource.setDriverClassName(driverClass);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Profile("dev")
    @Bean("devDataSource")
    public DataSource dataSourceDev(@Value("${db.driverClass}") String driverClass) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/spring_cloud_8002?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false");
        dataSource.setDriverClassName(driverClass);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Profile("prod")
    @Bean("prodDataSource")
    public DataSource dataSourceProd(@Value("${db.driverClass}") String driverClass) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/spring_cloud_8003?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false");
        dataSource.setDriverClassName(driverClass);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

}
