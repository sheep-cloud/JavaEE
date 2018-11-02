package cn.colg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import cn.colg.bean.Department;

/**
 * DepartmentMapper
 * 
 * @author colg
 */
public interface DepartmentMapper {

    @Select("SELECT * FROM department")
    List<Department> query();

    @Select("SELECT d.* FROM department d WHERE d.id = #{id}")
    Department getDeptById(Integer id);

    /**
     * 插入数据，返回主键
     *
     * @param department
     * @return
     * @author colg
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO department(departmentName) VALUES(#{departmentName})")
    int insertDept(Department department);
}
