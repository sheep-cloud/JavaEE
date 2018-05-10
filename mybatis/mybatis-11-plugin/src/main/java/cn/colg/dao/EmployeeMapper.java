package cn.colg.dao;

import org.apache.ibatis.annotations.Param;

import cn.colg.entity.Employee;

/**
 * 员工Mapper
 *
 * @author colg
 */
public interface EmployeeMapper {

    /**
     * 根据id查询员工
     *
     * @param id
     * @return
     */
    Employee findById(@Param("id") Integer id);
}
