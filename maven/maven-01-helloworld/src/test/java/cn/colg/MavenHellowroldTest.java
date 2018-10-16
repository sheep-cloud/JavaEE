package cn.colg;

import org.junit.Assert;
import org.junit.Test;

/**
 * MavenHellowrold 测试
 *
 * @author colg
 */
public class MavenHellowroldTest {

    /**
     * Test method for {@link cn.colg.MavenHellowrold#sayHello(java.lang.String)}.
     */
    @Test
    public void testSayHello() {
        MavenHellowrold helloMaven = new MavenHellowrold();
        String result = helloMaven.sayHello("maven");
        Assert.assertEquals("hello maven", result);
    }

}
