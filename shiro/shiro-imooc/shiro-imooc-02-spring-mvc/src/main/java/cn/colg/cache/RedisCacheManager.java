package cn.colg.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

import lombok.Getter;
import lombok.Setter;

/**
 * redis 缓存管理
 *
 * @author colg
 * @param <K>
 * @param <V>
 */
@Getter
@Setter
public class RedisCacheManager<K, V> implements CacheManager{
    
    private RedisCache<K, V> redisCache;

    @SuppressWarnings({"unchecked", "hiding"})
    @Override
    public <K, V> Cache<K, V> getCache(String arg0) throws CacheException {
        return (Cache<K, V>)redisCache;
    }

}
