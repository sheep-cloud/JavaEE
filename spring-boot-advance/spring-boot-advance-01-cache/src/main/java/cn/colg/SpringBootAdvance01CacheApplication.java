package cn.colg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Sprign Boot 启动类
 * 
 * <pre>
 * 一、搭建基本环境
 *  1. 导入数据库文件，创建departement和employee表
 *  2. 创建javaBean封装数据
 *  3. 整合MyBatis操作数据库
 *      3.1. 配置数据源信息
 *      3.2. 使用注解版的MyBatis；`@MapperScan`指定需要扫描的mapper接口所在的包
 *      
 * 二、快速体验缓存
 *  1. 开启基于注解的缓存：`@EnableCaching`
 *  2. 标注缓存注解即可
 *      `@Cacheable`:   主要针对方法配置，能够根据方法的请求参数对其结果进行缓存
 *      `@CacheEvict`:  清空缓存
 *      `@CachePut`:    保证方法被调用，又希望结果被缓存
 *      
 * 三、整合redis作为缓存；Redis 是一个开源（BSD许可）的，内存中的数据结构存储系统，它可以作用数据库、缓存和消息中间件。
 *  1. 安装redis；
 *  2. 引入redis的starter
 *  3. 配置redis
 *  4. 测试缓存
 *      原理：CacheManager===Cache 缓存组件来实际给缓存中存取数据
 *      1. 引入redis的starter，容器中保存的是 RedisCacheManager
 *      2. RedisCacheManager 帮我们创建 RedisCache 来作为缓存组件，RedisCache通过操作redis缓存数据
 *      3. 默认保存数据 k-v 都是object；利用序列化保存；
 *          如何保存为json
 *              1. 引入了redis的starter，cacheManager变为RedisCacheManager
 *              2. 默认创建的 RedisCacheManager 操作redis的时候使用的是 RedisTemplate<Object, Object>
 *              3. RedisTemplate<Object, Object> 是 默认使用 JdkSerializationRedisSerializer JDK序列化机制
 *      4. 自定义CacheManager
 *  
 * </pre>
 *
 * @author colg
 */
@EnableCaching
@MapperScan("cn.colg.mapper")
@SpringBootApplication
public class SpringBootAdvance01CacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAdvance01CacheApplication.class, args);
    }
}
