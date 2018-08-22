package cn.colg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.colg.entity.Employee;

/**
 * 员工Mapper
 *
 * @author colg
 */
public interface EmployeeMapper {

    /**
     * 根据id查询员工
     *
     * @param id
     * @return
     */
    Employee findById(@Param("id") Integer id);

    /**
     * 根据id查询员工，把部门也查出来；
     * 
     * 方式一：级联属性封装，OGNL表达式，对象.属性
     *
     * @param id
     * @return
     */
    Employee findEmpAndDept01(@Param("id") Integer id);

    /**
     * 根据id查询员工，把部门也查出来；
     * 
     * 方式二：级联属性封装
     *
     * @param id
     * @return
     */
    Employee findEmpAndDept02(@Param("id") Integer id);

    /**
     * 根据id查询员工，把部门也查出来；
     * 
     * 方式三：级联属性封装：使用association，关联对象
     *
     * @param id
     * @return
     */
    Employee findEmpAndDept03(@Param("id") Integer id);

    /**
     * 根据id查询员工，把部门也查出来；
     * 
     * 方式四：分步查询：使用association，分步查询
     *
     * @param id
     * @return
     */
    Employee findEmpAndDeptStep04(@Param("id") Integer id);

    /**
     * 根据部门id查询所有员工
     *
     * @param deptId
     * @return
     */
    List<Employee> queryByDeptId(@Param("deptId") Integer deptId);

    /**
     * 根据部门id和员工姓名模糊查询
     *
     * @param deptId
     * @param lastName
     * @return
     */
    List<Employee> queryByDeptIdAndLastName(@Param("deptId") Integer deptId, @Param("lastName") String lastName);

}
