package cn.clog.bean;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Cat 实体 '@Value' 获取值
 *
 * @author colg
 */
@Component
@Data
public class Cat implements Serializable {
    
    /*
     * colg  xml配置
     * <bean>
     *  <property name="lastName" value="?" />
     * </bean>
     */

    private static final long serialVersionUID = 1L;

    @Value("${cat.lastName}")
    private String lastName;
    @Value("${cat.age}")
    private Integer age;
    @Value("${cat.birth}")
    private Date birth;
}
