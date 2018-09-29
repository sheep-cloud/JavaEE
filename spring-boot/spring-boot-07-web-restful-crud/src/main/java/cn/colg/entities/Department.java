package cn.colg.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 部门entity
 *
 * @author colg
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Department {

    /** id */
    private Integer id;
    
    /** 名称 */
    private String departmentName;

}
