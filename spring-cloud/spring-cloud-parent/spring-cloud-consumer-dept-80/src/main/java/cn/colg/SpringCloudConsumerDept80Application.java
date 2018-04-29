package cn.colg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Spirng Boot 启动类
 *
 * @author colg
 */
@EnableEurekaClient
@SpringBootApplication
public class SpringCloudConsumerDept80Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConsumerDept80Application.class, args);
    }
}
