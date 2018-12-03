package cn.colg.dao;

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
     * Test method for {@link cn.colg.dao.EmployeeMapper#findByIdWithGender(java.lang.Integer)}.
     */
    @Test
    public void testFindByIdWithGender() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = employeeMapper.findByIdWithGender(1);
        log.info("employee : {}", employee);
        log.info("employee.getDept() : {}", employee.getDept());
        log.info("----------------------------------------------------------------------------------------------------");

        employee = employeeMapper.findByIdWithGender(2);
        log.info("employee : {}", employee);
        log.info("employee.getDept() : {}", employee.getDept());
    }

}
