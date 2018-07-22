package cn.colg.core;

import org.springframework.beans.factory.annotation.Autowired;

import cn.colg.service.UsersService;

/**
 * controller 基类
 *
 * @author colg
 */
public abstract class BaseController {

    @Autowired
    protected UsersService usersService;
}
