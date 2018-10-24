package cn.colg.dao;

import java.util.List;

import org.junit.Test;

import cn.colg.BaseMapperTest;
import cn.colg.entity.Department;
import cn.colg.entity.Employee;
import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 员工Mapper 测试
 *
 * @author colg
 */
@Slf4j
public class EmployeeMapperTest extends BaseMapperTest {

    /**
     * Test method for {@link cn.colg.dao.EmployeeMapper#queryByConditionIf(cn.colg.entity.Employee)}.
     */
    @Test
    public void testQueryByConditionIf() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = new Employee(1, "%j%", "0", "%j%", null);
        List<Employee> list = employeeMapper.queryByConditionIf(employee);
        log.info("list : {}", list);
    }

    /**
     * Test method for {@link cn.colg.dao.EmployeeMapper#queryByConditionIfTrim(cn.colg.entity.Employee)}.
     */
    @Test
    public void testQueryByConditionIfTrim() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = new Employee(null, null, "0", "%j%", null);
        List<Employee> list = employeeMapper.queryByConditionIfTrim(employee);
        log.info("list : {}", list);
    }

    /**
     * Test method for {@link cn.colg.dao.EmployeeMapper#queryByConditionIfChoose(cn.colg.entity.Employee)}.
     */
    @Test
    public void testQueryByConditionIfChoose() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = new Employee();
        // 对象为空，直接进入最后一个分支查询
        List<Employee> list = employeeMapper.queryByConditionIfChoose(employee);
        log.info("list : {}", list);
    }

    /**
     * Test method for {@link cn.colg.dao.EmployeeMapper#updateEmployee(cn.colg.entity.Employee)}.
     */
    @Test
    public void testUpdateEmployee() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = new Employee(23, "tom", "0", null, null);
        boolean result = employeeMapper.updateEmployee(employee);
        log.info("result : {}", result);
    }

    /**
     * Test method for {@link cn.colg.dao.EmployeeMapper#queryByConditionForeach(java.lang.Integer[])}.
     */
    @Test
    public void testQueryByConditionForeach() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        List<Employee> list = employeeMapper.queryByConditionForeach(new Integer[] {1, 2});
        log.info("list : {}", list);
    }
    
    /**
     * Test method for {@link cn.colg.dao.EmployeeMapper#addEmployees(java.util.List<Employee>)}.
     */
    @Test
    public void testAddEmployees() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        List<Employee> employees = CollUtil.newArrayList(
                new Employee(null, "smith", "0", "smith@colg.com", new Department().setId(1)),
                new Employee(null, "allen", "1", "allen@colg.com", new Department().setId(2))
            );
        long result = employeeMapper.addEmployees(employees);
        log.info("result : {}", result);
    }
}
