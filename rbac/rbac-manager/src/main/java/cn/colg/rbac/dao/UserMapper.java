package cn.colg.rbac.dao;

import cn.colg.rbac.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * - @mbg.generated
 *
 * @author colg
 */
public interface UserMapper extends tk.mybatis.mapper.common.Mapper<User> {

    /**
     * 批量删除用户
     *
     * @param ids
     */
    void delUsers(@Param("ids") String[] ids);

    /**
     * 分配角色数据（添加关系表）
     *
     * @param userId
     * @param unAssignRoleIds
     */
    void insertUserRoles(@Param("userId") String userId, @Param("unAssignRoleIds") String[] unAssignRoleIds);

    /**
     * 删除角色数据（删除关系表）
     *
     * @param userId
     * @param assignRoleIds
     */
    void deleteUserRoles(@Param("userId") String userId, @Param("assignRoleIds") String[] assignRoleIds);

    /**
     * 根据用户id查询角色id列表
     *
     * @param id
     * @return
     */
    List<String> selectRoleIdsByUserId(@Param("id") String id);

}