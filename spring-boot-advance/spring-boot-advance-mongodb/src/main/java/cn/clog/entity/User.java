package cn.clog.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 
 *
 * @author colg
 */
@Getter
@Setter
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private Integer age;
    private String gender;

    @Override
    public String toString() {
        return "{\"name\":\"" + name + "\",\"age\":\"" + age + "\",\"gender\":\"" + gender + "\"}";
    }

}
