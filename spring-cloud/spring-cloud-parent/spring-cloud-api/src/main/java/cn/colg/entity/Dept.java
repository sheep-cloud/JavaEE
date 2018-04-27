package cn.colg.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "部门对象")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class Dept implements Serializable {

    /*
     * Swagger2 语法：
     * 作用在Model上：
     *  1. @ApiModel(description = "部门对象") ：    Model的描述
     *  2. @ApiModelProperty("主键")：                                 属性描述
     *  
     * 作用在Controller上：
     *  1. @Api(tags = {"部门管理"})：                                   Controller的类描述
     *  2. @ApiOperation("添加部门")：                                   Controller的方法描述
     *  3. @ApiImplicitParam(name = "id", value = "部门id", paramType = "Long", required = true)
     *      name：属性；value：属性描述；paramType：属性类型；required：是否必填
     */

    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("主键")
    private Long deptNo;
    /** 部门名称 */
    @ApiModelProperty("部门名称")
    private String deptName;
    /** 来自哪个数据库，因为微服务架构可以一个服务对应一个数据库，同一个信息被存储到不同的数据库 */
    @ApiModelProperty("来自哪个数据库，因为微服务架构可以一个服务对应一个数据库，同一个信息被存储到不同的数据库")
    private String dbSource;

}
