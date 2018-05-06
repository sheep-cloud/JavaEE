package cn.colg.bean;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * Person 实体
 * 
 * <pre>
 * '@Value("Jack")'：    注入值
 *  1. 基本数值
 *  2. 可以写SpEL；#{}
 *  3. 可以写${}；取出配置文件【properties】中的值（在运行环境变量里面的值）
 * </pre>
 *
 * @author colg
 */
@Slf4j
@ToString
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Value("Jack")
    private String name;
    @Value("#{20-2}")
    private Integer age;
    
    @Value("${person.nickName}")
    private String nickName;

    public Person() {
        log.info("Person() >>  : {}", "Person 构造方法");
    }

}
