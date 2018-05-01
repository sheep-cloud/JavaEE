package cn.colg.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.colg.entity.Employee;

public interface EmployeeMapperAnnotation {

    @Select("SELECT * FROM tbl_employee WHERE id = #{id}")
    Employee findById(@Param("id") Integer id);
}
