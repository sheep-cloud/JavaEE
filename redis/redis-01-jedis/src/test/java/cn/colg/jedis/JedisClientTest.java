package cn.colg.jedis;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;

import cn.colg.BaseTest;
import cn.colg.bean.User;
import cn.colg.cache.JedisClient;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpUtil;
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
        String key = DateUtil.today() + ":" + DateUtil.hour(new Date(), true);
        String value = HttpUtil.get("https://api.thinkpage.cn/v2/weather/all.json?city=" + 101190408 + "&language=zh-chs&unit=c&aqi=city&key=VQZU1H5TOT");
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
        String key = "USER";
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

        List<User> list = CollUtil.newArrayList(
            new User(System.currentTimeMillis() + "", "Jack", SecureUtil.md5("123456"), new Date()),
            new User(System.currentTimeMillis() + "", "Jack", SecureUtil.md5("123456"), new Date()),
            new User(System.currentTimeMillis() + "", "Jack", SecureUtil.md5("123456"), new Date()),
            new User(System.currentTimeMillis() + "", "Jack", SecureUtil.md5("123456"), new Date()),
            new User(System.currentTimeMillis() + "", "Jack", SecureUtil.md5("123456"), new Date()),
            new User(System.currentTimeMillis() + "", "Jack", SecureUtil.md5("123456"), new Date()),
            new User(System.currentTimeMillis() + "", "Jack", SecureUtil.md5("123456"), new Date()),
            new User(System.currentTimeMillis() + "", "Jack", SecureUtil.md5("123456"), new Date()),
            new User(System.currentTimeMillis() + "", "Jack", SecureUtil.md5("123456"), new Date()),
            new User(System.currentTimeMillis() + "", "Jack", SecureUtil.md5("123456"), new Date())
        );
        String key = "weather:forecast";
        for (User user : list) {
            String field = user.getId();
            String value = JSON.toJSONString(user);
            Long hset = jedisClient.hset(key, field, value);
            log.info("JedisClientTest.testHset() >> hset : {}", hset);
        }

    }

    /**
     * Test method for {@link cn.colg.cache.JedisClient#hget(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testHget() {
        String key = "weather:forecast";
        String field = "1527492172197";
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

    @Test
    public void testName() throws Exception {
        String[] strs = new String[] {"abc", "def", "ghj"};
        String join = ArrayUtil.join(strs, ",");
        Console.log(join);
    }
}
