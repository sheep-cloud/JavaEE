package cn.colg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * Spirng Boot 启动类
 * 
 * <pre>
 * `@EnableHystrixDashboard`：   启用 hystrixDashboard 服务监控仪表盘
 * </pre>
 * 
 * @author colg
 */
@EnableHystrixDashboard
@SpringBootApplication
public class SpringCloudConsumerHystrixDashboard9001Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConsumerHystrixDashboard9001Application.class, args);
    }
}
