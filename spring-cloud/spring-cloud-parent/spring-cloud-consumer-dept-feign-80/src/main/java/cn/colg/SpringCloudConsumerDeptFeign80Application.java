package cn.colg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * Spirng Boot 启动类
 * 
 * <pre>
 * `@EnableFeignClients`：    开启Feign功能，接口调用
 * </pre>
 * 
 * @author colg
 */
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class SpringCloudConsumerDeptFeign80Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConsumerDeptFeign80Application.class, args);
    }
}
