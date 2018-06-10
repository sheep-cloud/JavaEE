package cn.colg.tx.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 声明式事务
 * 
 * <pre>
 * 环境搭建：
 *  1. 导入相关依赖
 *      数据源、数据库驱动，spring-jdbc
 *  2. 配置数据源、JdbcTemplate（Spring提供的简化数据库操作的工具）操作数据
 *  3. 给方法上标注 @Transactional 表示当前方法是一个事务方法
 *  4. @EnableTransactionManagement 开启基于注解的事务管理功能
 *      &#64;Enablexxx
 *  5. 配置事务管理器来控制事务
 *  
 * 原理：
 *  1. @EnableTransactionManagement
 *      利用了 @Import(TransactionManagementConfigurationSelector.class) 给容器中导入组件
 *      导入两个组件
 *          1. AutoProxyRegistrar
 *              给容器中注册一个 InfrastructureAdvisorAutoProxyCreator 组件
 *          2. ProxyTransactionManagementConfiguration
 * </pre>
 *
 * @author colg
 */
@EnableTransactionManagement
@ComponentScan("cn.colg.tx")
@Configuration
public class TxConfig {

    /**
     * 数据源
     *
     * @return
     * @throws PropertyVetoException
     * @author colg
     */
    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("root");
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/spring_annotation?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false");
        return dataSource;
    }

    /**
     * jdbc模版
     *
     * @return
     * @throws PropertyVetoException
     * @author colg
     */
    @Bean
    public JdbcTemplate jdbcTemplate() throws PropertyVetoException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        return jdbcTemplate;
    }

    /**
     * 事务管理器
     *
     * @return
     * @throws PropertyVetoException
     * @author colg
     */
    @Bean
    public PlatformTransactionManager platformTransactionManager() throws PropertyVetoException {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource());
        return transactionManager;
    }
}
