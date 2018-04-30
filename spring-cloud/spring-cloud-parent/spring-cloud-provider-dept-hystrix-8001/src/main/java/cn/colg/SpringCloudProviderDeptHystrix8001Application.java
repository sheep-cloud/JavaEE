package cn.colg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Spring Boot 启动类
 * 
 * <pre>
 * '@EnableCircuitBreaker'：             开启对hystrix熔断机制的支持
 * </pre>
 *
 * @author colg
 */
@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableEurekaClient
@MapperScan("cn.colg.dao")
@SpringBootApplication
public class SpringCloudProviderDeptHystrix8001Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudProviderDeptHystrix8001Application.class, args);
    }
}