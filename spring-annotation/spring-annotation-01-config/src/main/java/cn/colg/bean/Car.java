package cn.colg.bean;

import java.io.Serializable;

import lombok.extern.slf4j.Slf4j;

/**
 * Car 实体
 *
 * @author colg
 */
@Slf4j
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    public Car() {
        log.info("Car() >>  : {}", "Car 构造方法");
    }

    public void init() {
        log.info("init() >>  : {}", "Car init 初始化方法");
    }

    public void destroy() {
        log.info("destroy() >>  : {}", "Car destroy 销毁方法");
    }
}
