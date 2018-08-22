package cn.colg.entity;

import cn.colg.core.BaseEntity;
import java.io.Serializable;
import javax.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * JavaBean <br>
 * 
 * 定义JavaBean中成员变量时所使用的类型；
 * 
 * - @mbg.generated
 *
 * @author colg
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "table_emp")
public class Employee extends BaseEntity implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "emp_id")
    private String empId;

    /**
     * 员工姓名
     */
    @Column(name = "emp_name")
    private String empName;

    /**
     * 员工工资
     */
    @Column(name = "emp_salary")
    private Double empSalary;

    /**
     * 员工年龄
     */
    @Column(name = "emp_age")
    private Integer empAge;

    private static final long serialVersionUID = 1L;

}