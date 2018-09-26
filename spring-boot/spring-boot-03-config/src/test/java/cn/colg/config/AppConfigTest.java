package cn.colg.config;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.colg.SpringBoot03ConfigApplicationTest;
import cn.colg.bean.Cat;
import cn.colg.bean.Color;
import cn.colg.bean.Person;
import cn.colg.bean.Phone;
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
     * Test method for {@link cn.colg.config.AppConfig#person()}.
     */
    @Test
    public void testPerson() {
        log.info("person : {}", person);
        log.info("cat : {}", cat);
        log.info("color : {}", color);
        log.info("phone : {}", phone);
    }

}
