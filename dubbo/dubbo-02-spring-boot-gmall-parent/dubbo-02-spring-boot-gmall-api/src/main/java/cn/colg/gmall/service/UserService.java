package cn.colg.gmall.service;

import java.util.List;

import cn.colg.gmall.model.UserAddress;


/**
 * 用户Service
 *
 * @author colg
 */
public interface UserService {

    /**
     * 根据用户id获取所有的收货地址
     *
     * @param userId
     * @return
     * @author colg
     */
    List<UserAddress> selectUserAddressList(String userId);
}
