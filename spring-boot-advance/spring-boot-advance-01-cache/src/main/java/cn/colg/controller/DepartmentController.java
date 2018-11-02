package cn.colg.controller;

import static cn.colg.util.ResultVoUtil.success;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cn.colg.bean.Department;
import cn.colg.service.DepartmentService;
import cn.colg.vo.ResultVo;

/**
 * DepartmentController
 *
 * @author colg
 */
@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;
    
    @GetMapping("/dept/{id}")
    public ResultVo getDept(@PathVariable Integer id) {
        Department department = departmentService.getDeptById(id);
        return success(department);
    }
    
    @GetMapping("/dept2/{id}")
    public ResultVo getDept2(@PathVariable Integer id) {
        Department department = departmentService.getDept2ById(id);
        return success(department);
    }
}
