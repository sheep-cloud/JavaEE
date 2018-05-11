package cn.colg.entity;

import java.io.Serializable;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * - @mbg.generated
 *
 * @author colg
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Table(name = "tabple_emp")
public class TabpleEmp implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "emp_id")
    private Integer empId;

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

    /**
     * 获取主键
     *
     * @return emp_id - 主键
     */
    public Integer getEmpId() {
        return empId;
    }

    /**
     * 设置主键
     *
     * @param empId 主键
     */
    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    /**
     * 获取员工姓名
     *
     * @return emp_name - 员工姓名
     */
    public String getEmpName() {
        return empName;
    }

    /**
     * 设置员工姓名
     *
     * @param empName 员工姓名
     */
    public void setEmpName(String empName) {
        this.empName = empName;
    }

    /**
     * 获取员工工资
     *
     * @return emp_salary - 员工工资
     */
    public Double getEmpSalary() {
        return empSalary;
    }

    /**
     * 设置员工工资
     *
     * @param empSalary 员工工资
     */
    public void setEmpSalary(Double empSalary) {
        this.empSalary = empSalary;
    }

    /**
     * 获取员工年龄
     *
     * @return emp_age - 员工年龄
     */
    public Integer getEmpAge() {
        return empAge;
    }

    /**
     * 设置员工年龄
     *
     * @param empAge 员工年龄
     */
    public void setEmpAge(Integer empAge) {
        this.empAge = empAge;
    }
}