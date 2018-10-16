package cn.colg;

import org.junit.Assert;
import org.junit.Test;

/**
 * HelloMaven 测试
 *
 * @author colg
 */
public class HelloMavenTest {

    /**
     * Test method for {@link cn.colg.HelloMaven#sayHello(java.lang.String)}.
     */
    @Test
    public void testSayHello() {
        HelloMaven helloMaven = new HelloMaven();
        String result = helloMaven.sayHello("maven");
        Assert.assertEquals("hello maven", result);
    }

}
