package cn.colg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import cn.colg.bean.Department;

/**
 * DepartmentMapper；注解版
 * 
 * <pre>
 * `@Mapper`: 单独指定这是一个操作数据库的mapper；可在启动类批量扫描
 * </pre>
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
