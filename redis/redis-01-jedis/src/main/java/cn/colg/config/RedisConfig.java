package cn.colg.config;

import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import cn.colg.cache.JedicClusterCache;
import cn.colg.cache.JedisPoolCache;
import cn.hutool.core.collection.CollUtil;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

/**
 * redis 配置，使用策略模式，方便调用；
 * 
 * <pre>
 * 注意：单机版本与集群版本不能共存；
 * 若使用单机版：注释掉集群版，
 * 若使用集群版：注释掉单机版；
 * 如果都不注释：默认使用单机版。
 * </pre>
 *
 * @author colg
 */
@Configuration
public class RedisConfig {

    /**
     * redis，单机版配置
     *
     * @return
     */
    @Conditional(JedisPoolCatchCondition.class)
    @Bean
    public JedisPoolCache jedisClientPool() {
        JedisPoolCache jedisClientPool = new JedisPoolCache();
        String host = "192.168.21.103";
        JedisPool jedisPool = new JedisPool(host, 6379);
        jedisClientPool.setJedisPool(jedisPool);
        return jedisClientPool;
    }
    

    /**
     * redis，集群版配置
     *
     * @return
     */
    @Conditional(JedicClusterCacheCondition.class)
    @Bean
    public JedicClusterCache jedisClientCluster() {
        JedicClusterCache jedisClientCluster = new JedicClusterCache();
        String host = "192.168.21.103";
        Set<HostAndPort> nodes = CollUtil.newHashSet(
                new HostAndPort(host, 7000),
                new HostAndPort(host, 7001),
                new HostAndPort(host, 7002),
                new HostAndPort(host, 7003),
                new HostAndPort(host, 7004),
                new HostAndPort(host, 7005)
            );
        JedisCluster jedisCluster = new JedisCluster(nodes);
        jedisClientCluster.setJedisCluster(jedisCluster);
        return jedisClientCluster;
    }

}
