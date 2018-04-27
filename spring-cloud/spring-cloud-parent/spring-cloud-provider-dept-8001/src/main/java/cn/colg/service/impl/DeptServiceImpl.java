package cn.colg.service.impl;

import static cn.colg.util.CheckUtil.check;
import static cn.colg.util.ValidUtil.isIntegerPositive;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.colg.dao.DeptMapper;
import cn.colg.entity.Dept;
import cn.colg.service.DeptService;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

/**
 * 部门ServiceImpl
 *
 * @author colg
 */
@Service
public class DeptServiceImpl implements DeptService {
    
    public static final Log log = LogFactory.get();

    @Autowired
    private DeptMapper deptMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean add(Dept dept) {
        return deptMapper.addDept(dept);
    }

    @Override
    public Dept get(Long id) {
        check(isIntegerPositive(id + ""), "参数异常，请输入正整数！");
        log.info("DeptServiceImpl.get(id) : {}", id);
        return deptMapper.findById(id);
    }
    
    @Override
    public List<Dept> list() {
        List<Dept> list = deptMapper.queryAll();
        log.info("DeptServiceImpl.list() : size : {}", list.size());
        return list;
    }

}
