package cn.colg.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.colg.entities.Department;

/**
 * 部门dao
 *
 * @author colg
 */
@Repository
public class DepartmentDao {

    private static Map<Integer, Department> departments = null;

    /**
     * 初始化部门数据
     */
    static {
        departments = new HashMap<>();
        departments.put(101, new Department(101, "Top"));
        departments.put(102, new Department(102, "Jungle"));
        departments.put(103, new Department(103, "Middle"));
        departments.put(104, new Department(104, "Bottom"));
        departments.put(105, new Department(105, "Support"));
    }

    /**
     * 查询所有部门
     *
     * @return
     * @author colg
     */
    public Collection<Department> getDepartments() {
        return departments.values();
    }

    /**
     * 根据id查询部门
     *
     * @param id
     * @return
     * @author colg
     */
    public Department getDepartment(Integer id) {
        return departments.get(id);
    }

}
