package cn.colg.rabbitmq;

import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import cn.colg.SpringBootAdvance02AmqpApplicationTests;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Dict;

/**
 * rabbitmq 测试类
 *
 * @author colg
 */
public class RabbitmqTest extends SpringBootAdvance02AmqpApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test01() throws Exception {
        /*
         * colg  [使用特定路由密钥将消息发送到特定交换机]
         *  交换交换的名称, 路由密钥, 消息要发送的消息
         */
        // message 需要自己构造一个; 定义消息体内容和消息头
        // rabbitTemplate.send(exchange, routingKey, message);
        
        // object默认当成消息体, 只需要传入要发送的对象, 自动序列化发送给rabbitmq
        Dict object = Dict.create().set("msg", "这是第一个消息")
                                   .set("data", CollUtil.newArrayList("HelloWorld", 123, true));
        rabbitTemplate.convertAndSend("exchange.direct", "colg.news", object);
    }
}
