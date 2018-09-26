package cn.colg.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 使用SLF4j
 *
 * @author colg
 */
public class HelloWorld {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(HelloWorld.class);
        logger.info("Hello World");
    }

}