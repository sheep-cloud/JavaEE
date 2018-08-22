package cn.colg.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * entity 基类
 *
 * @author colg
 */
public abstract class BaseEntity {

    @Override
    public String toString() {
        return JSON.toJSONString(this,
            // JSON 格式化
            SerializerFeature.PrettyFormat,
            // 日期时间 毫秒 -> "yyyy-MM-dd HH:mm:ss"
            SerializerFeature.WriteDateUseDateFormat,
            // 输出值为null的字段
            SerializerFeature.WriteMapNullValue,
            // 消除对同一对象循环引用
            SerializerFeature.DisableCircularReferenceDetect);
    }
}
