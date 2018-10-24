package cn.colg.entity;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

import cn.colg.core.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 员工Entity
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
    /** 性别（0：男，1：女） */
    private String gender;
    /** 邮箱 */
    private String email;

    /** 部门；多对一 */
    @JSONField(ordinal = 1)
    private Department dept;
}
