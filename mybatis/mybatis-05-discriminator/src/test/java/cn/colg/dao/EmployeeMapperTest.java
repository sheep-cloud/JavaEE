package cn.colg.dao;

import org.junit.Test;

import cn.colg.BaseMapperTest;
import cn.colg.entity.Employee;
import lombok.extern.slf4j.Slf4j;

/**
 * 
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
        Employee employee = employeeMapper.findByIdWithGender(22);
        log.info("testFindByIdWithGender() >> employee : {}", employee);
        log.info("testFindByIdWithGender() >> employee.getDept() : {}", employee.getDept());
        // TODO colg [discriminator：case条件：gender=1，没有查询部门]
    }

}
