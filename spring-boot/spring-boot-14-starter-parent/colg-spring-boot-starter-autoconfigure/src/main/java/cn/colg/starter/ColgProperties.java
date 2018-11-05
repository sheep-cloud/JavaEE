package cn.colg.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * Configuration properties for Colg.
 *
 * @author colg
 */
@Getter
@Setter
@ConfigurationProperties(prefix = ColgProperties.COLG_PREFIX)
public class ColgProperties {

    public static final String COLG_PREFIX = "colg";

    /** 前缀 */
    private String prefix;
    /** 后缀 */
    private String suffix;

}
