package cn.colg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Spring Boot 启动类
 * 
 * @author colg
 */
@EnableDiscoveryClient
@EnableEurekaClient
@MapperScan("cn.colg.dao")
@SpringBootApplication
public class SpringCloudProviderDept8003Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudProviderDept8003Application.class, args);
    }
}