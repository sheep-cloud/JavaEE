package cn.colg.controller;

import static cn.colg.util.ResultVoUtil.success;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.colg.bean.Employee;
import cn.colg.service.EmployeeService;
import cn.colg.vo.ResultVo;

/**
 * EmployeeController
 *
 * @author colg
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/emp/{id}")
    public ResultVo getEmployee(@PathVariable Integer id) {
        Employee employee = employeeService.getEmpById(id);
        return success(employee);
    }
    
    @GetMapping("/emp2/{id}")
    public ResultVo getEmployee2(@PathVariable Integer id) {
        Employee employee = employeeService.getEmp2ById(id);
        return success(employee);
    }
}
