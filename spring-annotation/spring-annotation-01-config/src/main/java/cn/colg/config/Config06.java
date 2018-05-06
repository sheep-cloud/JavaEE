package cn.colg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.colg.bean.Car;
import cn.colg.bean.Cat;

/**
 * 配置类；Bean的生命周期
 * 
 * <pre>
 * Bean的生命周期：
 *  bean创建 --> 初始化 --> 销毁的过程
 *  
 * 容器管理bean的生命周期：
 *  可以自定义初始化和销毁方法；容器在bean进行到当前圣经周期的时候来调用我们自定义的初始化和销毁方法；
 *      1. 指定初始化和销毁方法：
 *          指定init-method="" 和 destroy-method=""
 *      2. 通过让bean实现'InitializingBean'（定义初始化逻辑），DisposableBean（定义销毁逻辑）
 * 
 * 构造（对象创建）
 *  单实例：在容器启动的时候创建对象
 *  多实例：在每次获取的时候创建对象
 * 初始化：
 *  对象创建完成，并赋值好，调用初始化方法。。。
 * 销毁：
 *  单实例：容器关闭的时候
 *  多实例：容器不会管理这个bean，容器不会调用销毁方法
 * </pre>
 * 
 * 
 * @author colg
 */
@Configuration
public class Config06 {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Car car() {
        return new Car();
    }

    @Bean
    public Cat cat() {
        return new Cat();
    }
}
