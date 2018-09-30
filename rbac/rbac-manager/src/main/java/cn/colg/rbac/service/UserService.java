package cn.colg.rbac.service;

import cn.colg.rbac.entity.User;

import java.util.List;

/**
 * 用户Service
 *
 * @author colg
 */
public interface UserService {

    /**
     * 根据用户查询
     *
     * @param user
     * @return
     */
    User findUser(User user);

    /**
     * 分页查询用户列表
     *
     * @param pageNum
     * @param pageSize
     * @param loginacct 用户名
     * @return
     */
    List<User> queryUser(Integer pageNum, Integer pageSize, String loginacct);

    /**
     * 新增用户
     *
     * @param user
     */
    void insertUser(User user);

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    User findById(String id);

    /**
     * 修改用户
     *
     * @param user
     */
    void updateUser(User user);

    /**
     * 根据id删除用户
     *
     * @param id
     */
    void delUser(String id);

    /**
     * 批量删除用户
     *
     * @param ids
     */
    void delUsers(String[] ids);

    /**
     * 分配角色数据（添加关系表数据）
     *
     * @param userId
     * @param unAssignRoleIds
     */
    void insertUserRoles(String userId, String[] unAssignRoleIds);

    /**
     * 取消分配角色数据（删除关系表数据）
     *
     * @param userId
     * @param assignRoleIds
     */
    void deleteUserRoles(String userId, String[] assignRoleIds);

    /**
     * 根据用户id获取角色id列表
     *
     * @param id
     * @return
     */
    List<String> selectRoleIdsByUserId(String id);


}
