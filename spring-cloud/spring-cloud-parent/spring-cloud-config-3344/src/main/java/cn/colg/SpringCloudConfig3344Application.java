package cn.colg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Spring Boot 启动类
 * 
 * <pre>
 * '@EnableConfigServer'：   开启 分布式配置服务中心
 * </pre>
 * 
 * @author colg
 */
@EnableConfigServer
@SpringBootApplication
public class SpringCloudConfig3344Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConfig3344Application.class, args);
    }
}
