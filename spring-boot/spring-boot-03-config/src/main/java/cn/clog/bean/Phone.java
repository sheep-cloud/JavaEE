package cn.clog.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Phone 实体 '@ImportResource' 导入Spring的配置文件
 *
 * @author colg
 */
@ImportResource(locations = {"classpath:beans.xml"})
@Component
@Data
public class Phone implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String color;
    private Integer age;
    
    private Map<String, Object> maps;
    private List<String> lists;
}
