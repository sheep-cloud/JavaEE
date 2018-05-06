package cn.clog.bean;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.clog.SpringBoot03ConfigApplicationTest;
import lombok.extern.slf4j.Slf4j;

/**
 * Person 实体 测试类
 *
 * @author colg
 */
@Slf4j
public class PersonTest extends SpringBoot03ConfigApplicationTest {

    @Autowired
    private Person person;

    /**
     * Test method for {@link cn.clog.bean.Person#Person()}.
     */
    @Test
    public void testPerson() {
        log.info("testPerson() >> person : {}", person);
    }

}
