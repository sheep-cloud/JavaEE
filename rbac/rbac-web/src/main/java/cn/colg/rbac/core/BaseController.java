package cn.colg.rbac.core;

import cn.colg.rbac.service.PermissionService;
import cn.colg.rbac.service.RoleService;
import cn.colg.rbac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * controller 基类
 *
 * @author colg
 */
public abstract class BaseController {

    @Autowired
    protected UserService userService;
    @Autowired
    protected RoleService roleService;
    @Autowired
    protected PermissionService permissionService;
}
