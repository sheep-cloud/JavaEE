package cn.colg.entity;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 部门Entiry
 *
 * @author colg
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Integer id;
    /** 部门名称 */
    private String departmentName;

    /** 员工集合；一对多 */
    private List<Employee> emps;
}
