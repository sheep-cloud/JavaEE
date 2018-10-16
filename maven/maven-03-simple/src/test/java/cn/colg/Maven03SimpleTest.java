package cn.colg;

import org.junit.Assert;
import org.junit.Test;

/**
 * Maven03Simple
 *
 * @author colg
 */
public class Maven03SimpleTest {

    /**
     * Test method for {@link cn.colg.Maven03Simple#sayHello(java.lang.String)}.
     */
    @Test
    public void testSayHello() {
        Maven03Simple helloMaven = new Maven03Simple();
        String result = helloMaven.sayHello("maven");
        Assert.assertEquals("hello maven", result);
    }

}
