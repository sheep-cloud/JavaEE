package cn.colg.dao;

import java.util.List;

import org.junit.Test;

import cn.colg.BaseMapperTest;
import cn.colg.entity.Employee;
import lombok.extern.slf4j.Slf4j;

/**
 * 员工Mapper 测试
 *
 * @author colg
 */
@Slf4j
public class EmployeeMapperTest extends BaseMapperTest {

    /**
     * Test method for {@link cn.colg.dao.EmployeeMapper#findById(java.lang.Integer)}.
     */
    @Test
    public void testFindById() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = employeeMapper.findById(1);
        log.info("testFindById() >> employee : {}", employee);
    }

    /**
     * Test method for {@link cn.colg.dao.EmployeeMapper#findEmpAndDept01(java.lang.Integer)}.
     */
    @Test
    public void testFindEmpAndDept01() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = employeeMapper.findEmpAndDept01(1);
        log.info("testFindEmpAndDept() >> employee : {}", employee);
    }

    /**
     * Test method for {@link cn.colg.dao.EmployeeMapper#findEmpAndDept02(java.lang.Integer)}.
     */
    @Test
    public void testFindEmpAndDept02() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = employeeMapper.findEmpAndDept02(1);
        log.info("testFindEmpAndDept02() >> employee : {}", employee);
    }

    /**
     * Test method for {@link cn.colg.dao.EmployeeMapper#findEmpAndDept03(java.lang.Integer)}.
     */
    @Test
    public void testFindEmpAndDept03() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = employeeMapper.findEmpAndDept03(1);
        log.info("testFindEmpAndDept02() >> employee : {}", employee);
    }

    /**
     * Test method for {@link cn.colg.dao.EmployeeMapper#findEmpAndDeptStep04(java.lang.Integer)}.
     */
    @Test
    public void testFindEmpAndDeptStep04() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = employeeMapper.findEmpAndDeptStep04(1);

        // 延迟加载，按需加载
        log.info("testFindEmpAndDeptStep04() >> employee.getLastName() : {}", employee.getLastName());
        log.info("testFindEmpAndDeptStep04() >> employee : {}", employee);
    }

    /**
     * Test method for {@link cn.colg.dao.EmployeeMapper#queryByDeptId(java.lang.Integer)}.
     */
    @Test
    public void testQueryByDeptId() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        List<Employee> list = employeeMapper.queryByDeptId(1);
        log.info("testQueryByDeptId() >> list : {}", list);
    }
    
    /**
     * Test method for {@link cn.colg.dao.EmployeeMapper#queryByDeptIdAndLastName(java.lang.Integer, java.lang.String)}.
     */
    @Test
    public void testQueryByDeptIdAndLastName() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        List<Employee> list = employeeMapper.queryByDeptIdAndLastName(1, "o");
        log.info("testQueryByDeptIdAndLastName() >> list : {}", list);
    }
}
