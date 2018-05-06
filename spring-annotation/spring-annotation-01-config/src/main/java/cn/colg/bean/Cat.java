package cn.colg.bean;

import java.io.Serializable;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import lombok.extern.slf4j.Slf4j;

/**
 * 猫 实体
 *
 * @author colg
 */
@Slf4j
public class Cat implements Serializable, InitializingBean, DisposableBean {

    private static final long serialVersionUID = 1L;

    public Cat() {
        log.info("Cat() >>  : {}", "Cat 构造方法");
    }

    @Override
    public void destroy() throws Exception {
        log.info("destroy() >>  : {}", "Cat destroy 销毁方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("afterPropertiesSet() >>  : {}", "Cat afterPropertiesSet 赋值完成后，初始化方法");
    }

}
