package cn.colg.config;

import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
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
 * 注意：单机版本与集群版本不能共存；
 * 若使用单机版：注释掉集群版，
 * 若使用集群版：注释掉单机版；
 * 如果都不注释：默认使用单机版。
 * </pre>
 *
 * @author colg
 */
@PropertySource(value = {"classpath:/redis.properties"})
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
     */
    @Bean
    public JedisPoolCache jedisClientPool() {
        JedisPoolCache jedisClientPool = new JedisPoolCache();
        JedisPool jedisPool = new JedisPool(host, port);
        jedisClientPool.setJedisPool(jedisPool);
        return jedisClientPool;
    }

    /**
     * redis，集群版配置
     *
     * @return
     */
    @Bean
    public JedicClusterCache jedisClientCluster() {
        JedicClusterCache jedisClientCluster = new JedicClusterCache();
        Set<HostAndPort> nodes = CollUtil.newHashSet(
                new HostAndPort(clusterHost, clusterPort1),
                new HostAndPort(clusterHost, clusterPort2),
                new HostAndPort(clusterHost, clusterPort3),
                new HostAndPort(clusterHost, clusterPort4),
                new HostAndPort(clusterHost, clusterPort5),
                new HostAndPort(clusterHost, clusterPort6)
            );
        JedisCluster jedisCluster = new JedisCluster(nodes);
        jedisClientCluster.setJedisCluster(jedisCluster);
        return jedisClientCluster;
    }

}
