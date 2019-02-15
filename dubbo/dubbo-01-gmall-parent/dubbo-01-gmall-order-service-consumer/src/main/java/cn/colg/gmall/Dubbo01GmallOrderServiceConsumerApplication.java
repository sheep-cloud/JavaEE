package cn.colg.gmall;

import static cn.colg.util.ResultVoUtil.success;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.colg.gmall.model.UserAddress;
import cn.colg.gmall.service.OrderService;
import cn.colg.vo.ResultVo;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * Dubbo 服务发布; 启动类
 *
 * @author colg
 */
@Slf4j
public class Dubbo01GmallOrderServiceConsumerApplication {

    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("consumer.xml");
        applicationContext.start();
        log.info("Dubbo Publish [dubbo-01-gmall-order-service-consumer]: {}", DateUtil.now());

        OrderService orderService = applicationContext.getBean(OrderService.class);
        String userId = "1";
        log.info("userId: {}", userId);
        List<UserAddress> list = orderService.initOrder(userId);
        ResultVo resultVo = success(list);
        log.info("resultVo: {}", resultVo);

        synchronized (Dubbo01GmallOrderServiceConsumerApplication.class) {
            Dubbo01GmallOrderServiceConsumerApplication.class.wait();
            applicationContext.close();
        }
    }
}
