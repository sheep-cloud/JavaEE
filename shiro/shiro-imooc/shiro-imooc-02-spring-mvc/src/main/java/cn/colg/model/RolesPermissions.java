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
@Table(name = "roles_permissions")
public class RolesPermissions extends BaseEntity implements Serializable {
    /**
     * 角色id
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 权限id
     */
    @Column(name = "permission")
    private String permission;

    private static final long serialVersionUID = 1L;

    /**
     * 获取角色id
     *
     * @return role_name - 角色id
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色id
     *
     * @param roleName 角色id
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取权限id
     *
     * @return permission - 权限id
     */
    public String getPermission() {
        return permission;
    }

    /**
     * 设置权限id
     *
     * @param permission 权限id
     */
    public void setPermission(String permission) {
        this.permission = permission;
    }
}