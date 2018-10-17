package cn.colg.tx.bean;

import java.io.Serializable;

import cn.colg.core.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * User 实体类
 *
 * @author colg
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String username;
    private Integer age;

}
