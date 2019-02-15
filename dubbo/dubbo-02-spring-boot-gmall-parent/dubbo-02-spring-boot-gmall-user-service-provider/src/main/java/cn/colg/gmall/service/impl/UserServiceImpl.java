package cn.colg.gmall.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.dubbo.config.annotation.Service;

import cn.colg.gmall.model.UserAddress;
import cn.colg.gmall.service.UserService;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户ServiceImpl
 * 
 * <pre>
 *  `@Service(version = "1.0", timeout = 60000)`: 暴露服务
 * </pre>
 *
 * @author colg
 */
@Slf4j
@Service(version = "1.0", timeout = 60000)
public class UserServiceImpl implements UserService {
    
    @Override
    public List<UserAddress> selectUserAddressList(String userId) {
        List<UserAddress> list = CollUtil.newArrayList(
            new UserAddress(1, "北京市昌平区宏福科技园综合楼3层", "1", "李老师", "010-56253825", "Y"),
            new UserAddress(2, "深圳市宝安区西部硅谷大厦B座3层（深圳分校）", "1", "王老师", "010-56253825", "N"),
            new UserAddress(3, "广州市天河区科韵北路万科广场A座28层", "2", "Jax", "020-123456", "N")
        );

        // 测试超时
        if (!userId.equals("1")) {
            log.info("ThreadUtil.sleep(5000)");
            ThreadUtil.sleep(5000);
        }
        
        // 过滤
        return list.stream()
                   .filter(t -> t.getUserId().equals(userId))
                   .collect(Collectors.toList());
    }

}
