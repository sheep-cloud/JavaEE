package cn.colg.core;

import org.springframework.beans.factory.annotation.Autowired;

import cn.colg.mapper.RolesPermissionsMapper;
import cn.colg.mapper.UserRolesMapper;
import cn.colg.mapper.UsersMapper;

/**
 * serviceImpl 基类
 *
 * @author colg
 */
public abstract class BaseServiceImpl {

    @Autowired
    protected UsersMapper usersMapper;
    @Autowired
    protected UserRolesMapper userRolesMapper;
    @Autowired
    protected RolesPermissionsMapper rolesPermissionsMapper;
}
