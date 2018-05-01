package cn.colg.dao;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import cn.colg.entity.Employee;
import cn.hutool.core.lang.Dict;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

/**
 * 员工Mapper 测试
 *
 * @author colg
 */
public class EmployeeMapperTest {

    public static final Log log = LogFactory.get();

    private static SqlSession session;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        // 从 XML 中构建 SqlSessionFactory
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        // 从 SqlSessionFactory 中获取 SqlSession
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        /*
         * 1、Mybatis允许增删改查直接定义以下类型返回值
         *      Integer、Long、Boolean
         * 
         * 2、需要手动提交数据
         *      sqlSessionFactory.openSession();        ===>    手动提交
         *      sqlSessionFactory.openSession(true);    ===>    自动提交
         */
        session = sqlSessionFactory.openSession(true);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        session.close();
    }

    @Test
    public void testQueryByLastNameResultMap() {
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        Map<Integer, Employee> map = employeeMapper.queryByLastNameResultMap("jack");
        log.info("enclosing_method() >> map : {}", map);
    }

    @Test
    public void testFindByIdResultMap() {
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        Map<String, Object> map = employeeMapper.findByIdResultMap(1);
        log.info("testFindByIdResultMap() >> map : {}", map);
    }

    @Test
    public void testQueryByLastName() {
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        List<Employee> list = employeeMapper.queryByLastName("jack");
        log.info("testQueryByLastName() >> list : {}", list);
    }

    @Test
    public void testFindByMap() {
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        Dict map = new Dict(3);
        map.set("tableName", "tbl_employee")
           .set("lastName", "jack")
           .set("id", 1);
        Employee employee = employeeMapper.findByMap(map);
        log.info("testFindByMap() >> employee : {}", employee);
    }

    @Test
    public void testFindByIdAndLastName() {
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        Employee employee = employeeMapper.findByIdAndLastName(1, "jack");
        log.info("testFindByIdAndLastName() >> employee : {}", employee);
    }

    @Test
    public void testFindById() {
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        Employee employee = employeeMapper.findById(1);
        log.info("testFindById() >> employee : {}", employee);
    }

    @Test
    public void testAddEmployee() {
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        Employee employee = new Employee(null, "rose", "1", "rose@qq.com");
        log.info("testAddEmployee() >> 插入前 id : {}", employee.getId());
        
        // 不是直接得到返回的主键id，而是通过之前的对象get出来
        employeeMapper.addEmployee(employee);
        log.info("testAddEmployee() >> 插入后 id : {}", employee.getId());
    }

    @Test
    public void testUpdateEmployee() {
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        Employee employee = new Employee(11, "tom", "0", "tom@colg.com");
        Integer integer = employeeMapper.updateEmployee(employee);
        log.info("testUpdateEmployee() >> integer : {}", integer);
    }

    @Test
    public void testDeleteById() {
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        Boolean boolean1 = employeeMapper.deleteById(1);
        log.info("testDeleteById() >> boolean1 : {}", boolean1);
    }

}
