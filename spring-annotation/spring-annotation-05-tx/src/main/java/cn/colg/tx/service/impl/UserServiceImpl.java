package cn.colg.tx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.colg.tx.bean.User;
import cn.colg.tx.dao.UserDao;
import cn.colg.tx.service.UserService;
import cn.hutool.core.lang.Console;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 *
 * @author colg
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public User insertUser(User user) {
        User insertUser = userDao.insert(user);
        // userDao.other()...
        log.info("UserServiceImpl.insertUser() >> 插入完成");
        Console.log(1 / 0);
        return insertUser;
    }

}
