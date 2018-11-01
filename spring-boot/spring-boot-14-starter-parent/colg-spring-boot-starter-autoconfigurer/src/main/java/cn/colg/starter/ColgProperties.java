package cn.colg.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for Colg.
 *
 * @author colg
 */
@ConfigurationProperties(prefix = ColgProperties.COLG_PREFIX)
public class ColgProperties {

    public static final String COLG_PREFIX = "colg";

    private String prefix;
    private String suffix;
    
    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

}
