package cn.colg.controller;

import static cn.colg.util.ResultVoUtil.success;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cn.colg.bean.Department;
import cn.colg.mapper.DepartmentMapper;
import cn.colg.vo.ResultVo;

/**
 * DeptController
 *
 * @author colg
 */
@RestController
public class DeptController {

    @Autowired
    private DepartmentMapper departmentMapper;

    @GetMapping("/dept/query")
    public ResultVo queryDept() {
        List<Department> list = departmentMapper.query();
        return success(list);
    }

    @GetMapping("/dept/{id}")
    public ResultVo getDepartment(@PathVariable("id") Integer id) {
        Department department = departmentMapper.getDeptById(id);
        return success(department);
    }

    @GetMapping("/dept")
    public ResultVo insertDept(Department department) {
        departmentMapper.insertDept(department);
        return success(department);
    }

}