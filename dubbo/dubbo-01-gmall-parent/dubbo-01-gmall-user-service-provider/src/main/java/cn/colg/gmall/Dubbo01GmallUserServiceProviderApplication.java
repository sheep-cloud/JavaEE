package cn.colg.gmall;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * Dubbo 服务发布; 启动类
 *
 * @author colg
 */
@Slf4j
public class Dubbo01GmallUserServiceProviderApplication {

    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("provider.xml");
        applicationContext.start();
        log.info("Dubbo Publish [dubbo-01-gmall-user-service-provider]: {}", DateUtil.now());

        synchronized (Dubbo01GmallUserServiceProviderApplication.class) {
            Dubbo01GmallUserServiceProviderApplication.class.wait();
            applicationContext.close();
        }
    }
}
