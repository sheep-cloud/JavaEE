package cn.colg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.colg.dao.DeptMapper;
import cn.colg.entity.Dept;
import cn.colg.service.DeptService;

/**
 * 部门ServiceImpl
 *
 * @author colg
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean add(Dept dept) {
        return deptMapper.addDept(dept);
    }

    @Override
    public Dept get(Long id) {
        return deptMapper.findById(id);
    }

    @Override
    public List<Dept> list() {
        return deptMapper.queryAll();
    }

}
