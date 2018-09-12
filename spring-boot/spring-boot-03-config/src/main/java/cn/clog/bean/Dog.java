package cn.clog.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * Dog 实体
 *
 * @author colg
 */
@Data
public class Dog implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private Integer age;

}
