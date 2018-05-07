package cn.clog.bean;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.clog.SpringBoot03ConfigApplicationTest;
import lombok.extern.slf4j.Slf4j;

/**
 * Phone 实体 '@ImportResource' 导入Spring的配置文件 测试
 *
 * @author colg
 */
@Slf4j
public class PhoneTest extends SpringBoot03ConfigApplicationTest{
    
    @Autowired
    private Phone phone;

    /**
     * Test method for {@link cn.clog.bean.Phone#Phone()}.
     */
    @Test
    public void testPhone() {
        log.info("testPhone() >> phone : {}", phone);
    }

}
