package cn.colg.controller;

import static cn.colg.util.ResultVoUtil.success;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cn.colg.bean.Employee;
import cn.colg.mapper.EmployeeMapper;
import cn.colg.vo.ResultVo;

/**
 * EmpController
 *
 * @author colg
 */
@RestController
public class EmpController {

    @Autowired
    private EmployeeMapper employeeMapper;

    @GetMapping("/emp/query")
    public ResultVo queryEmp() {
        List<Employee> list = employeeMapper.query();
        return success(list);
    }

    @GetMapping("/emp/{id}")
    public ResultVo getEmp(@PathVariable("id") Integer id) {
        Employee employee = employeeMapper.getEmpById(id);
        return success(employee);
    }

    @GetMapping("/emp")
    public ResultVo insertEmp(Employee employee) {
        employeeMapper.insertEmp(employee);
        return success(employee);
    }
    
}
