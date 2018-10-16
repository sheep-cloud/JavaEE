package cn.colg;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Maven02Seed 测试
 *
 * @author colg
 */
public class Maven02SeedTest extends TestCase {

    /**
     * Test method for {@link cn.colg.Maven02Seed#sayHello(java.lang.String)}.
     */
    public void testSayHello() {
        Maven02Seed helloMaven = new Maven02Seed();
        String result = helloMaven.sayHello("maven");
        Assert.assertEquals("hello maven", result);
    }

}
