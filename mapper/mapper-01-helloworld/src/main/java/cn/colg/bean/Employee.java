package cn.colg.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.colg.core.BaseEntity;
import cn.colg.core.UuidGenId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import tk.mybatis.mapper.annotation.KeySql;

/**
 * 员工实体
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
    @KeySql(genId = UuidGenId.class)
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

    /// ----------------------------------------------------------------------------------------------------

    public Employee(String empName, Double empSalary) {
        this.empName = empName;
        this.empSalary = empSalary;
    }

    public Employee(String empName, Double empSalary, Integer empAge) {
        this.empName = empName;
        this.empSalary = empSalary;
        this.empAge = empAge;
    }

}