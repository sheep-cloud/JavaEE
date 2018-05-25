package cn.colg.jedis;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.colg.BaseTest;
import cn.colg.cache.JedisClient;
import lombok.extern.slf4j.Slf4j;

/**
 * jedisClient 测试
 *
 * @author colg
 */
@Slf4j
public class JedisClientTest extends BaseTest {

    @Autowired
    private JedisClient jedisClient;

    /**
     * Test method for {@link cn.colg.cache.JedisClient#set(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testSet() {
        String key = "hello";
        String value = "world";
        String result = jedisClient.set(key, value);
        log.info("testSet() >> result : {}", result);
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#get(java.lang.String)}.
     */
    @Test
    public void testGet() {
        String key = "hello";
        String result = jedisClient.get(key);
        log.info("testGet() >> result : {}", result);
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#del(java.lang.String[])}.
     */
    @Test
    public void testDel() {
        String key = "hello";
        Long del = jedisClient.del(key);
        log.info("JedisClientTest.testDel() >> del : {}", del);
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#exists(java.lang.String)}.
     */
    @Test
    public void testExists() {
        String key = "hello";
        Boolean exists = jedisClient.exists(key);
        log.info("JedisClientTest.testExists() >> exists : {}", exists);
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#expire(java.lang.String, int)}.
     */
    @Test
    public void testExpire() {
        String key = "hello";
        int seconds = 3600;
        Long expire = jedisClient.expire(key, seconds);
        log.info("JedisClientTest.testExpire() >> expire : {}", expire);
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#ttl(java.lang.String)}.
     */
    @Test
    public void testTtl() {
        String key = "hello";
        Long ttl = jedisClient.ttl(key);
        log.info("JedisClientTest.testTtl() >> ttl : {}", ttl);
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#incr(java.lang.String)}.
     */
    @Test
    public void testIncr() {
        String key = "hello_id";
        Long incr = jedisClient.incr(key);
        log.info("JedisClientTest.testIncr() >> incr : {}", incr);
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#hset(java.lang.String, java.lang.String, java.lang.String)}.
     */
    @Test
    public void testHset() {
        String key = "employee";
        String field = "empName";
        String value = "Jack";
        Long hset = jedisClient.hset(key, field, value);
        log.info("JedisClientTest.testHset() >> hset : {}", hset);
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#hget(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testHget() {
        String key = "employee";
        String field = "empName";
        String hget = jedisClient.hget(key, field);
        log.info("JedisClientTest.testHget() >> hget : {}", hget);
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#hdel(java.lang.String, java.lang.String[])}.
     */
    @Test
    public void testHdel() {
        String key = "employee";
        String field = "empName";
        Long hdel = jedisClient.hdel(key, field);
        log.info("JedisClientTest.testHdel() >> hdel : {}", hdel);
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#hexists(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testHexists() {
        String key = "employee";
        String field = "empName";
        Boolean hexists = jedisClient.hexists(key, field);
        log.info("JedisClientTest.testHexists() >> hexists : {}", hexists);
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#hvals(java.lang.String)}.
     */
    @Test
    public void testHvals() {
        String key = "employee";
        List<String> hvals = jedisClient.hvals(key);
        log.info("JedisClientTest.testHvals() >> hvals : {}", hvals);
    }

}
