package cn.colg.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import cn.colg.dao.DepartmentDao;
import cn.colg.dao.EmployeeDao;
import cn.colg.entities.Department;
import cn.colg.entities.Employee;
import lombok.extern.slf4j.Slf4j;

/**
 * EmployeeController
 *
 * @author colg
 */
@Slf4j
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DepartmentDao departmentDao;

    /**
     * 查询所有员工，返回列表页面
     *
     * @return
     * @author colg
     */
    @GetMapping("/emps")
    public String list(Model model) {
        Collection<Employee> employees = employeeDao.getAll();
        // 放在请求域
        model.addAttribute("emps", employees);

        // classpath:/templates/emp/list.html
        return "emp/list";
    }

    /**
     * 跳转员工添加页面
     *
     * @return
     * @author colg
     */
    @GetMapping("/emp")
    public String toAddEmp(Model model) {
        // 查出所有的部门，在页面显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);

        // classpath:/templates/emp/add.html
        return "emp/add";
    }

    /**
     * 添加员工
     *
     * @param employee
     * @return
     * @author colg
     */
    @PostMapping("/emp")
    public String addEmp(Employee employee) {
        log.info("保存的员工信息：{}", employee);
        // 保存员工
        employeeDao.save(employee);

        /*
         * colg  [添加完跳转到列表页面]
         *  redirect:   表示重定向到一个地址 "/"代表当前项目的路径
         *  forward:    表示转发到一个地址
         */
        return "redirect:/emps";
    }

    /**
     * 跳转员工编辑页面
     *
     * @param id
     * @return
     * @author colg
     */
    @GetMapping("/emp/{id}")
    public String toEditEmp(@PathVariable Integer id, Model model) {
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp", employee);
        // 查出所有的部门，在页面显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);

        // classpath:/templates/emp/edit.html
        return "emp/edit";
    }
    
    /**
     * 修改员工
     *
     * @param employee
     * @return
     * @author colg
     */
    @PutMapping("/emp")
    public String editEmp(Employee employee) {
        log.info("修改的员工信息：{}", employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    /**
     * 删除员工
     *
     * @param id
     * @return
     * @author colg
     */
    @DeleteMapping("/emp/{id}")
    public String deleteEmp(@PathVariable Integer id) {
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
