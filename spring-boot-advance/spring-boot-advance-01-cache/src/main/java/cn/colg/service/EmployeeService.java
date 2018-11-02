package cn.colg.service;

import java.util.List;

import cn.colg.bean.Employee;

/**
 * EmployeeService
 *
 * @author colg
 */
public interface EmployeeService {

    List<Employee> query();

    Employee getEmpById(Integer id);

    /**
     * 插入数据，返回主键
     *
     * @param employee
     * @author colg
     */
    void insertEmp(Employee employee);
}
