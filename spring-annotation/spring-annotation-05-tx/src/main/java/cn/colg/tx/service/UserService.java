package cn.colg.tx.service;

import cn.colg.tx.bean.User;

/**
 * 
 *
 * @author colg
 */
public interface UserService {

    /**
     * 添加用户
     *
     * @param user
     * @return
     * @author colg
     */
    User insertUser(User user);
}
