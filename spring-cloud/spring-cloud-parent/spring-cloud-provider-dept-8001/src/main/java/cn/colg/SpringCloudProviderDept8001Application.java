package cn.colg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Spring Boot 启动类
 * 
 * <pre>
 * '@EnableEurekaClient'：               启动后自动注册进eureka服务中（就是如果选用的注册中心是eureka，那么就推荐@EnableEurekaClient）
 * '@EnableDiscoveryClient'：      启动后发现客户端（如果是其他的注册中心，那么推荐使用@EnableDiscoveryClient）
 * </pre>
 *
 * @author colg
 */
@EnableDiscoveryClient
@EnableEurekaClient
@MapperScan("cn.colg.dao")
@SpringBootApplication
public class SpringCloudProviderDept8001Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudProviderDept8001Application.class, args);
    }
}