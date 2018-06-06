package cn.colg.config;

import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

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
 * <strong>注意：单机版本与集群版本不能共存。</strong>
 * </pre>
 *
 * @author colg
 */
@PropertySource("classpath:redis.properties")
@Configuration
public class RedisConfig {

    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private int port;

    @Value("${redis.cluster.host}")
    private String clusterHost;
    @Value("${redis.cluster.port1}")
    private int clusterPort1;
    @Value("${redis.cluster.port2}")
    private int clusterPort2;
    @Value("${redis.cluster.port3}")
    private int clusterPort3;
    @Value("${redis.cluster.port4}")
    private int clusterPort4;
    @Value("${redis.cluster.port5}")
    private int clusterPort5;
    @Value("${redis.cluster.port6}")
    private int clusterPort6;
    
    /**
     * redis，单机版配置
     *
     * @return
     * @author colg
     */
    @Conditional(PoolCacheConditional.class)
    @Bean
    public JedisPoolCache jedisClientPool() {
        return new JedisPoolCache().setJedisPool(new JedisPool(host, port));
    }

    /**
     * redis，集群版配置
     *
     * @return
     * @author colg
     */
    @Conditional(ClusterCacheConditional.class)
    @Bean
    public JedicClusterCache jedisClientCluster() {
        Set<HostAndPort> nodes = CollUtil.newHashSet(
            new HostAndPort(clusterHost, clusterPort1),
            new HostAndPort(clusterHost, clusterPort2),
            new HostAndPort(clusterHost, clusterPort3),
            new HostAndPort(clusterHost, clusterPort4),
            new HostAndPort(clusterHost, clusterPort5),
            new HostAndPort(clusterHost, clusterPort6)
        );
        return new JedicClusterCache().setJedisCluster(new JedisCluster(nodes));
    }
    
}

