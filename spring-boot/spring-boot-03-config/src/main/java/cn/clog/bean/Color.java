package cn.clog.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Color 实体 '@PropertySource' 加载指定的配置文件
 *
 * @author colg
 */
@ConfigurationProperties(prefix = "color")
@PropertySource(value = {"classpath:color.properties"})
@Component
@Data
public class Color implements Serializable {

    private static final long serialVersionUID = 1L;

    private String red;
    private String blue;
    private Date createDate;

    private Map<String, Object> maps;
    private List<String> lists;
}
