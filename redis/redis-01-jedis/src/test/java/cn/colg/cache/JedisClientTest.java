package cn.colg.cache;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;

import cn.colg.BaseTest;
import cn.colg.bean.Users;
import cn.hutool.core.lang.Console;
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

    /** Github 前缀：{@value} */
    public static final String KEY_PRE = "github";
    /** Github API：{@value} */
    public static final String API = "https://api.github.com/search/users?q=vue";

    /**
     * Test method for {@link cn.colg.cache.JedisClient#exists(java.lang.String)}.
     */
    @Test
    public final void testExists() {
        String string = jedisClient.get("2018-05-28:14");
        Console.log(string);
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#del(java.lang.String[])}.
     */
    @Test
    public final void testDel() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#expire(java.lang.String, int)}.
     */
    @Test
    public final void testExpire() {
        Long result = jedisClient.del("https://api.github.com/search/users?q=vue:users", "hello", "hash1", "github:users", "github", "USER:天气", "USER");
        log.info("JedisClientTest.testExpire() >> result : {}", result);
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#ttl(java.lang.String)}.
     */
    @Test
    public final void testTtl() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#get(java.lang.String)}.
     */
    @Test
    public final void testGet() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#set(java.lang.String, java.lang.String)}.
     */
    @Test
    public final void testSet() {
        Users users = JSON.parseObject(HttpUtil.get(API), Users.class);
        String result = jedisClient.set(KEY_PRE, users.toString());
        log.info("JedisClientTest.testSet() >> set : {}", result);
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#incr(java.lang.String)}.
     */
    @Test
    public final void testIncr() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#hexists(java.lang.String, java.lang.String)}.
     */
    @Test
    public final void testHexists() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#hdel(java.lang.String, java.lang.String[])}.
     */
    @Test
    public final void testHdel() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#hget(java.lang.String, java.lang.String)}.
     */
    @Test
    public final void testHget() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#hset(java.lang.String, java.lang.String, java.lang.String)}.
     */
    @Test
    public final void testHset() {
        Users users = JSON.parseObject(HttpUtil.get(API), Users.class);
        users.getItems().forEach(e -> {
            jedisClient.hset(KEY_PRE + ":users", e.getId() + "", e.toString());
        });
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#hgetAll(java.lang.String)}.
     */
    @Test
    public final void testHgetAll() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#hkeys(java.lang.String)}.
     */
    @Test
    public final void testHkeys() {

        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#hvals(java.lang.String)}.
     */
    @Test
    public final void testHvals() {
        fail("Not yet implemented"); // TODO
    }

}
