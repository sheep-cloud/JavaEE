package cn.colg.gmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.colg.gmall.model.UserAddress;
import cn.colg.gmall.service.OrderService;
import cn.colg.gmall.service.UserService;

/**
 * 订单ServiceImpl
 *
 * @author colg
 */
@Service
public class OrderServiceImpl implements OrderService {
    
    @Autowired
    private UserService userService;

    @Override
    public List<UserAddress> initOrder(String userId) {
        return userService.selectUserAddressList(userId);
    }

}
