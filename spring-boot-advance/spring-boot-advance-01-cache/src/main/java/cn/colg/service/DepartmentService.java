package cn.colg.service;

import java.util.List;

import cn.colg.bean.Department;

/**
 * DepartmentService
 *
 * @author colg
 */
public interface DepartmentService {

    List<Department> query();

    Department getDeptById(Integer id);

    /**
     * 插入数据，返回主键
     *
     * @param employee
     * @author colg
     */
    void insertDept(Department department);

    Department getDept2ById(Integer id);
}
