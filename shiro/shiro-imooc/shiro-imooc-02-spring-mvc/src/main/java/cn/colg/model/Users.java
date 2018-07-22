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
@Table(name = "users")
public class Users extends BaseEntity implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    private String id;

    /**
     * 用户名
     */
    @Column(name = "username")
    private String username;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 是否锁定（0:启动，1:锁定）
     */
    @Column(name = "locked")
    private String locked;

    private static final long serialVersionUID = 1L;

}