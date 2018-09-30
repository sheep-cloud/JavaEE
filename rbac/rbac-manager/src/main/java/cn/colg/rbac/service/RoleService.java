package cn.colg.rbac.service;

import cn.colg.rbac.entity.Role;

import java.util.List;

/**
 * 角色Service
 *
 * @author colg
 */
public interface RoleService {
    /**
     * 根据角色查询
     *
     * @param role
     * @return
     */
    Role findRole(Role role);

    /**
     * 分页查询角色列表
     *
     * @param pageNum
     * @param pageSize
     * @param name 角色名称
     * @return
     */
    List<Role> queryRole(Integer pageNum, Integer pageSize, String name);

    /**
     * 新增角色
     *
     * @param role
     */
    void insertRole(Role role);

    /**
     * 根据id查询角色
     *
     * @param id
     * @return
     */
    Role findById(String id);

    /**
     * 修改角色
     *
     * @param role
     */
    void updateRole(Role role);

    /**
     * 根据id删除角色
     *
     * @param id
     */
    void delRole(String id);

    /**
     * 批量删除角色
     *
     * @param ids
     */
    void delRoles(String[] ids);

    /**
     * 查询所有角色
     *
     * @return
     */
    List<Role> selectAll();

    /**
     * 为角色分配许可
     *
     * @param roleId
     * @param permissionIds
     */
    void insertRolePermission(String roleId, String[] permissionIds);
}
