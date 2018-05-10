package cn.colg.dao;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import cn.colg.entity.Employee;
import lombok.extern.slf4j.Slf4j;

/**
 * 员工Mapper 测试
 *
 * @author colg
 */
@Slf4j
public class EmployeeMapperTest {

    /**
     * Test method for {@link cn.colg.dao.EmployeeMapper#findById(java.lang.Integer)}.
     * 
     * @throws IOException
     */
    @Test
    public void testFindById() throws IOException {
        // 从 XML 中构建SqlSessionFactory
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 从SqlsessionFactory 中获取 sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        // EmployeeMapperTest.testFindById() >> mapper : org.apache.ibatis.binding.MapperProxy@55634720
        log.info("EmployeeMapperTest.testFindById() >> mapper : {}", mapper);

        Employee employee = mapper.findById(1);
        // EmployeeMapperTest.testFindById() >> employee : Employee(id=1, lastName=jack, gender=0, email=jack@colg.com)
        log.info("EmployeeMapperTest.testFindById() >> employee : {}", employee);
    }

}
