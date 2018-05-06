package cn.clog.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Person 实体
 * 
 * <pre>
 * 将配置文件中配置的每一个属性的值，映射到这个组件中
 *  `@ConfigurationProperties`：  告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定
 *      `(prefix = "person")`：  配置文件中哪个与下面的所有属性进行一一映射
 *      
 *  只有这个组件是容器中的组件，才能使用容器提供的`@ConfigurationProperties`组件
 * </pre>
 *
 * @author colg
 */
@ConfigurationProperties(prefix = "person")
@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    private String lastName;
    private Integer age;
    private Boolean boss;
    private Date birth;

    private Map<String, Object> maps;
    private List<Object> lists;

    private Dog dog;
}
