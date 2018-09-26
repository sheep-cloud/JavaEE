package cn.colg.bean;

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
     * colg  [xml配置]
     *  <bean id="cat" class="cn.colg.bean.Cat">
     *      <property name="lastName" value="black" />
     *      <property name="age" value="3" />
     *      <property name="birth" value="2018/03/15" />
     *  </bean>
     */
    
    private static final long serialVersionUID = 1L;

    @Value("${cat.lastName}")
    private String lastName;
    @Value("${cat.age}")
    private Integer age;
    @Value("${cat.birth}")
    private Date birth;
}
