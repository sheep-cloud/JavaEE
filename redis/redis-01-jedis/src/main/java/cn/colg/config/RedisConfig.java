package cn.colg.config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import cn.colg.cache.JedicClusterCache;
import cn.colg.cache.JedisPoolCache;
import cn.hutool.core.util.StrUtil;
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
@PropertySource("classpath:config/redis.properties")
@Configuration
public class RedisConfig {

    // TODO colg [redis 使用 yml 注入属性，接收 int 异常，原因不明]

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.cluster.nodes}")
    private String clusterNodes;

    @Value("${spring.redis.pool.max-wait}")
    private long maxWait;
    @Value("${spring.redis.pool.min-idle}")
    private int minIdle;
    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.pool.max-active}")
    private int maxActive;

    /**
     * redis 单机配置
     *
     * @return
     * @author colg
     */
    @Conditional(PoolCacheConditional.class)
    @Bean
    public JedisPoolCache jedisClientPool() {
        return new JedisPoolCache().setJedisPool(new JedisPool(poolConfig(), host, port));
    }

    /**
     * redis 集群配置
     *
     * @return
     * @author colg
     */
    @Conditional(ClusterCacheConditional.class)
    @Bean
    public JedicClusterCache jedisClientCluster() {
        Set<HostAndPort> nodes = new HashSet<>(9);
        StrUtil.split(clusterNodes, ',').forEach(clusterNode -> {
            List<String> node = StrUtil.split(clusterNode, ':');
            nodes.add(new HostAndPort(node.get(0), Integer.parseInt(node.get(1))));
        });
        return new JedicClusterCache().setJedisCluster(new JedisCluster(nodes, poolConfig()));
    }

    /**
     * redis 连接池
     *
     * @return
     * @author colg
     */
    private GenericObjectPoolConfig poolConfig() {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxWaitMillis(maxWait);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMaxIdle(maxIdle);
        return poolConfig;
    }

}
