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

    /**
     * 根据id查询员工信息
     *
     * @param id
     * @return
     * @author colg
     */
    Employee getEmpById(Integer id);

    /**
     * 插入数据，返回主键
     *
     * @param employee
     * @author colg
     */
    void insertEmp(Employee employee);
    
    Employee getEmp2ById(Integer id);
}
