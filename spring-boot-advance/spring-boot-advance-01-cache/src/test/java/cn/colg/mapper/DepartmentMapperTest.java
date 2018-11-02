package cn.colg.mapper;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.colg.SpringBootAdvance01CacheApplicationTests;
import cn.colg.bean.Department;
import lombok.extern.slf4j.Slf4j;

/**
 * DepartmentMapper 测试
 *
 * @author colg
 */
@Slf4j
public class DepartmentMapperTest extends SpringBootAdvance01CacheApplicationTests{
    
    @Autowired
    private DepartmentMapper departmentMapper;

    @Test
    public final void testQuery() {
        List<Department> list = departmentMapper.query();
        log.info("部门数量: {}", list.size());
        log.info("部门列表: {}", list);
    }

    @Test
    public final void testGetDeptById() {
        Department department = departmentMapper.getDeptById(1);
        log.info("部门信息: {}", department);
    }

    @Test
    public final void testInsertDept() {
        Department department = new Department("开发部");
        departmentMapper.insertDept(department);
        log.info("插入部门: {}", department);
    }

}
