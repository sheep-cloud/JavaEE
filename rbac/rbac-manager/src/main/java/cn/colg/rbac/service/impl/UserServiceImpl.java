package cn.colg.rbac.service.impl;

import static cn.colg.rbac.util.CheckUtil.checkFalse;
import static cn.colg.rbac.util.CheckUtil.checkTrue;
import static cn.colg.rbac.util.ValidUtil.isAccount;
import static cn.colg.rbac.util.ValidUtil.isEmail;
import static cn.colg.rbac.util.ValidUtil.isGeneralWithChinese;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import cn.colg.rbac.core.BaseServiceImpl;
import cn.colg.rbac.entity.User;
import cn.colg.rbac.service.UserService;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import tk.mybatis.mapper.entity.Example;

/**
 * 用户ServiceImpl
 *
 * @author colg
 */
@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {

    @Override
    public User findUser(User user) {
        return userMapper.selectOne(user);
    }

    @Override
    public List<User> queryUser(Integer pageNum, Integer pageSize, String loginacct) {
        PageHelper.startPage(pageNum, pageSize, "update_time DESC");
        Example example = new Example(User.class);
        if (StrUtil.isNotBlank(loginacct)) {
            example.createCriteria().andLike("loginacct", "%" + loginacct + "%");
        }
        return userMapper.selectByExample(example);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertUser(User user) {
        // 数据校验
        checkUser(user);

        user.setId(IdUtil.simpleUUID())
            .setPassword("123456");
        userMapper.insertSelective(user);
    }

    @Override
    public User findById(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateUser(User user) {
        checkUser(user);

        userMapper.updateByPrimaryKeySelective(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delUser(String id) {
        userMapper.deleteByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delUsers(String[] ids) {
        userMapper.delUsers(ids);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertUserRoles(String userId, String[] unAssignRoleIds) {
        userMapper.insertUserRoles(userId, unAssignRoleIds);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteUserRoles(String userId, String[] assignRoleIds) {
        userMapper.deleteUserRoles(userId, assignRoleIds);
    }

    @Override
    public List<String> selectRoleIdsByUserId(String id) {
        return userMapper.selectRoleIdsByUserId(id);
    }

    /**
     * 校验用户
     *
     * @param user
     */
    private void checkUser(User user) {
        String loginacct = user.getLoginacct();
        String username = user.getUsername();
        String email = user.getEmail();

        checkTrue(isAccount(loginacct), "登陆帐号格式输入错误");
        checkTrue(isEmail(email), "请输入合法的邮箱地址, 格式为： xxxx@xxxx.com");
        checkTrue(isGeneralWithChinese(username), "用户名称格式输入错误");

        checkFalse(existsLoginacct(user), "登录帐号已存在");
    }

    /**
     * 判断用户名是否存在
     *
     * @param user
     * @return
     */
    private boolean existsLoginacct(User user) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("loginacct", user.getLoginacct());

        String id = user.getId();
        if (StrUtil.isNotBlank(id)) {
            criteria.andNotEqualTo("id", id);
        }

        return userMapper.selectCountByExample(example) > 0;
    }

}
