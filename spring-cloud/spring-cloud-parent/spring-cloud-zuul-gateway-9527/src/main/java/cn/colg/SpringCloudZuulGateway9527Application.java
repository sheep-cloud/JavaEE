package cn.colg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Spring Boot 启动类
 * 
 * <pre>
 * '@EnableZuulProxy'：  启动路由网关代理；@EnableZuulServer的增强版
 * 
 * </pre>
 * 
 * @author colg
 */
@EnableZuulProxy
@SpringBootApplication
public class SpringCloudZuulGateway9527Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudZuulGateway9527Application.class, args);
    }
}
