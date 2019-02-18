package cn.colg.bean;

import java.io.Serializable;

import cn.colg.core.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * Person实体
 *
 * @author colg
 */
@Slf4j
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class Person extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private Integer age;

    public Person() {
        log.info("Person() >>  : {}", "Person 构造方法");
    }

    public void init() {
        log.info("init() >>  : {}", "Person init 初始化方法");
    }

    public void destroy() {
        log.info("destroy() >>  : {}", "Person destroy 销毁方法");
    }
}
