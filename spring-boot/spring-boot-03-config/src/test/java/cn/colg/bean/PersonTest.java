package cn.colg.bean;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.colg.SpringBoot03ConfigApplicationTest;
import lombok.extern.slf4j.Slf4j;

/**
 * Person 实体 '@ConfigurationProperties' 获取值 测试
 *
 * @author colg
 */
@Slf4j
public class PersonTest extends SpringBoot03ConfigApplicationTest {

    @Autowired
    private Person person;

    /**
     * Test method for {@link cn.colg.bean.Person#Person()}.
     */
    @Test
    public void testPerson() {
        log.info("person : {}", person);
    }

}
