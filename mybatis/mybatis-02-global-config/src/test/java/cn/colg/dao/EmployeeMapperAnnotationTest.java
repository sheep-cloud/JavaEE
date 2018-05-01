package cn.colg.dao;

import org.junit.Test;

import cn.colg.BaseMapperTest;
import cn.colg.entity.Employee;
import lombok.extern.slf4j.Slf4j;

/**
 * 员工MapperAnnotation 测试
 *
 * @author colg
 */
@Slf4j
public class EmployeeMapperAnnotationTest extends BaseMapperTest {

    /**
     * Test method for {@link cn.colg.dao.EmployeeMapperAnnotation#findById(java.lang.Integer)}.
     */
    @Test
    public void testFindById() {
        // mybatis会为接口自动创建一个代理对象，代理对象去执行增删改查方法
        EmployeeMapperAnnotation employeeMapperAnnotation = sqlSession.getMapper(EmployeeMapperAnnotation.class);
        Employee employee = employeeMapperAnnotation.findById(1);
        log.info("testFindById() >> employee : {}", employee);
    }

}
