package cn.colg.dao;

import java.util.List;

import cn.colg.entity.Employee;

/**
 * 员工Mapper
 *
 * @author colg
 */
public interface EmployeeMapper {

    /**
     * 查询所有员工
     *
     * @return
     */
    List<Employee> selectAll();
}
