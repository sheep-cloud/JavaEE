package cn.rule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;

/**
 * 自定义负载均衡策略规则
 *
 * @author colg
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule iRule() {
        // 载均衡策略规则：轮询，每台机器5次
        return new MyRundomRule();
    }
}