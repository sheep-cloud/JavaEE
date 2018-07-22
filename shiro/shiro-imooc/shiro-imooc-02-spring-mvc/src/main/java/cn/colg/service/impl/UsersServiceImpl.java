package cn.colg.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import cn.colg.core.BaseServiceImpl;
import cn.colg.model.Users;
import cn.colg.service.UsersService;

/**
 * 
 *
 * @author colg
 */
@Service
public class UsersServiceImpl extends BaseServiceImpl implements UsersService {

    @Override
    public List<Users> selectAll() {
        return usersMapper.selectAll();
    }

    @Override
    public String findPasswordBuUserName(String username) {
        return usersMapper.findPasswordBuUserName(username);
    }

    @Override
    public Set<String> selectRolesByUserName(String username) {
        return usersMapper.selectRolesByUserName(username);
    }

    @Override
    public Set<String> selectPermissionsRoles(Set<String> roles) {
        return usersMapper.selectPermissionsRoles(roles);
    }

}
