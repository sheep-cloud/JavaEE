package cn.colg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

import cn.rule.MySelfRule;


/**
 * Spirng Boot 启动类
 * 
 * <pre>
 * '@RibbonClient(name = "SPRING-CLOUD-DEPT", configuration = MySelfRule.class)'
 *  在启动该微服务的时候就去加载自定义的Ribbon配置类，从而使配置生效
 *  MySelfRule.class 不能在 @ComponentScan 包以及子包下，否则将全局使用自定义规则
 * </pre>
 *
 * @author colg
 */
@RibbonClient(name = "SPRING-CLOUD-DEPT", configuration = MySelfRule.class)
@EnableEurekaClient
@SpringBootApplication
public class SpringCloudConsumerDept80Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConsumerDept80Application.class, args);
    }
}
