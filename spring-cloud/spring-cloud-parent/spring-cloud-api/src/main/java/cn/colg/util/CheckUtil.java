package cn.colg.util;

import cn.colg.exception.CheckException;
import cn.hutool.core.util.StrUtil;

/**
 * 校验工具
 *
 * @author colg
 */
public final class CheckUtil {

    private CheckUtil() {}

    /**
     * 
     * 表达式的结果为false时，抛出校验异常
     * 
     * @param condition
     * @param msgKey
     * @param args
     */
    public static void check(boolean condition, String msg) {
        if (!condition) {
            fail(msg);
        }
    }

    /**
     * 字符串为空则抛出校验异常，空的定义如下: <br />
     * 1、为null <br />
     * 2、为""
     * 
     * @param str
     * @param msgKey
     * @param args
     */
    public static void notEmpty(String str, String msg) {
        if (StrUtil.isEmpty(str)) {
            fail(msg);
        }
    }

    /**
     * 字符串为空白则抛出校验异常， 空白的定义如下： <br />
     * 1、为null <br />
     * 2、为不可见字符（如空格） <br />
     * 3、""
     * 
     * @param str
     * @param msgKey
     * @param args
     */
    public static void notBlank(String str, String msg) {
        if (StrUtil.isBlank(str)) {
            fail(msg);
        }
    }

    /**
     * 对象为null，则抛出校验异常
     * 
     * @param obj
     * @param msgKey
     * @param args
     */
    public static void notNull(Object obj, String msg) {
        if (obj == null) {
            fail(msg);
        }
    }

    /**
     * 抛出校验错误异常
     *
     * @param msgKey
     */
    private static void fail(String msg) {
        /// 消息的参数化和国际化配置
        // Locale locale = LocaleContextHolder.getLocale();
        // msgKey = source.getMessage(msg, args, locale);
        throw new CheckException(msg);
    }
}
