package cn.colg.rbac.service.impl;

import static cn.colg.rbac.util.CheckUtil.checkFalse;
import static cn.colg.rbac.util.CheckUtil.checkTrue;
import static cn.colg.rbac.util.ValidUtil.isGeneralWithChinese;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.colg.rbac.core.BaseServiceImpl;
import cn.colg.rbac.dto.PermissionDto;
import cn.colg.rbac.entity.Permission;
import cn.colg.rbac.entity.User;
import cn.colg.rbac.service.PermissionService;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import tk.mybatis.mapper.entity.Example;

/**
 * 许可ServiceImpl
 *
 * @author colg
 */
@Service
public class PermissionServiceImpl extends BaseServiceImpl implements PermissionService {

    @Override
    public PermissionDto queryRootPermission() {
        return permissionMapper.queryRootPermission();
    }

    @Override
    public List<PermissionDto> queryChildPermission(String pid) {
        return permissionMapper.queryChildPermission(pid);
    }

    @Override
    public List<PermissionDto> querAll() {
        return permissionMapper.queryAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insert(Permission permission) {
        checkPermission(permission);

        permission.setId(IdUtil.simpleUUID());
        permissionMapper.insertSelective(permission);
    }

    @Override
    public Permission findById(String id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Permission permission) {
        checkPermission(permission);

        permissionMapper.updateByPrimaryKeySelective(permission);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delPermission(String id) {
        permissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<String> queryPermissionIdsByRoleId(String roleId) {
        return permissionMapper.queryPermissionIdsByRoleId(roleId);
    }

    @Override
    public List<PermissionDto> queryPermissionsByUser(User dbUser) {
        return permissionMapper.queryPermissionsByUser(dbUser);
    }

    /**
     * 校验许可信息
     *
     * @param permission
     */
    private void checkPermission(Permission permission) {
        checkTrue(isGeneralWithChinese(permission.getName()), "许可名称格式输入错误");

        checkFalse(existsName(permission), "许可名称已存在");
    }

    /**
     * 判断许可名称是否存在
     *
     * @param permission
     */
    private boolean existsName(Permission permission) {
        Example example = new Example(Permission.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name", permission.getName());

        String id = permission.getId();
        if (StrUtil.isNotBlank(id)) {
            criteria.andNotEqualTo("id", id);
        }

        return permissionMapper.selectCountByExample(example) > 0;
    }

}
