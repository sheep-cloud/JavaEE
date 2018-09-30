package cn.colg.rbac.util;

import cn.colg.rbac.exception.CheckException;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;

import java.util.Collection;
import java.util.Map;

/**
 * 校验工具
 *
 * @author colg
 */
public final class CheckUtil {

    private CheckUtil() {}

    /**
     * 表达式的结果为 false 时，抛出校验异常
     *
     * @param bool 校验条件
     * @param message 消息提示
     * @author colg
     */
    public static void checkTrue(boolean bool, String message) {
        if (!bool) {
            throwFail(message);
        }
    }

    /**
     * 表达式的结果为 true 时，抛出校验异常
     *
     * @param bool 校验条件
     * @param message 消息提示
     * @author colg
     */
    public static void checkFalse(boolean bool, String message) {
        if (bool) {
            throwFail(message);
        }
    }

    /**
     * Object 为null时，抛出校验异常
     * 
     * @param value 校验的对象
     * @param message 消息提示
     * @author colg
     */
    public static void checkNotNull(Object value, String message) {
        if (value == null) {
            throwFail(message);
        }
    }

    /**
     * 对象不为 null 时，抛出校验异常
     *
     * @param value 校验的对象
     * @param message 消息提示
     * @author colg
     */
    public static void checkNull(Object value, String message) {
        if (value != null) {
            throwFail(message);
        }
    }

    /**
     * 字符串为空白时，抛出校验异常，空白的定义如下：<br>
     * 1、String: null 2、String: 为不可见字符（如空格） <br>
     * 3、String: "" <br>
     *
     * @param value 校验的字符串
     * @param message 错误消息
     * @author colg
     */
    public static void checkNotNull(String value, String message) {
        if (StrUtil.isBlank(value)) {
            throwFail(message);
        }
    }

    /**
     * 字符串为非空白时，抛出校验异常，空白的定义如下：<br>
     * 1、String: null 2、String: 为不可见字符（如空格） <br>
     * 3、String: "" <br>
     * 
     * @param value 校验的字符串
     * @param message 消息提示
     * @author colg
     */
    public static void checkNull(String value, String message) {
        if (StrUtil.isNotBlank(value)) {
            throwFail(message);
        }
    }

    /**
     * 为空时，抛出校验异常
     *
     * @param collection 校验的集合
     * @param message 消息提示
     * @author colg
     */
    public static void checkNotNull(Collection<?> collection, String message) {
        if (CollUtil.isEmpty(collection)) {
            throwFail(message);
        }
    }

    /**
     * Collection 为非空时，抛出校验异常
     *
     * @param collection 校验的集合
     * @param message 消息提示
     * @author colg
     */
    public static void checkNull(Collection<?> collection, String message) {
        if (CollUtil.isNotEmpty(collection)) {
            throwFail(message);
        }
    }

    /**
     * Map 为空时，抛出校验异常
     *
     * @param map 校验的map
     * @param message 消息提示
     * @author colg
     */
    public static void checkNotNull(Map<?, ?> map, String message) {
        if (MapUtil.isEmpty(map)) {
            throwFail(message);
        }
    }

    /**
     * Map 为非空时，抛出校验异常
     *
     * @param map 校验的map
     * @param message 消息提示
     * @author colg
     */
    public static void checkNull(Map<?, ?> map, String message) {
        if (MapUtil.isNotEmpty(map)) {
            throwFail(message);
        }
    }

    /**
     * 抛出校验异常
     *
     * @param message 错误提示消息
     * @author colg
     */
    public static void throwFail(String message) {
        throw new CheckException(message);
    }

    /**
     * 抛出运行时异常
     *
     * @param runtimeException 异常类型
     * @author colg
     */
    public static void throwFail(RuntimeException runtimeException) {
        throw runtimeException;
    }
}
