package cn.colg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot 启动类
 * 
 * <pre>
 * '@SpringBootApplication'：    标注一个主程序类，说明这是一个Spring Boot的主配置类，SpringBoot就应该运行这个类的main方法来启动SpringBoot应用
 * </pre>
 *
 * @author colg
 */
@SpringBootApplication
public class SpringBoot01HelloworldApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot01HelloworldApplication.class, args);
    }
}
