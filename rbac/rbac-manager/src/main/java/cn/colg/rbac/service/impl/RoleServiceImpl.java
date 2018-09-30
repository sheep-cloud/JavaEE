package cn.colg.rbac.service.impl;

import static cn.colg.rbac.util.CheckUtil.checkFalse;
import static cn.colg.rbac.util.CheckUtil.checkTrue;
import static cn.colg.rbac.util.ValidUtil.isGeneralWithChinese;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import cn.colg.rbac.core.BaseServiceImpl;
import cn.colg.rbac.entity.Role;
import cn.colg.rbac.service.RoleService;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import tk.mybatis.mapper.entity.Example;

/**
 * 角色ServiceImpl
 *
 * @author colg
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl implements RoleService {

    @Override
    public Role findRole(Role role) {
        return roleMapper.selectOne(role);
    }

    @Override
    public List<Role> queryRole(Integer pageNum, Integer pageSize, String name) {
        PageHelper.startPage(pageNum, pageSize, "update_time DESC");
        Example example = new Example(Role.class);
        if (StrUtil.isNotBlank(name)) {
            example.createCriteria().andLike("name", "%" + name + "%");
        }
        return roleMapper.selectByExample(example);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertRole(Role role) {
        // 数据校验
        checkRole(role);

        role.setId(IdUtil.simpleUUID());
        roleMapper.insertSelective(role);
    }

    @Override
    public Role findById(String id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateRole(Role role) {
        checkRole(role);

        roleMapper.updateByPrimaryKeySelective(role);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delRole(String id) {
        roleMapper.deleteByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delRoles(String[] ids) {
        Example example = new Example(Role.class);
        example.createCriteria().andIn("id", CollUtil.newArrayList(ids));
        roleMapper.deleteByExample(example);
    }

    @Override
    public List<Role> selectAll() {
        return roleMapper.selectAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertRolePermission(String roleId, String[] permissionIds) {
        // 先清空所有许可（负向授权）
        roleMapper.deleteRolePermission(roleId);
        // 再重新设置许可（正向授权）
        roleMapper.insertRolePermission(roleId, permissionIds);
    }

    /**
     * 校验角色
     *
     * @param role
     */
    private void checkRole(Role role) {
        checkTrue(isGeneralWithChinese(role.getName()), "角色名称格式输入错误");

        checkFalse(existsLoginacct(role), "角色名称已存在");
    }

    /**
     * 判断角色名是否存在
     *
     * @param role
     * @return
     */
    private boolean existsLoginacct(Role role) {
        Example example = new Example(Role.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name", role.getName());

        String id = role.getId();
        if (StrUtil.isNotBlank(id)) {
            criteria.andNotEqualTo("id", id);
        }

        return roleMapper.selectCountByExample(example) > 0;
    }
}
