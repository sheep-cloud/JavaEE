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
         *      解析文件的每一个信息保存在Configuration中，返回包含Configuration的DefaultSqlSession；
         *      注意：【MappedStatement】代表一个增删改查的详细信息
         *      
         *  2. 获取sqlSession对象
         *      返回一个DefaultSqlSession对象，包含Executor和Configuration；
         *      这一步会创建Executor对象；
         *      
         *  3. 获取接口的代理对象（MapperProxy）
         *      getMapper，使用MapperProxyFactory创建一个MapperProxy的代理对象
         *      代理对象里面包含了，DefaultSqlSession（Executor）
         *      
         *  4. 执行增删改查方法
         *  
         *  【总结】：
         *      1、根据配置文件（全局，sql映射）初始化出Configuration对象；
         *      2、创建一个DefaultSqlSession对象，他里面包含Configuration以及Executor（根据全局配置文件中的defaultExecutorType创建出对应的Executor）；
         *      3、DefaultSqlSession.getMapper()：拿到Mapper接口对应的MapperProxy；
         *      4、MapperProxy里面有（DefaultSqlSession）；
         *      5、执行增删改查方法：
         *          1)、调用DefaultSqlSession的增删改查（Executor）；
         *          2)、创建一个StatementHandler对象，（同时也会创建出ParameterHandler和resultSetHandler）；
         *          3)、调用StatementHandler预编译参数以及设置参数值；使用ParameterHandler来给sql设置参数；
         *          4)、调用StatementHandler的增删改查方法；
         *          5)、ResultSetHandler封装结果
         *          
         *  注意：
         *      四大对象每个创建的时候都有一个interceptorChain.pluginAll(parameterHandler);
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
    }

}
