package cn.colg.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.colg.entities.Department;
import cn.colg.entities.Employee;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;

/**
 * 员工dao
 *
 * @author colg
 */
@Repository
public class EmployeeDao {

    private static Map<Integer, Employee> employees = null;

    @Autowired
    private DepartmentDao departmentDao;

    /**
     * 初始化员工数据
     */
    static {
        employees = new HashMap<>();
        employees.put(1001, new Employee(1001, "Jax", "jax@lol.com", 1, getRandomBirth(), new Department(101, "Top")));
        employees.put(1002, new Employee(1002, "Elise", "elise@lol.com", 0, getRandomBirth(), new Department(102, "Jungle")));
        employees.put(1003, new Employee(1003, "Kassadin", "kassadin@lol.com", 1, getRandomBirth(), new Department(103, "Middle")));
        employees.put(1004, new Employee(1004, "Ashe", "ashe@lol.com", 0, getRandomBirth(), new Department(104, "Boottom")));
        employees.put(1005, new Employee(1005, "Lulu", "lulu@lol.com", 0, getRandomBirth(), new Department(105, "Support")));
    }

    /**
     * 获取随机生日，范围 1950-01-01 ~ 2000-01-01
     *
     * @return 随机日期
     * @author colg
     */
    private static DateTime getRandomBirth() {
        long timeMillis = RandomUtil.randomLong(DateUtil.parse("1950-01-01").getTime(), DateUtil.parse("2000-01-01").getTime());
        return new DateTime(timeMillis);
    }
    
    private static Integer initId = 1006;
    
    /**
     * 添加员工
     *
     * @param employee
     * @author colg
     */
    public void save(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(initId++);
        }

        employee.setDepartment(departmentDao.getDepartment(employee.getDepartment().getId()));
        employees.put(employee.getId(), employee);
    }

    /**
     * 查询所有员工
     *
     * @return
     * @author colg
     */
    public Collection<Employee> getAll() {
        return employees.values();
    }

    /**
     * 根据id查询员工
     *
     * @param id
     * @return
     * @author colg
     */
    public Employee get(Integer id) {
        return employees.get(id);
    }

    /**
     * 根据id删除员工
     *
     * @param id
     * @author colg
     */
    public void delete(Integer id) {
        employees.remove(id);
    }
}
