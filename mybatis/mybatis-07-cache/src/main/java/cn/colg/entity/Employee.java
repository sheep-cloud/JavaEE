package cn.colg.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 员工Entity
 *
 * @author colg
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Integer id;
    /** 姓名 */
    private String lastName;
    /** 性别（0：男，1：女） */
    private String gender;
    /** 邮箱 */
    private String email;

    /** 部门；多对一 */
    private Department dept;

}
