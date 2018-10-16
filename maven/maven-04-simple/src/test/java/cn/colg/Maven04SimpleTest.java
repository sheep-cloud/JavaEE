package cn.colg;

import org.junit.Assert;
import org.junit.Test;

/**
 * Maven04Simple 测试
 *
 * @author colg
 */
public class Maven04SimpleTest {

    /**
     * Test method for {@link cn.colg.Maven04Simple#sayHello(java.lang.String)}.
     */
    @Test
    public void testSayHello() {
        Maven04Simple maven04Simple = new Maven04Simple();
        String result = maven04Simple.sayHello("maven");
        Assert.assertEquals("hello maven", result);
    }

}
