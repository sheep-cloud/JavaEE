package cn.colg.logging;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.colg.SpringBoot06LoggingApplicationTests;

/**
 * Spring Boot 日志默认配置 测试
 *
 * @author colg
 */
public class LoggingTest extends SpringBoot06LoggingApplicationTests {

    /** 日志记录器 */
    Logger logger = LoggerFactory.getLogger(LoggingTest.class);

    @Test
    public void loggerTest() {
        /*
         * colg  [日志级别]
         *  由低到高: trace < debug < info < warn < error
         *  可以调整输出的日志级别；日志就会只在这个级别以后的高级别生效
         */
        logger.trace("这是trace日志...");
        logger.debug("这是debug日志...");
        // Spring Boot 默认使用的是info级别的
        logger.info("这是info日志...");
        logger.warn("这是warn日志...");
        logger.error("这是error日志...");
    }

}
