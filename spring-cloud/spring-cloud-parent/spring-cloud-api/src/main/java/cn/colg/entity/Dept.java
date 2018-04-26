package cn.colg.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 部门Entity </br>
 * 
 * <pre>
 * Dept(Entity) orm     mysql -> Dept(table)    类表关系映射
 * 
 * lombok：
 *  '@NoArgsConstructor'：无参构造
 *  '@AllArgsConstructor'：全参构造
 *  '@Data'：getter/setter方法
 *  '@Accessors(chain = true)'：链式编程
 * </pre>
 * 
 * @author colg
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long deptNo;
    /** 部门名称 */
    private String deptName;
    /** 来自哪个数据库，因为微服务架构可以一个服务对应一个数据库，同一个信息被存储到不同的数据库 */
    private String dbSource;

}
