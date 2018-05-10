package cn.colg.dao;

import java.util.List;

import org.junit.Test;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

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
     * Test method for {@link cn.colg.dao.EmployeeMapper#selectAll()}.
     */
    @Test
    public void testSelectAll() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);

        PageHelper.startPage(1, 2);
        List<Employee> list = employeeMapper.selectAll();
        log.info("EmployeeMapperTest.testSelectAll() >> list : {}", list);

        /// ----------------------------------------------------------------------------------------------------

        // jdk8 Lambda写法
        Page<Object> page = PageHelper.startPage(1, 2)
                                      .doSelectPage(() -> employeeMapper.selectAll());
        log.info("EmployeeMapperTest.testSelectAll() >> page : {}", page);

        /// ----------------------------------------------------------------------------------------------------

        PageInfo<Object> pageInfo = PageHelper.startPage(1, 2)
                                              .doSelectPageInfo(() -> employeeMapper.selectAll());
        
        log.info("EmployeeMapperTest.testSelectAll() >> pageInfo : {}", pageInfo);
    }
}
