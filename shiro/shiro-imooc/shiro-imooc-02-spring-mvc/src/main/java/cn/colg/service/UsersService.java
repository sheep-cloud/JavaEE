package cn.colg.service;

import java.util.List;
import java.util.Set;

import cn.colg.model.Users;

/**
 * 
 *
 * @author colg
 */
public interface UsersService {

    /**
     * 查询所有用户
     *
     * @return
     * @author colg
     */
    List<Users> selectAll();

    /**
     * 根据用户名查询用户密码（有效的用户）
     *
     * @param username
     * @return
     * @author colg
     */
    String findPasswordBuUserName(String username);

    /**
     * 根据用户名查询角色列表
     *
     * @param username
     * @return
     * @author colg
     */
    Set<String> selectRolesByUserName(String username);

    /**
     * 根据角色查询角色权限列表
     *
     * @param roles
     * @return
     * @author colg
     */
    Set<String> selectPermissionsRoles(Set<String> roles);

}
