package cn.colg.listener;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * ApplicationRunner
 *
 * @author colg
 */
@Slf4j
@Component
public class HelloApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("HelloApplicationRunner.run() >> args : {}", args);
    }

}
