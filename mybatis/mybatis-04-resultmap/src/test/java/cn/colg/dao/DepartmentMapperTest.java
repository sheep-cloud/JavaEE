package cn.colg.dao;

import org.junit.Test;

import cn.colg.BaseMapperTest;
import cn.colg.entity.Department;
import lombok.extern.slf4j.Slf4j;

/**
 * 部门Mapper 测试
 *
 * @author colg
 */
@Slf4j
public class DepartmentMapperTest extends BaseMapperTest {
    
    /**
     * Test method for {@link cn.colg.dao.DepartmentMapper#findById(java.lang.Integer)}.
     */
    @Test
    public void testFindById() {
        DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
        Department department = departmentMapper.findById(1);
        log.info("testFindById() >> department : {}", department);
    }
    
    /**
     * Test method for {@link cn.colg.dao.DepartmentMapper#findByIdPlus(java.lang.Integer)}.
     */
    @Test
    public void testFindByIdPlus() {
        DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
        Department department = departmentMapper.findByIdPlus(1);
        log.info("testFindById() >> department : {}", department);
        log.info("testFindByIdPlus() >> department.getEmps() : {}", department.getEmps());
    }
    
    /**
     * Test method for {@link cn.colg.dao.DepartmentMapper#findByIdStep(java.lang.Integer)}.
     */
    @Test
    public void testFindByIdStep() {
        DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
        Department department = departmentMapper.findByIdStep(1);
        log.info("testFindById() >> department : {}", department);
        log.info("testFindByIdPlus() >> department.getEmps() : {}", department.getEmps());
    }
    
}
