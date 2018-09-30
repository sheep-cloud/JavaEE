package cn.colg.rbac.dao;

import cn.colg.rbac.entity.Role;
import org.apache.ibatis.annotations.Param;

/**
 * - @mbg.generated
 *
 * @author colg
 */
public interface RoleMapper extends tk.mybatis.mapper.common.Mapper<Role> {

    /**
     * 根据角色id分配许可
     *
     * @param roleId
     * @param permissionIds
     */
    void insertRolePermission(@Param("roleId") String roleId, @Param("permissionIds") String[] permissionIds);

    /**
     * 根据角色id删除所有许可
     *
     * @param roleId
     */
    void deleteRolePermission(@Param("roleId") String roleId);
}