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
     * 根据携带的条件查询（携带了哪些字段就查哪些）；
     * 
     * test，where 语法
     *
     * @param employee
     * @return
     */
    List<Employee> queryByConditionIf(Employee employee);

    /**
     * 根据携带的条件查询（携带了哪些字段就查哪些）；
     * 
     * trim 语法
     *
     * @param employee
     * @return
     */
    List<Employee> queryByConditionIfTrim(Employee employee);

    /**
     * 根据携带的条件查询（携带了哪些字段就查哪些）；
     * 
     * choose 语法
     *
     * @param employee
     * @return
     */
    List<Employee> queryByConditionIfChoose(Employee employee);

    /**
     * 根据employee对象更新
     * 
     * set 语法
     *
     * @param employee
     * @return
     */
    boolean updateEmployee(Employee employee);

    /**
     * 根据id数组获取员工列表；
     * 
     * foreach 语法
     *
     * @param ids
     * @return
     */
    List<Employee> queryByConditionForeach(@Param("ids") Integer[] ids);

    /**
     * 批量添加员工对象；
     * 
     * foreach 语法
     *
     * @param employees
     * @return
     */
    long addEmployees(@Param("employees") List<Employee> employees);
}
