package cn.colg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.colg.bean.Employee;
import cn.colg.mapper.EmployeeMapper;

/**
 * 员工Service
 *
 * @author colg
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    public Employee selectOne(Employee employee) {
        return employeeMapper.selectOne(employee);
    }

    public Employee selectByPrimaryKey(Integer empId) {
        return employeeMapper.selectByPrimaryKey(empId);
    }

    public Boolean existsWithPrimaryKey(Integer empId) {
        return employeeMapper.existsWithPrimaryKey(empId);
    }

    public Integer insert(Employee employee) {
        return employeeMapper.insert(employee);
    }


}
