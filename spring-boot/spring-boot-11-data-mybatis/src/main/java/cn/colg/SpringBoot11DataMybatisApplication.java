package cn.colg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Sprign Boot 启动类
 * 
 * <pre>
 * `@MapperScan`: 批量扫描操作数据库的mapper
 * </pre>
 *
 * @author colg
 */
@MapperScan("cn.colg.mapper")
@SpringBootApplication
public class SpringBoot11DataMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot11DataMybatisApplication.class, args);
    }
}
