package cn.colg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * Spirng Boot 启动类
 * 
 * <pre>
 * `@EnableFeignClients("cn.colg.web")`：    开启Feign功能，接口调用
 * </pre>
 * 
 * @author colg
 */
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class SpringCloudConsumerDept80FeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConsumerDept80FeignApplication.class, args);
    }
}
