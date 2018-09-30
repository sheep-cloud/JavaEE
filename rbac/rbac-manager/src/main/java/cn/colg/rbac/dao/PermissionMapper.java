package cn.colg.rbac.dao;

import cn.colg.rbac.dto.PermissionDto;
import cn.colg.rbac.entity.Permission;
import cn.colg.rbac.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * - @mbg.generated
 *
 * @author colg
 */
public interface PermissionMapper extends tk.mybatis.mapper.common.Mapper<Permission> {

    /**
     * 查询顶级节点
     *
     * @return
     */
    PermissionDto queryRootPermission();

    /**
     * 根据pid查询节点
     *
     * @param pid
     * @return
     */
    List<PermissionDto> queryChildPermission(@Param("pid") String pid);

    /**
     * 查询所有节点
     *
     * @return
     */
    List<PermissionDto> queryAll();

    /**
     * 根据角色id获取许可列表
     *
     * @param roleId
     * @return
     */
    List<String> queryPermissionIdsByRoleId(@Param("roleId") String roleId);

    /**
     * 根据用户查询许可列表
     *
     * @param dbUser
     * @return
     */
    List<PermissionDto> queryPermissionsByUser(@Param("dbUser") User dbUser);
}