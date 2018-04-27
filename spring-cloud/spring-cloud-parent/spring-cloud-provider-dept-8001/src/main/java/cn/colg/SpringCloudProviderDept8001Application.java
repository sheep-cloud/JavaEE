package cn.colg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Spring Boot 启动类
 * 
 * <pre>
 * '@EnableEurekaClient'：本服务启动后会自动注册进eureka服务中
 * </pre>
 *
 * @author colg
 */
@EnableEurekaClient
@MapperScan("cn.colg.dao")
@SpringBootApplication
public class SpringCloudProviderDept8001Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudProviderDept8001Application.class, args);
    }
}