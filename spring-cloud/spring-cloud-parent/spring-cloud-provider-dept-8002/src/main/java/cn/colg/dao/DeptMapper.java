package cn.colg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.colg.entity.Dept;

/**
 * 部门Mapper
 *
 * @author colg
 */
public interface DeptMapper {

    /**
     * 添加部门
     *
     * @param dept
     * @return
     */
    boolean addDept(@Param("dept") Dept dept);

    /**
     * 根据id获取部门
     *
     * @param id
     * @return
     */
    Dept findById(@Param("id") Long id);

    /**
     * 获取所有部门列表
     *
     * @return
     */
    List<Dept> queryAll();
}
