package cn.colg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 自定义bean
 * 
 * <pre>
 * boot --> spring  |   applicationContext.xml --> @Configuration  |    ConfigBean --> applicationContext.xml
 * </pre>
 *
 * @author colg
 */
@Configuration
public class ConfigBean {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
