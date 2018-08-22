package cn.colg.dao;

import java.util.List;

import org.junit.Test;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.colg.BaseMapperTest;
import cn.colg.entity.Employee;
import cn.hutool.core.lang.Console;
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

        PageInfo<Object> pageInfo = PageHelper.startPage(1, 2, "te.last_name ASC")
                                              .doSelectPageInfo(() -> employeeMapper.selectAll());
        
        log.info("EmployeeMapperTest.testSelectAll() >> pageInfo : {}", pageInfo);
    }
    

    /**
     * 使用PageHelper时，pageSize=0时返回全部结果
     *
     * @throws Exception
     */
    @Test
    public void testPageHelper() throws Exception {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        
        // 使用PageHelper时，pageSize=0时返回全部结果
        PageHelper.startPage(1, 0);
        List<Employee> list = employeeMapper.selectAll();
        log.info("EmployeeMapperTest.testPageHelper() >> list.size() : {}", list.size());
    }
    
    /**
     * 自定义get方法
     *
     * @throws Exception
     */
    @Test
    public void testGetSex() throws Exception {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        PageHelper.startPage(1, 0);
        List<Employee> list = employeeMapper.selectAll();
        log.info("EmployeeMapperTest.testGetSex() >> list : {}", list);
    }
    
    @Test
    public void testOrderBy() throws Exception {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        PageHelper.startPage(1, 10, "last_name desc");
        List<Employee> list = employeeMapper.selectAll();
        list.forEach(e -> Console.log(e));
    }
    
}
