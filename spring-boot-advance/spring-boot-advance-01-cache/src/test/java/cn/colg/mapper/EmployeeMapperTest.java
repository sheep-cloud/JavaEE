package cn.colg.mapper;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.colg.SpringBootAdvance01CacheApplicationTests;
import cn.colg.bean.Employee;
import lombok.extern.slf4j.Slf4j;

/**
 * EmployeeMapper 测试
 *
 * @author colg
 */
@Slf4j
public class EmployeeMapperTest extends SpringBootAdvance01CacheApplicationTests {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * Test method for {@link cn.colg.mapper.EmployeeMapper#query()}.
     */
    @Test
    public final void testQuery() {
        List<Employee> list = employeeMapper.query();
        log.info("员工数量: {}", list.size());
        log.info("员工列表: {}", list);
    }

    /**
     * Test method for {@link cn.colg.mapper.EmployeeMapper#getEmpById(java.lang.Integer)}.
     */
    @Test
    public final void testGetEmpById() {
        Employee employee = employeeMapper.getEmpById(1);
        log.info("员工信息: {}", employee);
    }

    /**
     * Test method for {@link cn.colg.mapper.EmployeeMapper#insertEmp(cn.colg.bean.Employee)}.
     */
    @Test
    public final void testInsertEmp() {
        Employee employee = new Employee("jack", "jack@qq.com", 0, 1);
        employeeMapper.insertEmp(employee);
        log.info("插入员工: {}", employee);
    }

}
