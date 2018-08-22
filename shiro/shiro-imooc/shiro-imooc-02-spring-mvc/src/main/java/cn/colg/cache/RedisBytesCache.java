package cn.colg.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.SerializationUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * redis 缓存
 *
 * @author colg
 */
@Slf4j
public class RedisBytesCache<K, V> implements Cache<K, V> {

    @Autowired
    private JedisClient jedisClient;

    /** 缓存前缀 */
    private static final String CACHE_PREFIX = "imooc-cache:";

    /**
     * 获取带前缀的key
     *
     * @param k
     * @return
     * @author colg
     */
    private byte[] getKey(K k) {
        log.info("从 redis 获取权限数据");
        if (k instanceof String) {
            return (CACHE_PREFIX + k).getBytes();
        }
        return SerializationUtils.serialize(k);
    }

    /**
     * 清空缓存
     *
     * @throws CacheException
     */
    @Override
    public void clear() throws CacheException {}

    /**
     * 
     * 从缓存中获得对象，当对象不在缓存中或已经过期返回<code>null</code>
     * 
     * @param k 键
     * @return 键对应的对象
     * @throws CacheException
     */
    @SuppressWarnings("unchecked")
    @Override
    public V get(K k) throws CacheException {
        byte[] value = jedisClient.get(getKey(k));
        if (value != null) {
            return (V)SerializationUtils.deserialize(value);
        }
        return null;
    }

    /**
     * 缓存的key的集合
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public Set<K> keys() {
        byte[] key = getKey((K)"*");
        return (Set<K>)jedisClient.keys(key);
    }

    /**
     * 
     * 将对象加入到缓存，使用默认失效时长
     * 
     * @param k 键
     * @param v 缓存的对象
     * @return 缓存的对象
     * @throws CacheException
     */
    @Override
    public V put(K k, V v) throws CacheException {
        byte[] key = getKey(k);
        byte[] value = SerializationUtils.serialize(v);
        jedisClient.set(key, value);
        jedisClient.expire(key, 600);
        return v;
    }

    /**
     * 
     * 从缓存中移除对象
     * 
     * @param k 键
     * @return 键对应的对象
     * @throws CacheException
     */
    @SuppressWarnings("unchecked")
    @Override
    public V remove(K k) throws CacheException {
        byte[] key = getKey(k);
        byte[] value = getKey(k);
        jedisClient.del(key);
        return (V)value;
    }

    /**
     * 缓存的对象数量
     *
     * @return 缓存的对象数量
     */
    @Override
    public int size() {
        return keys().size();
    }

    /**
     * 缓存的对象集合
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public Collection<V> values() {
        Set<K> keys = keys();
        Collection<V> values = new ArrayList<>();
        for (K k : keys) {
            byte[] value = jedisClient.get(getKey(k));
            values.add((V)value);
        }
        return values;
    }

}
