[TOC]

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

### 1、MyBatis configuration配置

- MyBatis 的配置文件包含了影响 MyBatis 行为甚深的 设置（settings）和属性（properties）信息。文档的 顶层结构如下：

- configuration 配置

  - [properties 属性](http://www.mybatis.org/mybatis-3/zh/configuration.html#properties)
  - [settings 设置](http://www.mybatis.org/mybatis-3/zh/configuration.html#settings)
  - [typeAliases 类型别名](http://www.mybatis.org/mybatis-3/zh/configuration.html#typeAliases)
  - [typeHandlers 类型处理器](http://www.mybatis.org/mybatis-3/zh/configuration.html#typeHandlers)
  - [objectFactory 对象工厂](http://www.mybatis.org/mybatis-3/zh/configuration.html#objectFactory)
  - [plugins 插件](http://www.mybatis.org/mybatis-3/zh/configuration.html#plugins)
  - [environment 环境变量](http://www.mybatis.org/mybatis-3/zh/configuration.html#environments)
    - environment 环境变量
      - transactionManager 事务管理器
      - dataSource 数据源
  - [databaseIdProvider 数据库厂商标识](http://www.mybatis.org/mybatis-3/zh/configuration.html#databaseIdProvider)
  - [mappers 映射器](http://www.mybatis.org/mybatis-3/zh/configuration.html#mappers)

### 2、Mybatis 内置类型别名

- mybatis 已经为许多常见的Java类型内建了相应的类型别名。它们都是==大小写不敏感==的，在起别名的时候不要占用已有的别名。

| 别名     | 映射的类型 |
| -------- | ---------- |
| _byte    | byte       |
| _long    | long       |
| _short   | short      |
| _int     | int        |
| _integer | int        |
| _double  | double     |
| _float   | float      |
| _boolean | boolean    |
| string  | String  |
| byte    | Byte    |
| long    | Long    |
| short   | Short   |
| int     | Integer |
| integer | Integer |
| double  | Double  |
| float   | Float   |
| boolean | Boolean |
| date       | Date       |
| decimal    | BigDecimal |
| bigdecimal | BigDecimal |
| object     | Object     |
| map        | Map        |
| hashmap    | HashMap    |
| list       | List       |
| arraylist  | ArrayList  |
| collection | Collection |
| iterator   | Iterator   |

### 3、mappers

- mapper逐个注册SQL映射文件

```xml
	<mappers>
        <!-- mapper：不能使用通配符 -->
		<mapper resource="mapper/EmployeeMapper.xml" />
		<mapper resource="mapper/DepartmentMapper.xml" />
	</mappers>
```

- mapper批量注册SQL映射文件
  - 这种方式要求SQL映射文件必须和接口名相同并且在同一目录下

```xml
	<mappers>
		<!-- 
			mapper: 注册一个sql映射
				注册配置文件：
					resource：	引用类路径下的sql映射文件				<mapper resource="org/mybatis/builder/PostMapper.xml"/>
					url：		引用网络路径下或者磁盘路径下的sql映射文件	<mapper url="file:///var/mappers/PostMapper.xml"/>
				注册接口：
				class:		引用（注册）接口
					1、有sql映射文件，映射文件名必须和接口同名，并且放在与接口同一目录下；
					2、没有sql映射文件，所有的sql都是利用注解写在接口上
					推荐：比较重要的，复杂的Dao接口写sql映射文件
						不重要，简单的Dao接口为了开发快速可以使用注解；
		 -->
		<!-- <mapper resource="mybatis/mapper/EmployeeMapper.xml" /> -->
		<!-- <mapper class="com.atguigu.mybatis.dao.EmployeeMapperAnnotation"/> -->
		
		<!-- package：	批量注册：同包名、同命名 -->
		<package name="cn.colg.dao" />
	</mappers>
```

## 4、MyBatis 映射文件

### 1、映射文件

- 映射文件指导着MyBatis如何进行数据库增删改查，有着非常重要的意义。

- cache - 命名空间的二级缓存配置；

- cache-ref - 其他命名空间缓存配置的引用；

- ==resultMap== - 自定义结果集映射

- ==sql== - 抽取可重用语句块

- ==insert== - 映射插入语句

- ==update== - 映射更新语句

- ==delete== - 映射删除语句

- ==select== - 映射查询语句

- 主键生成方式

  - 数据库支持自动生成主键

  ```xml
  	<!-- 
  		parameterType：参数类型，可以省略
  		
  		获取自增主键的值：
  			Mysql支持自增主键，自增主键值的获取，Mybatis也是利用statement.getGenreatedKeys();
  			useGeneratedKeys="true"	使用自增主键获取主键值策略
  			keyProperty				指定对应的主键属性，也就是Mybatis获取到主键值以后，将这个值封装给javaBean的哪个属性
  	 -->
  	<insert id="addEmployee" useGeneratedKeys="true" keyProperty="employee.id">
  		INSERT INTO tbl_employee (last_name, email, gender)
  		VALUES (#{employee.lastName}, #{employee.email}, #{employee.gender})
  	</insert>
  ```

  - 数据库不支持自动生成主键 - 使用selectKey子元素

### 2、参数传递

- 单个参数
  - 可以接受基本类型、对象类型、集合类型的只。这种情况Mybatis可直接使用这个参数，不需要经过任何处理。
- 多个参数
  - 任意多个参数，都会被MyBatis重新包装成一个Map传入。Map的key是param1，param2...只就是参数的值。
- 命名参数
  - 为参数使用@Param起一个名字，MyBatis就会将这些参数封装进map中，key就是指定的名字。

- POJO
  - 当这些参数属于业务pojo时，直接传递pojo
- Map
  - 可以封装多个参数为map。直接传递

### 3、参数处理

- #{key}：获取参数的值，预编译到SQL中，安全；
- ${key}：获取参数的值，拼接到SQL中。有SQL注入问题。

## 5、MyBatis 动态SQL

## 6、MyBatis 缓存机制

## 7、MyBatis Spring整合

## 8、MyBatis 逆向工程

## 9、Mybaits 工作原理

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fr6jgoequuj30ls0cg0y5.jpg)

```java
package cn.colg.dao;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import cn.colg.entity.Employee;
import lombok.extern.slf4j.Slf4j;

/**
 * 员工Mapper <b>运行原理</b>
 *
 * @author colg
 */
@Slf4j
public class EmployeeMapperTest {

    /**
     * Test method for {@link cn.colg.dao.EmployeeMapper#findById(java.lang.Integer)}.
     * 
     * @throws IOException
     */
    @Test
    public void testFindById() throws IOException {

        /*
         * colg  mybatis 运行原理
         *  1. 获取sqlSessionFactory对象
         *      解析文件的每一个信息保存在Configuration中，返回包含Configuration的DefaultSqlSession；
         *      注意：【MappedStatement】代表一个增删改查的详细信息
         *      
         *  2. 获取sqlSession对象
         *      返回一个DefaultSqlSession对象，包含Executor和Configuration；
         *      这一步会创建Executor对象；
         *      
         *  3. 获取接口的代理对象（MapperProxy）
         *      getMapper，使用MapperProxyFactory创建一个MapperProxy的代理对象
         *      代理对象里面包含了，DefaultSqlSession（Executor）
         *      
         *  4. 执行增删改查方法
         *  
         *  【总结】：
         *      1、根据配置文件（全局，sql映射）初始化出Configuration对象；
         *      2、创建一个DefaultSqlSession对象，他里面包含Configuration以及Executor（根据全局配置文件中的defaultExecutorType创建出对应的Executor）；
         *      3、DefaultSqlSession.getMapper()：拿到Mapper接口对应的MapperProxy；
         *      4、MapperProxy里面有（DefaultSqlSession）；
         *      5、执行增删改查方法：
         *          1)、调用DefaultSqlSession的增删改查（Executor）；
         *          2)、创建一个StatementHandler对象，（同时也会创建出ParameterHandler和resultSetHandler）；
         *          3)、调用StatementHandler预编译参数以及设置参数值；使用ParameterHandler来给sql设置参数；
         *          4)、调用StatementHandler的增删改查方法；
         *          5)、ResultSetHandler封装结果
         *          
         *  注意：
         *      四大对象每个创建的时候都有一个interceptorChain.pluginAll(parameterHandler);
         */

        /// ----------------------------------------------------------------------------------------------------

        // 从 XML 中构建SqlSessionFactory
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 从SqlsessionFactory 中获取 sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        // EmployeeMapperTest.testFindById() >> mapper : org.apache.ibatis.binding.MapperProxy@55634720
        log.info("EmployeeMapperTest.testFindById() >> mapper : {}", mapper);

        Employee employee = mapper.findById(1);
        // EmployeeMapperTest.testFindById() >> employee : Employee(id=1, lastName=jack, gender=0, email=jack@colg.com)
        log.info("EmployeeMapperTest.testFindById() >> employee : {}", employee);
    }
}
```



## 10、MyBatis 插件开发

### 1、插件开发简介

- MyBatis在==四大对象的创建过程中，都会有插件进行介入。==插件可以利用动态代理机制一层层的包装目标队形，而实现在目标对象执行目标方法之前进行拦截的效果。
- MyBatis允许在已映射语句执行过程中的某一点进行拦截调用。
- 默认情况下，MyBatis允许使用插件来拦截的方法调用包括：
  - ==**Executor**==（update、query、flushStatements、commit、rollback、getTransaction、close、isClosed）
  - ==**ParameterHandler**==（getParameterObject、setParameters）
  - ==**ResultSetHandler**==（handleResultSets、handleOutputParameters）
  - ==**StatementHandler**==（prepare、parameterize、batch、update、query）

