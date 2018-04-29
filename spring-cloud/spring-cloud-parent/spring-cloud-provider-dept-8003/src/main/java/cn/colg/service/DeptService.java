package cn.colg.service;

import java.util.List;

import cn.colg.entity.Dept;

/**
 * 部门Service
 *
 * @author colg
 */
public interface DeptService {

    /**
     * 添加部门
     *
     * @param dept
     * @return
     */
    boolean add(Dept dept);

    /**
     * 根据id获取部门
     *
     * @param id
     * @return
     */
    Dept get(Long id);

    /**
     * 获取所有部门
     *
     * @return
     */
    List<Dept> list();
}
