package cn.colg.rbac.core;

import cn.colg.rbac.dao.PermissionMapper;
import cn.colg.rbac.dao.RoleMapper;
import cn.colg.rbac.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * service 基类
 *
 * @author colg
 */
public abstract class BaseServiceImpl {

    @Autowired
    protected UserMapper userMapper;
    @Autowired
    protected RoleMapper roleMapper;
    @Autowired
    protected PermissionMapper permissionMapper;
}
