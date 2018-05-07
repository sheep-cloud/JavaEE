package cn.clog.bean;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Taxi 实体
 *
 * @author colg
 */
@ConfigurationProperties(prefix = "taxi")
@Component
@Data
public class Taxi implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String plateNumber;
    private Integer age;

    private Dog dog;
}
