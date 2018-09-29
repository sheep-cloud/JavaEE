package cn.colg.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 员工entity
 *
 * @author colg
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {

    /** id */
    private Integer id;

    /** 名称 */
    private String lastName;

    /** 邮箱 */
    private String email;

    /** 性别 - 1 male, 0 female */
    private Integer gender;

    /** 生日 */
    private Date birth;

    /** 部门 */
    private Department department;

}
