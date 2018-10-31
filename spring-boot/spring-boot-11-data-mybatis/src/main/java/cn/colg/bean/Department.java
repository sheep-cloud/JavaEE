package cn.colg.bean;

import java.io.Serializable;

import cn.colg.core.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Department
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
}
