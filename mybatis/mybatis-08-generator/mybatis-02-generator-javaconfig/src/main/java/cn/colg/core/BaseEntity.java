package cn.colg.core;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Entity的基类，建议所有实体类都继承
 *
 * @author colg
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public abstract class BaseEntity {

    /** 主键：UUID */
    private String id;

    /** 创建时间："YYYY-mm-dd HH:mm:ss" */
    private Date createDate;
    /** 修改时间："YYYY-mm-dd HH:mm:ss" */
    private Date updateDate;
}
