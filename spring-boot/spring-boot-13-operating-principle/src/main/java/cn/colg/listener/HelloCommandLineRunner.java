package cn.colg.listener;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * CommandLineRunner
 *
 * @author colg
 */
@Slf4j
@Component
public class HelloCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("HelloCommandLineRunner.run() >> args : {}", Arrays.asList(args));
    }

}
