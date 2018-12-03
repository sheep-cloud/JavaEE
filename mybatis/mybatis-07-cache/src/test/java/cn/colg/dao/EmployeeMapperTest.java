package cn.colg.dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

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

    /*
     * colg  [两级缓存]
     *  一级缓存：（本地缓存）sqlSession级别的缓存，一级缓存是一直开启的；
     *      与数据库同一次会话期间查询到的数据会放在本地缓存中。以后如果需要获取相同的数据，直接从缓存中拿，没必要再去查询数据库。
     *      
     *      一级缓存失效情况（没有使用到当前一级缓存的情况，效果就是，还需要再向数据库发出查询）
     *          1. sqlSession不同
     *          2. sqlSession相同
     *              2.1. 查询条件不同
     *              2.2. 查询之间执行了增删改操作
     *              2.3. 手动清除了一级缓存（缓存清空）
     *          
     *  二级缓存：（全局缓存基于namespace级别的缓存，一个namespace对应一个二级缓存
     *      工作机制:
     *          1. 一个会话，查询一条数据，这个数据就会被放在当前会话的一级缓存中；
     *          2. 如果会话关闭；一级缓存中的数据会被保存到二级缓存中；新的会话查询信息，就可以参照二级缓存中的内容。
     *          3. 不同namespace查询的数据会放在自己对应的缓存中（map）；数据会从二级缓存中获取；
     *              查出的数据会默认先放在一级缓存中。只有会话提交或者关闭以后，一级缓存中的数据才会转移到二级缓存中。
     *          
     *      使用：
     *          1. 去mybatis-config.xml中开启全局二级缓存配置: <setting name="cacheEnabled" value="true"/>
     *          2. 去mapper.xml中配置使用二级缓存: <cache></cache>
     *          3. pojo需要实现序列化接口
     *          
     *  缓存相关的设置/属性：
     *      1. <setting name="cacheEnabled" value="false"/>
     *          一级缓存：未关闭；二级缓存：关闭
     *      2. <select ... useCache="false">
     *          一级缓存：未关闭；二级缓存：关闭
     *      3. <insert ... flushCache="true">
     *          一级缓存：关闭；二级缓存：关闭
     */

    /**
     * 一级缓存：（本地缓存）
     *
     * @throws Exception
     * @author colg
     */
    @Test
    public void testFirstLevelCache() throws Exception {
        // 1. sqlSession不同
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = employeeMapper.findById(1);
        log.info("employee : {}", employee);
        sqlSession.close();

        // xxx 操作
        Thread.sleep(1000);
        log.info("sqlSession不同，执行了xxx操作");

        sqlSession = sqlSessionFactory.openSession();
        employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        employee = employeeMapper.findById(1);
        log.info("employee : {}", employee);
        sqlSession.close();
        log.info("----------------------------------------------------------------------------------------------------");

        // 2. sqlSession相同
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        EmployeeMapper employeeMapper2 = sqlSession2.getMapper(EmployeeMapper.class);
        Employee employee2 = employeeMapper2.findById(1);
        log.info("employee2 : {}", employee2);

        // 2.1. 查询条件不同
        /*
        log.info("sqlSession相同，查询条件不同");
        employeeMapper2 = sqlSession2.getMapper(EmployeeMapper.class);
        employee2 = employeeMapper2.findById(2);
        log.info("employee2 : {}", employee2);
        */
        // 2.2. 查询之间执行了增删改操作
        /*
        log.info("sqlSession相同，查询之间执行了增删改操作");
        employeeMapper2.addEmployee(new Employee("cache", "1", "cache@colg.com"));
        */
        // 2.3. 手动清除了一级缓存（缓存清空）
        /*
        log.info("sqlSession相同，手动清除了一级缓存（缓存清空）");
        sqlSession2.clearCache();
        */

        employee2 = employeeMapper2.findById(1);
        log.info("employee2 : {}", employee2);
    }

    /**
     * 二级缓存：（全局缓存）
     *
     * @throws Exception
     * @author colg
     */
    @Test
    public void testSecondLevelCache() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = employeeMapper.findById(1);
        log.info("employee : {}", employee);
        sqlSession.close();
        log.info("----------------------------------------------------------------------------------------------------");

        // 第二次查询是从二级缓存中拿到的数据，并没有发送新的sql
        sqlSession = sqlSessionFactory.openSession();
        employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        employee = employeeMapper.findById(1);
        log.info("employee : {}", employee);
        sqlSession.close();
    }

}
