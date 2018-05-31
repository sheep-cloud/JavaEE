package cn.colg.rbac.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.colg.rbac.dao.UserMapper;
import cn.colg.rbac.entity.User;
import cn.colg.rbac.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUser(User user) {
        return userMapper.selectOne(user);
    }

}
