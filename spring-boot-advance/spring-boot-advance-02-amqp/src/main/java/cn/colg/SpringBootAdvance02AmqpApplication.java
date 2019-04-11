package cn.colg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot 启动类
 * 
 * <pre>
 *  自动配置类
 *      1. 自动配置类: `org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration`
 *      2. 自动配置了连接工厂: CachingConnectionFactory
 *      3. RabbitProperties.class 封装了 RabbitMQ的配置
 *      4. RabbitTemplate 给RabbitMQ发送和接受消息
 *      5. AmqpAdmin RabbitMQ系统管理功能组件
 * </pre>
 *
 * @author colg
 */
@SpringBootApplication
public class SpringBootAdvance02AmqpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAdvance02AmqpApplication.class, args);
    }

}
