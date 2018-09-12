package cn.colg.mapper;

import java.util.Set;

import org.apache.ibatis.annotations.Param;

import cn.colg.model.Users;

/**
 * - @mbg.generated
 *
 * @author colg
 */
public interface UsersMapper extends tk.mybatis.mapper.common.Mapper<Users> {

    /**
     * 根据用户名查询用户密码（有效的用户）
     *
     * @param username
     * @return
     * @author colg
     */
    String findPasswordBuUserName(@Param("username") String username);

    /**
     * 根据用户名查询角色列表
     *
     * @param username
     * @return
     * @author colg
     */
    Set<String> selectRolesByUserName(@Param("username") String username);

    /**
     * 根据角色查询角色权限列表
     *
     * @param roles
     * @return
     * @author colg
     */
    Set<String> selectPermissionsRoles(@Param("roles") Set<String> roles);
}