package cn.colg.rbac.service;

import org.junit.Test;

import cn.colg.BaseTest;
import cn.colg.rbac.entity.User;
import cn.hutool.core.lang.Console;

/**
 * 
 *
 * @author colg
 */
public class UserServiceTest extends BaseTest {

    /**
     * Test method for {@link cn.colg.rbac.service.UserService#findUser(cn.colg.rbac.entity.User)}.
     */
    @Test
    public final void testFindUser() {
        User user = new User();
        user.setId("1");
        gen(user);
        Console.log(user);
    }

    private void gen(User user) {
        user.setUsername("admin");
    }
}
