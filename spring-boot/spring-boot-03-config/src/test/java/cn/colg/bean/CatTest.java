package cn.colg.bean;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.colg.SpringBoot03ConfigApplicationTest;
import lombok.extern.slf4j.Slf4j;

/**
 * Cat 实体 '@Value' 获取值 测试
 *
 * @author colg
 */
@Slf4j
public class CatTest extends SpringBoot03ConfigApplicationTest {

    @Autowired
    private Cat cat;

    /**
     * Test method for {@link cn.colg.bean.Cat#Cat()}.
     */
    @Test
    public void testCat() {
        log.info("cat : {}", cat);
    }

}
