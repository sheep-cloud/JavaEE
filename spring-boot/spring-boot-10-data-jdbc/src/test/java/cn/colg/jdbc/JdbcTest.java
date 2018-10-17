package cn.colg.jdbc;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.colg.SpringBoot10DataJdbcApplicationTests;
import lombok.extern.slf4j.Slf4j;

/**
 * Jdbc测试
 *
 * @author colg
 */
@Slf4j
public class JdbcTest extends SpringBoot10DataJdbcApplicationTests {

    @Autowired
    private DataSource dataSource;
    
    @Test
    public void testName() throws Exception {
        // driverClassName: class org.apache.tomcat.jdbc.pool.DataSource
        log.info("driverClassName: {}", dataSource.getClass());
        
        Connection connection = dataSource.getConnection();
        // ProxyConnection[PooledConnection[com.mysql.jdbc.JDBC4Connection@6d8796c1]]
        log.info(connection.toString());
        connection.close();
        // TODO: colg [Spring Boot]
    }
}
