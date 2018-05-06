package cn.colg.jedis;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.colg.cache.JedisClient;
import cn.colg.config.RedisConfig;
import lombok.extern.slf4j.Slf4j;

/**
 * jedisClient 测试
 *
 * @author colg
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RedisConfig.class})
public class JedisClientTest {

    @Autowired
    private JedisClient jedisClient;

    /**
     * Test method for {@link cn.colg.cache.JedisClient#set(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testSet() {
        String result = jedisClient.set("hello", "world");
        log.info("testSet() >> result : {}", result);
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#get(java.lang.String)}.
     */
    @Test
    public void testGet() {
        log.info("testGet() >> jedisClient : {}", jedisClient);
        String result = jedisClient.get("aaa");
        log.info("testGet() >> result : {}", result);
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#del(java.lang.String[])}.
     */
    @Test
    public void testDel() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#exists(java.lang.String)}.
     */
    @Test
    public void testExists() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#expire(java.lang.String, int)}.
     */
    @Test
    public void testExpire() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#ttl(java.lang.String)}.
     */
    @Test
    public void testTtl() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#incr(java.lang.String)}.
     */
    @Test
    public void testIncr() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#hset(java.lang.String, java.lang.String, java.lang.String)}.
     */
    @Test
    public void testHset() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#hget(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testHget() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#hdel(java.lang.String, java.lang.String[])}.
     */
    @Test
    public void testHdel() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#hexists(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testHexists() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#hvals(java.lang.String)}.
     */
    @Test
    public void testHvals() {
        fail("Not yet implemented"); // TODO
    }

}
