package cn.colg.listener;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import lombok.extern.slf4j.Slf4j;

/**
 * ApplicationContextInitializer
 *
 * @author colg
 */
@Slf4j
public class HelloApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        log.info("HelloApplicationContextInitializer.initialize() >> applicationContext : {}", applicationContext);
    }

}
