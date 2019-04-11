package cn.colg.redis;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.alibaba.fastjson.JSON;

import cn.colg.SpringBootAdvance01CacheApplicationTests;
import cn.colg.bean.Employee;
import cn.colg.mapper.EmployeeMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * Redis 测试类
 *
 * @author colg
 */
@Slf4j
public class RedisTest extends SpringBootAdvance01CacheApplicationTests {
    
    @Autowired
    private EmployeeMapper employeeMapper;

    /** 操作k-v都是字符串的 */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /** 操作k-v都是对象的 */
    @Autowired
    private RedisTemplate<Object, Employee> empRedisTemplate;

    /**
     * Redis常见的五大数据类型: <br>
     * 
     * String(字符串), List(列表), Set(集合), Hash(散列), Zset(有序集合)
     *
     * @throws Exception
     * @author colg
     */
    @Test
    public void testSet() throws Exception {
        String key = "msg";
        stringRedisTemplate.opsForValue()
                           .append(key, "hello");
         
        String result = stringRedisTemplate.opsForValue()
                                           .get(key);
        log.info("result: {}", result);
        log.info("------------------------------------------------------------------------------------------");
        
        key = "myList";
        stringRedisTemplate.opsForList()
                           .leftPush(key, "1");
        stringRedisTemplate.opsForList()
                           .leftPush(key, "2");
    }
    
    @Test
    public void testGet() throws Exception {
        String key = "emp-01";
        stringRedisTemplate.opsForValue()
                           .set(key, JSON.toJSONString(employeeMapper.getEmpById(1)));
        
        String jsonStr = stringRedisTemplate.opsForValue()
                                            .get(key);
        log.info("jsonStr: {}", jsonStr);
    }
    
    /**
     * 操作对象
     *
     * @throws Exception
     * @author colg
     */
    @Test
    public void testGetEneity() throws Exception {
        String key = "emp-01";
        // 默认如果保存对象，使用jdk序列化机制，序列化后的数据保存到redis
        empRedisTemplate.opsForValue()
                        .set(key, employeeMapper.getEmpById(1));
        
        Employee employee = empRedisTemplate.opsForValue()
                                            .get(key);
        log.info("employee: {}", employee);
        
        /*
         * colg  [将数据以json的方式保存]
         *  1. 自己将对象转为json
         *       String key = "emp-01";
         *       stringRedisTemplate.opsForValue()
         *                          .set(key, JSON.toJSONString(employeeMapper.getEmpById(1)));
         *  2. 修改redisTemplate默认的序列化规则
         *      cn.colg.config.RedisConfig
         */
    }
}
