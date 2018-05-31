package cn.colg.rbac.service;

import cn.colg.rbac.entity.User;

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

}
