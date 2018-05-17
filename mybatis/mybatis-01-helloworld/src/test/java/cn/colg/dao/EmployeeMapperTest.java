package cn.colg.dao;

import org.junit.Test;

import cn.colg.BaseMapperTest;
import cn.colg.entity.Employee;
import lombok.extern.slf4j.Slf4j;

/**
 * 测试
 *
 * @author colg
 */
@Slf4j
public class EmployeeMapperTest extends BaseMapperTest {
    
    /*
     * 1、接口式编程 
     *      原生：                        Dao     ===>        DaoImpl
     *      Mybatis:    Mapper  ===>        xxMapper.xml
     * 
     * 2、SqlSession代表和数据库的一次回话，用完必须关闭；
     * 
     * 3、SqlSession和Connection一样都是非线程安全的。每次使用都应该去获取新的对象。
     * 
     * 4、Mapper接口没有实现类，但是Mybatis会为这个接口生成一个代理对象。
     *      (将接口和xml进行绑定)
     *      EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
     * 
     * 5、两个重要的配置文件：
     *      1). Mybatis的全局配置文件，包括数据库连接池信息，事务管理器信息等。。系统运行环境信息
     *      2). Sql映射文件：保存了每一个sql语句的映射信息：将sql抽取出来
     */

    /// ----------------------------------------------------------------------------------------------------

    /**
     * Test method for {@link cn.colg.dao.EmployeeMapper#findById(java.lang.Integer)}.
     */
    @Test
    public void testFindById01() {
        // 1. 唯一标识符。
        // 2. 传递给语句的参数对象。
        Employee employee = sqlSession.selectOne("cn.colg.dao.EmployeeMapper.findById", 1);
        log.info("EmployeeMapperTest.testFindById01() >> employee : {}", employee);
    }

    /**
     * Test method for {@link cn.colg.dao.EmployeeMapper#findById(java.lang.Integer)}.
     */
    @Test
    public void testFindById02() {
        // mybatis会为接口自动创建一个代理对象，代理对象去执行增删改查方法
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        // class com.sun.proxy.$Proxy11
        log.info("EmployeeMapperTest.testFindById02() >> employeeMapper.getClass() : {}", employeeMapper.getClass());

        Employee employee = employeeMapper.findById(1);
        log.info("EmployeeMapperTest.testFindById02() >> employee : {}", employee);
    }

}
