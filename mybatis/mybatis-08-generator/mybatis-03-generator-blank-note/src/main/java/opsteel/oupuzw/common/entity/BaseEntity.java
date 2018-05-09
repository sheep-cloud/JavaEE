package opsteel.oupuzw.common.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * entity的基类，建议所有实体类都继承
 *
 * @author colg
 */
@Getter
@Setter
public abstract class BaseEntity implements Serializable{

    /**  */
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Integer id;

}
