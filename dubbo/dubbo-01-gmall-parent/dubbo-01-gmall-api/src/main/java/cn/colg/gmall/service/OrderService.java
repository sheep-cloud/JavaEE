package cn.colg.gmall.service;

import java.util.List;

import cn.colg.gmall.model.UserAddress;

/**
 * 订单Service
 *
 * @author colg
 */
public interface OrderService {

    /**
     * 根据用户id初始化订单
     *
     * @param userId
     * @author colg
     */
    List<UserAddress> initOrder(String userId);
}
