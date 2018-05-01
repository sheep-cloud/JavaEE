package cn.colg.dao;

import org.apache.ibatis.annotations.Param;

import cn.colg.entity.Department;

/**
 * 部门Mapper
 *
 * @author colg
 */
public interface DepartmentMapper {

    /**
     * 根据id查询部门
     *
     * @param id
     * @return
     */
    Department findById(@Param("id") Integer id);
}
