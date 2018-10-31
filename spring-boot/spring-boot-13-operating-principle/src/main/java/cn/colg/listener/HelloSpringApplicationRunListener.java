package cn.colg.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * SpringApplicationRunListener
 *
 * @author colg
 */
@Slf4j
public class HelloSpringApplicationRunListener implements SpringApplicationRunListener {

    public HelloSpringApplicationRunListener(SpringApplication application, String[] args) {

    }

    @Override
    public void starting() {
        log.info("HelloSpringApplicationRunListener.starting() >> starting : {}", DateUtil.now());
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        Object object = environment.getSystemEnvironment().get("os.name");
        log.info("HelloSpringApplicationRunListener.environmentPrepared() >> environment : {}", object);
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        log.info("HelloSpringApplicationRunListener.contextPrepared() >> context : {}", context.getClass().getName());
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        log.info("HelloSpringApplicationRunListener.contextLoaded() >> context : {}", context.getClass().getName());
    }

    @Override
    public void finished(ConfigurableApplicationContext context, Throwable exception) {
        log.info("HelloSpringApplicationRunListener.finished() >> exception : {}", exception);
    }

}
