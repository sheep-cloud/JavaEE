package cn.colg.model;

import cn.colg.core.BaseEntity;
import java.io.Serializable;
import javax.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * - @mbg.generated
 *
 * @author colg
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "user_roles")
public class UserRoles extends BaseEntity implements Serializable {
    /**
     * 用户名
     */
    @Column(name = "username")
    private String username;

    /**
     * 角色名
     */
    @Column(name = "role_name")
    private String roleName;

    private static final long serialVersionUID = 1L;

}