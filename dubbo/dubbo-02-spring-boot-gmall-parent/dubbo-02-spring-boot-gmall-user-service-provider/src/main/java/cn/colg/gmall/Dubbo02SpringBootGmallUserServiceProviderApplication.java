package cn.colg.gmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;

/**
 * SpringBoot 启动类
 * 
 * <pre>
 *  `@EnableDubbo`: 开启基于注解的dubbo功能
 * </pre>
 *
 * @author colg
 */
@EnableDubbo
@SpringBootApplication
public class Dubbo02SpringBootGmallUserServiceProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(Dubbo02SpringBootGmallUserServiceProviderApplication.class, args);
    }

}
