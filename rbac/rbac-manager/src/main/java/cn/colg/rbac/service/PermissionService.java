package cn.colg.rbac.service;

import cn.colg.rbac.dto.PermissionDto;
import cn.colg.rbac.entity.Permission;
import cn.colg.rbac.entity.User;

import java.util.List;

/**
 * 许可Service
 *
 * @author colg
 */
public interface PermissionService {

    /**
     * 查询属性结构顶级节点
     *
     * @return
     */
    PermissionDto queryRootPermission();

    /**
     * 根据父级id获取结构
     *
     * @param pid
     * @return
     */
    List<PermissionDto> queryChildPermission(String pid);

    /**
     * 查询所有节点
     *
     * @return
     */
    List<PermissionDto> querAll();

    /**
     * 添加节点
     *
     * @param permission
     */
    void insert(Permission permission);

    /**
     * 根据id查询节点
     *
     * @param id
     * @return
     */
    Permission findById(String id);

    /**
     * 修改节点
     *
     * @param permission
     */
    void update(Permission permission);

    /**
     * 删除节点
     *
     * @param id
     */
    void delPermission(String id);

    /**
     * 根据角色id查询许可信息列表
     *
     * @param roleId
     * @return
     */
    List<String> queryPermissionIdsByRoleId(String roleId);

    /**
     * 根据用户查询许可列表
     *
     * @param dbUser
     * @return
     */
    List<PermissionDto> queryPermissionsByUser(User dbUser);
}
