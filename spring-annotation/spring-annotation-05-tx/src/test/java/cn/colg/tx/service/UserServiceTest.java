package cn.colg.tx.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.colg.tx.BaseTest;
import cn.colg.tx.bean.User;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 *
 * @author colg
 */
@Slf4j
public class UserServiceTest extends BaseTest {

    @Autowired
    private UserService userService;

    /**
     * Test method for {@link cn.colg.tx.service.UserService#insertUser(cn.colg.tx.bean.User)}.
     */
    @Test
    public final void testInsertUser() {
        User user = new User(IdUtil.simpleUUID(), RandomStringUtils.randomAlphabetic(3), RandomUtil.randomInt(18, 40));
        User insertUser = userService.insertUser(user);
        log.info("UserServiceTest.testInsertUser() >> 插入的数据 : {}", insertUser);
    }

}
