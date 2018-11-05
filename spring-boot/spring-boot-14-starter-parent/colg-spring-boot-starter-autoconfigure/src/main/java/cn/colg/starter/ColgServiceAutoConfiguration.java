package cn.colg.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Auto-Configuration for Colg.
 *
 * @author colg
 */
@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties(ColgProperties.class)
public class ColgServiceAutoConfiguration {

    @Autowired
    private ColgProperties colgProperties;

    @Bean
    public ColgService colgService() {
        ColgService service = new ColgService();
        service.setColgProperties(colgProperties);
        return service;
    }
}
