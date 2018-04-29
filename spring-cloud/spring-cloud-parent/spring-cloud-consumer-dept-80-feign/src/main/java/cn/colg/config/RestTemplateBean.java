package cn.colg.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RetryRule;

/**
 * 自定义bean
 * 
 * <pre>
 * boot --> spring  |   applicationContext.xml --> @Configuration  |    ConfigBean --> applicationContext.xml
 * </pre>
 *
 * @author colg
 */
@Configuration
public class RestTemplateBean {

    /**
     * Spring的中心类用于同步客户端HTTP访问。它简化了与HTTP服务器的通信，并实施了RESTful原则。它处理HTTP连接，让应用程序代码提供URL（带有可能的模板变量）并提取结果。
     * 
     * <pre>
     * '@LoadBalanced'： Spring Cloud Ribbon是基于Netflix Ribbon实现的一套客户端，负载均衡的工具。
     * </pre>
     *
     * @return
     */
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    /**
     * 设置负载均衡算法规则，默认为"轮询"
     *
     * @return
     */
    @Bean
    public IRule iRule() {
        // 随机
        // return new RandomRule();
        
        // 按照轮询策略，如果获取服务失败，则在指定时间内进行重试，获取可用的服务
        return new RetryRule();
    }
}
