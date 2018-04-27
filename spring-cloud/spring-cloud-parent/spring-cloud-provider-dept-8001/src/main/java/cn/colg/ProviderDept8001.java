package cn.colg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot 启动类
 *
 * @author colg
 */
@MapperScan("cn.colg.dao")
@SpringBootApplication
public class ProviderDept8001 {

    public static void main(String[] args) {
        SpringApplication.run(ProviderDept8001.class, args);
    }

}