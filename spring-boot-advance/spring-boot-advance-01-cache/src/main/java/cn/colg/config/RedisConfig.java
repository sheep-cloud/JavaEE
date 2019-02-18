package cn.colg.config;

import java.net.UnknownHostException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;

import cn.colg.bean.Department;
import cn.colg.bean.Employee;

/**
 * Redis 配置
 *
 * @author colg
 */
@Configuration
public class RedisConfig {

    /**
     * 修改默认的序列化机制
     *
     * @param redisConnectionFactory
     * @return
     * @throws UnknownHostException
     * @author colg
     */
    @Bean
    public RedisTemplate<Object, Employee> empRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Employee> template = new RedisTemplate<Object, Employee>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setDefaultSerializer(new FastJsonRedisSerializer<>(Employee.class));
        return template;
    }

    /**
     * 定制缓存规则
     * 
     * <pre>
     * `@Primary`: 默认使用; 多个缓存管理器的情况下需要标注
     * </pre>
     *
     * @param redisTemplate
     * @return
     * @author colg
     */
    @Primary
    @Bean
    public RedisCacheManager empCacheManager(RedisTemplate<Object, Employee> empRedisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(empRedisTemplate);
        // 使用前缀，默认会将CacheName作为key的前缀
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }

    @Bean
    public RedisTemplate<Object, Department> deptRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Department> template = new RedisTemplate<Object, Department>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setDefaultSerializer(new FastJsonRedisSerializer<>(Department.class));
        return template;
    }

    @Bean
    public RedisCacheManager deptCacheManager(RedisTemplate<Object, Department> deptRedisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(deptRedisTemplate);
        // 使用前缀，默认会将CacheName作为key的前缀
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }
}
