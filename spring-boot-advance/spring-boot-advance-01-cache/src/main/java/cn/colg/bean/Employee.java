package cn.colg.bean;

import java.io.Serializable;

import cn.colg.core.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Empolyee
 *
 * @author colg
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class Employee extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Integer id;
    /** 姓名 */
    private String lastName;
    /** 邮箱 */
    private String email;
    /** 性别（0：男，1：女） */
    private Integer gender;
    /** 部门ID */
    private Integer dId;

    public Employee(String lastName, String email, Integer gender, Integer dId) {
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.dId = dId;
    }

}
