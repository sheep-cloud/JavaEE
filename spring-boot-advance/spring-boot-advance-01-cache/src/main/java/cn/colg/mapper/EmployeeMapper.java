package cn.colg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import cn.colg.bean.Employee;

/**
 * EmployeeMapper
 *
 * @author colg
 */
public interface EmployeeMapper {

    @Select("SELECT * FROM employee")
    List<Employee> query();

    @Select("SELECT e.* FROM employee e WHERE e.id = #{id}")
    Employee getEmpById(Integer id);

    /**
     * 插入数据，返回主键
     *
     * @param employee
     * @author colg
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO employee(lastName, email, gender, d_id) VALUES(#{lastName}, #{email}, #{gender}, #{dId})")
    void insertEmp(Employee employee);
}
