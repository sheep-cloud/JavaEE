package cn.colg;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;

import lombok.extern.slf4j.Slf4j;

/**
 * Mapper 测试基类
 *
 * @author colg
 */
@Slf4j
public abstract class BaseMapperTest {

    /** 获取sqlSsession工厂 */
    protected SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception {
        // 从 XML 中构建SqlSessionFactory
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @After
    public void tearDown() throws Exception {
        log.info("tearDown() : {}", "----------------------------------------------------------------------------------------------------\n");
        // TODO colg [mybatis-07-cache :: 缓存机制 未完成]
    }
}
