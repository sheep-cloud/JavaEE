package cn.colg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Spring Boot 启动类
 * 
 * <pre>
 * '@EnableEurekaClient'：                       启动后自动注册进eureka服务中
 * '@EnableDiscoveryClient'：              启动后发现客户端
 * '@EnableCircuitBreaker'：                 开启对 hystrix 熔断机制的支持
 * '@SpringCloudApplication'：           包含 '@SpringBootApplication'、'@EnableDiscoveryClient'、'@EnableCircuitBreaker'
 * </pre>
 *
 * @author colg
 */
@EnableEurekaClient
@MapperScan("cn.colg.dao")
@SpringCloudApplication
public class SpringCloudProviderDeptHystrix8001Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudProviderDeptHystrix8001Application.class, args);
    }
}
