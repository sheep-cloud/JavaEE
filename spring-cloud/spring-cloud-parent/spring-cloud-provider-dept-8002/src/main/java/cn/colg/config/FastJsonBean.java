package cn.colg.config;

import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

import cn.hutool.core.collection.CollUtil;

/**
 * 注入fastJson解析
 *
 * @author colg
 */
@Configuration
public class FastJsonBean {

    /**
     * 配置HttpMessageConverters用于读取或写入请求或响应的主体。 如果未添加转换器，则会注册转换器的默认列表。</br>
     * 
     * <a href="http://www.cnblogs.com/zf29506564/p/6669870.html">fastjson SerializerFeature详解</a>
     *
     * @return
     */
    @Bean
    public HttpMessageConverters httpMessageConverters() {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        converter.setSupportedMediaTypes(CollUtil.newArrayList(MediaType.APPLICATION_JSON_UTF8));
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
            // 日期时间 毫秒 -> "yyyy-MM-dd HH:mm:ss"
            SerializerFeature.WriteDateUseDateFormat,
            // 输出值为null的字段
            SerializerFeature.WriteMapNullValue,
            // String null -> ""
            SerializerFeature.WriteNullStringAsEmpty,
            // List null -> []
            SerializerFeature.WriteNullListAsEmpty,
            // 消除对同一对象循环引用
            SerializerFeature.DisableCircularReferenceDetect
        );
        converter.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters(converter);
    }
}
