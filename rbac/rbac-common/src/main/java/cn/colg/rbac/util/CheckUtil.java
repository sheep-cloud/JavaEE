package cn.colg.rbac.util;

import cn.colg.rbac.exception.CheckException;

/**
 * 校验工具
 *
 * @author colg
 */
public final class CheckUtil {

    private CheckUtil() {}

    /**
     * 表达式的结果为false时，抛出校验异常
     *
     * @param condition 校验条件
     * @param msg 错误消息提示
     */
    public static void check(boolean condition, String msg) {
        if (!condition) {
            fail(msg);
        }
    }

    /**
     * 对象为null或字符串为空白则抛出校验异常，空白的定义如下： <br>
     * 1、为null <br>
     * 2、为不可见字符（如空格）<br>
     * 3、""<br>
     *
     * @param value 需要校验的对象或字符串
     * @param msg 错误消息提示
     */
    public static void notNull(Object value, String msg) {
        if (value == null) {
            fail(msg);
        }
        if (value instanceof String) {
            String str = (String)value;
            if ("".equals(str.trim())) {
                fail(msg);
            }
        }
    }

    /**
     * 抛出校验异常
     *
     * @param msg 错误提示消息
     */
    private static void fail(String msg) {
        throw new CheckException(msg);
    }
}
