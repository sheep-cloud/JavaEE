package cn.clog.config;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.clog.SpringBoot03ConfigApplicationTest;
import cn.clog.bean.Cat;
import cn.clog.bean.Color;
import cn.clog.bean.Person;
import cn.clog.bean.Phone;
import lombok.extern.slf4j.Slf4j;

/**
 * 配置类；'@Configuration'： 指定当前类是一个配置类；就是来替代之前的Spring配置文件 测试
 *
 * @author colg
 */
@Slf4j
public class AppConfigTest extends SpringBoot03ConfigApplicationTest {

    @Autowired
    private Person person;
    @Autowired
    private Cat cat;
    @Autowired
    private Color color;
    @Autowired
    private Phone phone;

    /**
     * Test method for {@link cn.clog.config.AppConfig#person()}.
     */
    @Test
    public void testPerson() {
        log.info("testPerson() >> person : {}", person);
        log.info("testPerson() >> cat : {}", cat);
        log.info("testPerson() >> color : {}", color);
        log.info("testPerson() >> phone : {}", phone);
    }

}
