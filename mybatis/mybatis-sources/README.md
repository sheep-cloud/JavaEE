# MyBatis 3.4.x

## 1、MyBatis 简介

### 1、MyBatis 简介

- MyBatis 是支持定制化SQL、存储过程以及高级映射的优秀的持久层框架。
- MyBatis 避免了几乎所有的JDBC代码和手动设置参数以及获取结果集。
- Mybatis 可以使用简单的XML或注解用于配置和原始映射，将接口和Java的POJO映射成数据库中的记录

### 2、MyBatis 历史

- 原是Apache的一个开源项目iBatis，2010年6月迁移到了Google Code，正式改名为MyBatis，代码于2013年11月迁移到Github。
- iBatis一词来源于“internet”和“abatis”的组合，是一个基于Java的持久层框架。

### 3、为什么要使用Mybatis？

- MyBatis是一个半自动化的持久层框架。
- JDBC
  - SQL夹在Java代码块里，耦合度高导致硬编码；
  - 维护不易且实际开发需求中sql频繁修改的情况多见。
- Hibernate和JPA
  - 长、难、复杂SQL，对于Hibernate而言处理也不容易；
  - 内部自动生成的SQL，不容易做特殊优化；
  - 基于全映射的全自动框架，大量字段的POJO进行部分映射时比较困难，导致数据库性能下降。
- 对开发人员而言，核心sql还是需要自己优化
- ==SQL和Java编码分开，功能边界清晰，一个专注业务，一个专注数据。==

### 4、去哪里找Mybatis？

- https://github.com/mybatis/mybatis-3

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fr653dyh13j316g0r6ac6.jpg)

## 2、Mybatis Helloworld

### 1、HelloWorld简单版

1. 创建一张测试表
2. 创建对应的javaBean
3. 创建mybatis配置文件，sql映射文件
4. 测试

### 2、测试

```java
package cn.colg;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import lombok.extern.slf4j.Slf4j;

/**
 * Mapper 测试基类
 *
 * @author colg
 */
@Slf4j
public abstract class BaseMapperTest {

    /** 获取sqlSsession工厂 */
    private static SqlSessionFactory sqlSessionFactory;
    /** 获取sqlSession实例，提供给子类 */
    protected SqlSession sqlSession;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        // 从 XML 中构建SqlSessionFactory
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Before
    public void setUp() throws Exception {
        // 从SqlsessionFactory 中获取 sqlSession
        sqlSession = sqlSessionFactory.openSession();
    }

    @After
    public void tearDown() throws Exception {
        // 销毁sqlSession
        sqlSession.close();
        log.info("tearDown() : {}", "----------------------------------------------------------------------------------------------------\n");
    }
}
```

```java
package cn.colg.dao;

import org.junit.Test;

import cn.colg.BaseMapperTest;
import cn.colg.entity.Employee;
import lombok.extern.slf4j.Slf4j;

/**
 * 测试
 *
 * @author colg
 */
@Slf4j
public class EmployeeMapperTest extends BaseMapperTest {
    
    /*
     * 1、接口式编程 
     *      原生：                        Dao     ===>        DaoImpl
     *      Mybatis:    Mapper  ===>        xxMapper.xml
     * 
     * 2、SqlSession代表和数据库的一次回话，用完必须关闭；
     * 
     * 3、SqlSession和Connection一样都是非线程安全的。每次使用都应该去获取新的对象。
     * 
     * 4、Mapper接口没有实现类，但是Mybatis会为这个接口生成一个代理对象。
     *      (将接口和xml进行绑定)
     *      EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
     * 
     * 5、两个重要的配置文件：
     *      1). Mybatis的全局配置文件，包括数据库连接池信息，事务管理器信息等。。系统运行环境信息
     *      2). Sql映射文件：保存了每一个sql语句的映射信息：将sql抽取出来
     */

    /// ----------------------------------------------------------------------------------------------------

    /**
     * Test method for {@link cn.colg.dao.EmployeeMapper#findById(java.lang.Integer)}.
     */
    @Test
    public void testFindById01() {
        // 1. 唯一标识符。
        // 2. 传递给语句的参数对象。
        Employee employee = sqlSession.selectOne("cn.colg.dao.EmployeeMapper.findById", 1);
        log.info("testFindById01() >> employee : {}", employee);
    }

    /**
     * Test method for {@link cn.colg.dao.EmployeeMapper#findById(java.lang.Integer)}.
     */
    @Test
    public void testFindById02() {
        // mybatis会为接口自动创建一个代理对象，代理对象去执行增删改查方法
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        // class com.sun.proxy.$Proxy11
        log.info("testFindById02() >> employeeMapper.getClass() : {}", employeeMapper.getClass());

        Employee employee = employeeMapper.findById(1);
        log.info("testFindById02() >> employee : {}", employee);
    }
}
```

### 3、测试步骤

- 根据全局配置文件，利用 SqlSessionFactoryBuilder创建SqlSessionFactory
- 使用SqlSessionFactory获取sqlSession对象。一个 SqlSession对象代表和数据库的一次会话。    
- 使用SqlSession获取映射器进行操作
- ==SqlSession==
  - ==SqlSession 的实例不是线程安全的，因此是不能被共享的；==
  - ==SqlSession每次使用完成后需要正确关闭，这个 关闭操作是必须的；==
  - ==SqlSession可以获取到Dao接口的代理类，执行代理对象的方法，可以更安全的进行类型检查操作。==

## 3、MyBatis 全局配置文件

- MyBatis 的配置文件包含了影响 MyBatis 行为甚深的 设置（settings）和属性（properties）信息。文档的 顶层结构如下：
- 

## 4、MyBatis 映射文件

## 5、MyBatis 动态SQL

## 6、MyBatis 缓存机制

## 7、MyBatis Spring整合

## 8、MyBatis 逆向工程

## 9、Mybaits 工作原理

## 10、MyBatis 插件开发