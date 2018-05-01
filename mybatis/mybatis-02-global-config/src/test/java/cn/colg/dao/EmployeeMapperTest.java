package cn.colg.dao;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import cn.colg.entity.Employee;
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
        session = sqlSessionFactory.openSession();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        session.close();
    }

    @Test
    public void testFindById01() throws Exception {
        // mybatis会为接口自动创建一个代理对象，代理对象去执行增删改查方法
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        Employee employee = employeeMapper.findById(1);
        log.info("testFindById01() >> employee : {}", employee);
    }

    @Test
    public void testFindById02() throws Exception {
        // mybatis会为接口自动创建一个代理对象，代理对象去执行增删改查方法
        EmployeeMapperAnnotation employeeMapperAnnotation = session.getMapper(EmployeeMapperAnnotation.class);
        Employee employee = employeeMapperAnnotation.findById(1);
        log.info("testFindById02() >> employee : {}", employee);
    }

}
