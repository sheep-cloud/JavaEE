package cn.colg.entity;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import cn.colg.core.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 部门Entiry
 *
 * @author colg
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class Department extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Integer id;
    /** 部门名称 */
    private String departmentName;

    /** 员工集合；一对多 */
    @JSONField(ordinal = 1)
    private List<Employee> emps;
}
