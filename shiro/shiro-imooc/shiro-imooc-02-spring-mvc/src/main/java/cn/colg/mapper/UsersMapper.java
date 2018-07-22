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

    String findPasswordBuUserName(@Param("username") String username);

    Set<String> selectRolesByUserName(@Param("username") String username);

    Set<String> selectPermissionsRoles(@Param("roles") Set<String> roles);
}