package cn.colg.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;

import cn.colg.BaseTest;
import cn.colg.bean.Users;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * resid 客户端 测试
 *
 * @author colg
 */
@Slf4j
public class JedisClientTest extends BaseTest {

    @Autowired
    private JedisClient jedisClient;

    /** Github 前缀 {@value} */
    public static final String KEY_PRE = "redis-github";
    /** Github USERS-API {@value} */
    public static final String API_ITEMS = "https://api.github.com/search/users?q=vue";

    /**
     * Test method for {@link cn.colg.cache.JedisClient#exists(java.lang.String)}.
     */
    @Test
    public final void testExists() {
        Boolean exists = jedisClient.exists(KEY_PRE);
        log.info("JedisClientTest.testExists() >> 检查给定 key 是否存在 : {}", exists);
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#del(java.lang.String[])}.
     */
    @Test
    public final void testDel() {
        Long del = jedisClient.del(KEY_PRE + "-users");
        log.info("JedisClientTest.testDel() >> 删除已存在的 key 。不存在的 key 会被忽略 : {}", del);
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#expire(java.lang.String, int)}.
     */
    @Test
    public final void testExpire() {
        Long expire = jedisClient.expire(KEY_PRE, 60);
        log.info("JedisClientTest.testExpire() >> 设置 key 的过期时间。key 过期后将不再可用。服务器自动删除 key : {}", expire);
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#ttl(java.lang.String)}.
     */
    @Test
    public final void testTtl() {
        Long ttl = jedisClient.ttl(KEY_PRE);
        log.info("JedisClientTest.testTtl() >> 以秒为单位返回 key 的剩余过期时间 : {}", ttl);
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#get(java.lang.String)}.
     */
    @Test
    public final void testGet() {
        String result = jedisClient.get(KEY_PRE + "-users");
        log.info("JedisClientTest.testGet() >> 获取指定 key 的值 : {}", result);
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#set(java.lang.String, java.lang.String)}.
     */
    @Test
    public final void testSet() {
        Users users = JSON.parseObject(HttpUtil.get(API_ITEMS), Users.class);
        String result = jedisClient.set(KEY_PRE + "-users", users.toString());
        log.info("JedisClientTest.testSet() >> 设置给定 key 的值。如果 key 已经存储其他值， SET 就覆写旧值，且无视类型 : {}", result);
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#incr(java.lang.String)}.
     */
    @Test
    public final void testIncr() {
        Long incr = jedisClient.incr(KEY_PRE + "-users-id");
        log.info("JedisClientTest.testIncr() >> 将 key 中储存的数字值增一。本操作的值限制在 64 位(bit)有符号数字表示之内 : {}", incr);
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#hexists(java.lang.String, java.lang.String)}.
     */
    @Test
    public final void testHexists() {
        Boolean hexists = jedisClient.hexists(KEY_PRE + "-items", "13379595");
        log.info("JedisClientTest.testHexists() >> 查看哈希表的指定字段是否存在 : {}", hexists);
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#hdel(java.lang.String, java.lang.String[])}.
     */
    @Test
    public final void testHdel() {
        Long hdel = jedisClient.hdel(KEY_PRE + "-items", "13379595");
        log.info("JedisClientTest.testHdel() >> 删除哈希表 key 中的一个或多个指定字段，不存在的字段将被忽略 : {}", hdel);
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#hget(java.lang.String, java.lang.String)}.
     */
    @Test
    public final void testHget() {
        String hget = jedisClient.hget(KEY_PRE + "-items", "13379595");
        log.info("JedisClientTest.testHget() >> 获取哈希表中指定字段的值 : {}", hget);
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#hset(java.lang.String, java.lang.String, java.lang.String)}.
     */
    @Test
    public final void testHset() {
        Users users = JSON.parseObject(HttpUtil.get(API_ITEMS), Users.class);
        users.getItems().forEach(e -> {
            // String hset = jedisClient.set(KEY_PRE + "-items" + ":" + e.getId(), e.toString());
            Long hset = jedisClient.hset(KEY_PRE + "-items", e.getId() + "", e.toString());
            log.info("JedisClientTest.testHset() >> 将哈希表 key 中的字段 field 的值设为 value 。如果哈希表不存在，一个新的哈希表被创建并进行 HSET 操作。如果字段已经存在于哈希表中，旧值将被覆盖 : {}", hset);
        });
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#hgetAll(java.lang.String)}.
     */
    @Test
    public final void testHgetAll() {
        Map<String, String> map = jedisClient.hgetAll(KEY_PRE + "-items");
        map.forEach((key, value) -> {
            log.info("JedisClientTest.testHgetAll() >> 获取在哈希表中指定 key 的所有字段和值 : [{}: {}]", key, value);
        });
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#hkeys(java.lang.String)}.
     */
    @Test
    public final void testHkeys() {
        Set<String> hkeys = jedisClient.hkeys(KEY_PRE + "-items");
        hkeys.forEach(e -> {
            log.info("JedisClientTest.testHkeys() >> 获取在哈希表中指定 key 的所有字段 : {}", e);
        });
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#hvals(java.lang.String)}.
     */
    @Test
    public final void testHvals() {
        List<String> hvals = jedisClient.hvals(KEY_PRE + "-items");
        hvals.forEach(e -> {
            log.info("JedisClientTest.testHvals() >> 获取在哈希表中指定 key 的所有值 : {}", e);
        });
    }

}
