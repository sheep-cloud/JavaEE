package cn.colg.gmall.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;

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

    /*
     * colg  [Dubbo 配置]
     *  check = false                           启动时关闭检查
     *  url = "dubbo://127.0.0.1:20880"         Dubbo 直连提供者
     */
    @Reference(version = "1.0")
    private UserService userService;

    @Override
    public List<UserAddress> initOrder(String userId) {
        return userService.selectUserAddressList(userId);
    }

}
