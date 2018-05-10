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
 * 员工Mapper <b>运行原理</b>
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

        /*
         * colg  mybatis 运行原理
         *  1. 获取sqlSessionFactory对象
         *  2. 获取sqlSession对象
         *  3. 获取接口的代理对象（MapperProxy）
         *  4. 执行增删改查方法
         */

        /// ----------------------------------------------------------------------------------------------------

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
        
        // TODO colg [mybatis-09-operating-principle :: mybatis 运行原理 未完成]
    }

}
