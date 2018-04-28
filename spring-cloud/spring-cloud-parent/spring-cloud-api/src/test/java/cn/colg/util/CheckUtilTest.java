package cn.colg.util;

import static cn.colg.util.CheckUtil.*;

import org.junit.Test;

import cn.colg.exception.CheckException;

/**
 * 校验工具 测试
 *
 * @author colg
 */
public final class CheckUtilTest {

    /**
     * Test method for {@link cn.colg.util.CheckUtil#check(boolean, java.lang.String)}.
     */
    @Test(expected = CheckException.class)
    public void testCheck() {
        check(1 > 1, "表达式为false");
    }

    /**
     * Test method for {@link cn.colg.util.CheckUtil#notEmpty(java.lang.String, java.lang.String)}.
     */
    @Test(expected = CheckException.class)
    public void testNotEmpty() {
        notEmpty("", "字符串不能为空");
    }

    /**
     * Test method for {@link cn.colg.util.CheckUtil#notBlank(java.lang.String, java.lang.String)}.
     */
    @Test(expected = CheckException.class)
    public void testNotBlank() {
        notBlank("", "字符串不能为空白");
    }

    /**
     * Test method for {@link cn.colg.util.CheckUtil#notNull(java.lang.Object, java.lang.String)}.
     */
    @Test(expected = CheckException.class)
    public void testNotNull() {
        notNull(null, "对象不能为null！");
    }

}
