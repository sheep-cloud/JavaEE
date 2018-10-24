package cn.colg.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.colg.entity.Employee;

/**
 * 员工MapperAnnotation
 *
 * @author colg
 */
public interface EmployeeMapperAnnotation {

    /**
     * 根据id查询员工
     *
     * @param id
     * @return
     */
    @Select("SELECT te.id, te.last_name, te.gender, te.email FROM tbl_employee te WHERE te.id = #{id}")
    Employee findById(@Param("id") Integer id);
}
