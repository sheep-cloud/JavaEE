package cn.colg.dao;

import org.apache.ibatis.annotations.Param;

import cn.colg.entity.Employee;

/**
 * 员工Mapper
 *
 * @author colg
 */
public interface EmployeeMapper {

    /**
     * 根据id查询员工；
     * 
     * 如果员工是女生，就把部门信息查询出来，否则不查询； 如果员工是男生，就把lastName赋值给email。
     *
     * @param id
     * @return
     */
    Employee findByIdWithGender(@Param("id") Integer id);
}
