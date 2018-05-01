package cn.colg;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import lombok.extern.slf4j.Slf4j;

/**
 * Mapper 测试基类
 *
 * @author colg
 */
@Slf4j
public abstract class BaseMapperTest {

    /** 获取sqlSsession工厂 */
    private static SqlSessionFactory sqlSessionFactory;
    /** 获取sqlSession实例，提供给子类 */
    protected SqlSession sqlSession;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        // 从 XML 中构建SqlSessionFactory
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Before
    public void setUp() throws Exception {
        // 从SqlsessionFactory 中获取 sqlSession
        sqlSession = sqlSessionFactory.openSession();
    }

    @After
    public void tearDown() throws Exception {
        // 销毁sqlSession
        sqlSession.close();
        log.info("tearDown() : {}", "----------------------------------------------------------------------------------------------------\n");
    }
}
