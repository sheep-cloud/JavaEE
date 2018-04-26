package cn.colg;

import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring Boot 单元测试基类
 *
 * @author colg
 */
@MapperScan("cn.colg.dao")
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class ProviderDept8001Test {

}
