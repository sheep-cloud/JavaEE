[TOC]

# MyBatis 3.4.x

## 1. MyBatis 简介

### 1.1. MyBatis 简介

- MyBatis 是支持定制化SQL、存储过程以及高级映射的优秀的持久层框架。
- MyBatis 避免了几乎所有的JDBC代码和手动设置参数以及获取结果集。
- Mybatis 可以使用简单的XML或注解用于配置和原始映射，将接口和Java的POJO映射成数据库中的记录

### 1.2. MyBatis 历史

- 原是Apache的一个开源项目iBatis，2010年6月迁移到了Google Code，正式改名为MyBatis，代码于2013年11月迁移到Github。
- iBatis一词来源于“internet”和“abatis”的组合，是一个基于Java的持久层框架。

### 1.3. 为什么要使用Mybatis？

- MyBatis是一个半自动化的持久层框架。
- JDBC
  - SQL夹在Java代码块里，耦合度高导致硬编码；
  - 维护不易且实际开发需求中sql频繁修改的情况多见。
- Hibernate和JPA
  - 长、难、复杂SQL，对于Hibernate而言处理也不容易；
  - 内部自动生成的SQL，不容易做特殊优化；
  - 基于全映射的全自动框架，大量字段的POJO进行部分映射时比较困难，导致数据库性能下降。
- 对开发人员而言，核心sql还是需要自己优化
- **SQL和Java编码分开，功能边界清晰，一个专注业务，一个专注数据。**

### 1.4. 去哪里找Mybatis？

- https://github.com/mybatis/mybatis-3

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fr653dyh13j316g0r6ac6.jpg)

## 2. Mybatis Helloworld

### 2.1. HelloWorld简单版

1.  创建一张测试表
2.  创建对应的javaBean
3.  创建mybatis配置文件，sql映射文件
4.  测试

### 2.2. 测试

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
        log.info("tearDown() : {}", "------------------------------------------------\n");
    }
}
```

```java
@Slf4j
public class EmployeeMapperTest extends BaseMapperTest {

    /*
     * 1、接口式编程 
     *      原生：                        Dao     ===>        DaoImpl
     *      Mybatis:    Mapper  ===>        xxMapper.xml
     * 
     * 2、SqlSession代表和数据库的一次会话，用完必须关闭；
     * 
     * 3、SqlSession和Connection一样都是非线程安全的。每次使用都应该去获取新的对象。
     * 
     * 4、Mapper接口没有实现类，但是Mybatis会为这个接口生成一个代理对象。
     *      (将接口和xml进行绑定)
     *      EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
     * 
     * 5、两个重要的配置文件：
     *      1). Mybatis的全局配置文件，包括数据库连接池信息，事务管理器信息等，系统运行环境信息
     *      2). Sql映射文件：保存了每一个sql语句的映射信息：将sql抽取出来
     */

    /// ----------------------------------------------------------------------------------

    /**
     * Test method for {@link cn.colg.dao.EmployeeMapper#findById(java.lang.Integer)}.
     */
    @Test
    public void testFindById01() {
        // 1. 唯一标识符。
        // 2. 传递给语句的参数对象。
        Employee employee = sqlSession.selectOne("cn.colg.dao.EmployeeMapper.findById", 1);
        log.info("employee : {}", employee);
    }

    /**
     * Test method for {@link cn.colg.dao.EmployeeMapper#findById(java.lang.Integer)}.
     */
    @Test
    public void testFindById02() {
        // mybatis会为接口自动创建一个代理对象，代理对象去执行增删改查方法
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        // class com.sun.proxy.$Proxy8
        log.info("employeeMapper.getClass() : {}", employeeMapper.getClass());

        Employee employee = employeeMapper.findById(1);
        log.info("employee : {}", employee);
    }

}
```

### 2.3. 测试步骤

- 根据全局配置文件，利用 SqlSessionFactoryBuilder创建SqlSessionFactory
- 使用SqlSessionFactory获取sqlSession对象。一个 SqlSession对象代表和数据库的一次会话。    
- 使用SqlSession获取映射器进行操作
- **SqlSession**
  - **SqlSession 的实例不是线程安全的，因此是不能被共享的；**
  - **SqlSession 每次使用完成后需要正确关闭，这个关闭操作是必须的；**
  - **SqlSession 可以获取到Dao接口的代理类，执行代理对象的方法，可以更安全的进行类型检查操作。**

## 3. MyBatis 全局配置文件

### 3.1. MyBatis configuration配置

- MyBatis 的配置文件包含了影响 MyBatis 行为的 设置（settings）和属性（properties）信息。文档的顶层结构如下：

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

### 3.2. Mybatis 内置类型别名

- mybatis 已经为许多常见的Java类型内建了相应的类型别名。它们都是**大小写不敏感**的，在起别名的时候不要占用已有的别名。

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

### 3.3. mappers

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
		<!-- package：	批量注册：同包名、同命名 -->
		<package name="cn.colg.dao" />
	</mappers>
```

## 4. MyBatis 映射文件

### 4.1. 映射文件

映射文件指导着MyBatis如何进行数据库增删改查，有着非常重要的意义。

- cache - 命名空间的二级缓存配置；

- cache-ref - 其他命名空间缓存配置的引用；

- **resultMap** - 自定义结果集映射

- **sql**               - 抽取可重用语句块

- **insert **        - 映射插入语句

- **update**       - 映射更新语句

- **delete**        - 映射删除语句

- **select**         - 映射查询语句

- 主键生成方式

  - 数据库支持自动生成主键

    ```xml
    	<!-- 
            parameterType：参数类型，可以省略
            
                            获取自增主键的值：
                Mysql支持自增主键，自增主键值的获取，Mybatis也是利用statement.getGenreatedKeys();
                useGeneratedKeys="true"  使用自增主键获取主键值策略
                keyProperty              指定对应的主键属性，也就是Mybatis获取到主键值以后，将这个值封装给javaBean的哪个属性
                
                            新添加主键id并不是在执行添加操作时直接返回的，而是在执行添加操作之后将新添加记录的主键id字段设置为POJO对象的主键id属性
    	 -->
        <insert id="addEmployee" useGeneratedKeys="true" keyProperty="employee.id">
            INSERT INTO
                tbl_employee (last_name, gender, email)
            VALUES
                (#{employee.lastName}, #{employee.gender}, #{employee.email})
        </insert>
    ```

  - 数据库不支持自动生成主键 - 使用selectKey子元素

    ```xml
        <!-- 
            selectKey: 查询主键
                keyProperty：    指定对应的主键属性，也就是Mybatis获取到主键值以后，将这个值封装给javaBean的哪个属性
                order:        在执行操作之前还是之后，执行查询操作
         -->
        <insert id="addEmployee2">
            <selectKey keyProperty="employee.id" order="AFTER" resultType="int">
                SELECT LAST_INSERT_ID()
            </selectKey>
            INSERT INTO
                tbl_employee (last_name, email, gender)
            VALUES
                (#{employee.lastName}, #{employee.email}, #{employee.gender})
        </insert>
    ```

### 4.2. 参数传递

- 单个参数

  ```ini
  可以接收基本类型、对象类型、集合类型的值。这种情况Mybatis可直接使用这个参数，不需要经过任何处理
  #{参数名}:	取出参数值
  ```

- 多个参数

  ```ini
  任意多个参数，都会被MyBatis重新包装成一个Map传入。Map的key是param1，param2...值就是参数的值
  #{param1}:	取出参数值
  ```

- 命名参数

  ```ini
  为参数使用@Param起一个名字，MyBatis就会将这些参数封装进map中，key就是指定的名字
  #{指定的key}:	取出参数值
  ```

- POJO

  ```ini
  这些参数属于业务pojo时，直接传递pojo
  #{属性名}: 取出传入的pojo的属性值
  ```

- Map

  ```ini
  可以封装多个参数为map。直接传递
  #{指定的key}:	取出参数值
  ```

- _parameter

  ```ini
  mybatis默认的内置参数
  单个参数: _parameter就是这个参数；
  多个参数: 参数会被封装为一个map；_parameter就是代表这个map
  #{_parameter.指定的key}: 取出参数值
  ```

### 4.3. 参数处理

```ini
#{key}: 获取参数的值，预编译到SQL中，安全；
${key}: 获取参数的值，拼接到SQL中。有SQL注入问题。
```

## 5. MyBatis 动态SQL

- `if`, `where`

  ```xml
      <select id="queryByConditionIf" resultType="cn.colg.entity.Employee">
          SELECT
              te.id, te.last_name lastName, te.gender, te.email, te.dept_id
          FROM
              tbl_employee te
          <!-- 
              test：判断表达式（OGNL）
                                              从参数中取值进行判断
                                              遇见特殊符号，应该去写转义字符
           -->
          <!-- 
              where：
                                              查询的时候如果某些条件没有设置可能sql拼装有问题
                      1、给where后面加上1=1，以后的条件都加and
                      2、使用where标签，将所有的查询条件包括在内；
                          mybatis就会将where标签中拼装的sql，多出来的and或者or去掉
           -->
          <where>
              <if test="id != null">
                  AND te.id = #{id}
              </if>
              <if test="lastName != null and lastName != ''">
                  AND te.last_name LIKE #{lastName}
              </if>
              <!-- OGNL会进行字符串与数字的转换判断："0"==0 -->
              <if test="gender == 0 or gender == 1">
                  AND te.gender = #{gender}
              </if>
              <!-- 转义符号：&=&amp;  '=&apos; -->
              <if test="email != null &amp;&amp; email.trim() != &apos;&apos;">
                  AND te.email LIKE #{email}
              </if>
          </where>
      </select>
  ```

- `trim`

  ```xml
      <select id="queryByConditionIfTrim" resultType="cn.colg.entity.Employee">
          SELECT
              te.id, te.last_name lastName, te.gender, te.email, te.dept_id
          FROM
              tbl_employee te
          <!-- 
              trim：
                  prefix="where":           前缀，trim标签体中整个字符串拼串后的结果；给整个字符串加一个前缀
                  suffix="":                后缀，trim标签体中整个字符串拼串后的结果；给这个字符串加一个后缀
                  prefixOverrides="AND":    前缀覆盖，去掉整个字符串前面多余的字符
                  suffixOverrides="AND":    后缀覆盖，去掉整个字符串后面多余的字符
  		 -->
          <trim prefix="where" suffixOverrides="AND">
              <if test="id != null">
                  te.id = #{id} AND
              </if>
              <if test="lastName != null and lastName != ''">
                  te.last_name LIKE #{lastName} AND
              </if>
  			<!-- OGNL会进行字符串与数字的转换判断："0"==0 -->
              <if test="gender == 0 or gender == 1">
                  te.gender = #{gender} AND
              </if>
  			<!-- 转义符号：&=&amp;  '=&apos; -->
              <if test="email != null &amp;&amp; email.trim() != &apos;&apos;">
                  te.email LIKE #{email} AND
              </if>
          </trim>
      </select>
  ```

- `choose`

  ```xml
      <select id="queryByConditionIfChoose" resultType="cn.colg.entity.Employee">
          SELECT
              te.id, te.last_name lastName, te.gender, te.email, te.dept_id
          FROM
              tbl_employee te
          <where>
              <!--
                  choose：	分支查询，只会进入其中一个条件
               -->
              <choose>
                  <when test="id != null">
                      AND te.id = #{id}
                  </when>
                  <when test="lastName != null and lastName != ''">
                      AND te.last_name LIKE #{lastName}
                  </when>
                  <when test="gender == 0 or gender == 1">
                      AND te.gender = #{gender}
                  </when>
                  <otherwise>
                      AND te.email LIKE '%a%'
                  </otherwise>
              </choose>
          </where>
      </select>
  ```

- `set`

  ```xml
      <update id="updateEmployee">
          UPDATE tbl_employee
          <set>
              <if test="lastName != null and lastName != ''">
                  last_name = #{lastName},
              </if>
              <if test="gender == 0 or gender != 1">
                  gender = #{gender},
              </if>
              <if test="email != null and email != ''">
                  email = #{email},
              </if>
          </set>
          <where>
              id = #{id}
          </where>
      </update>
  ```

- `foreach`

  ```xml
      <select id="queryByConditionForeach" resultType="cn.colg.entity.Employee">
          SELECT
              te.id, te.last_name, te.gender, te.email, te.dept_id
          FROM
              tbl_employee te
          WHERE te.id IN
          <!-- foreach:   遍历
                  collection="ids":   指定要遍历的的集合；list类型的参数会特殊处理封装在map中，map的key就叫list
                  item="id":          将当前遍历出的元素赋值给指定的变量
                  separator=",":      每个元素之间的分隔符
                  open="(":           遍历出所有结果拼接一个开始的字符串
                  close=")":          遍历出所有结果拼接一个结束的字符串
                  index="":           索引；
                                                                                                                       遍历list的时候index就是索引，item就是当前值；index不需要填写
                                                                                                                       遍历map的时候index表示的就是map的key，item就是map的值
                  #{id}:              就是取出变量的值，也就是当前遍历出的元素
  		 -->
          <foreach collection="ids" item="id" separator="," open="(" close=")">
              #{id}
          </foreach>
      </select>
  ```

  ```xml
      <insert id="addEmployees">
          INSERT INTO
              tbl_employee(last_name, gender, email, dept_id)
          VALUES
          <!-- MySQL下批量保存:    可以foreach遍历；mysql支持values(), (), ()语法 -->
          <foreach collection="employees" item="employee" separator=",">
              (#{employee.lastName}, #{employee.gender}, #{employee.email}, #{employee.dept.id})
          </foreach>
      </insert>
  ```

## 6. MyBatis 缓存机制

- 一级缓存：（本地缓存）sqlSession级别的缓存，一级缓存是一直开启的；

  ```ini
  一级缓存：（本地缓存）sqlSession级别的缓存，一级缓存是一直开启的；
  	与数据库同一次会话期间查询到的数据会放在本地缓存中。以后如果需要获取相同的数据，直接从缓存中拿，没必要再去查询数据库。
  	
  	一级缓存失效情况（没有使用到当前一级缓存的情况，效果就是，还需要再向数据库发出查询）
  		1. sqlSession不同
  		2. sqlSession相同
  			2.1. 查询条件不同
  			2.2. 查询之间执行了增删改操作
  			2.3. 手动清除了一级缓存（缓存清空）
  ```

- 二级缓存

  ```ini
  二级缓存：（全局缓存基于namespace级别的缓存，一个namespace对应一个二级缓存
  	工作机制:
  		1. 一个会话，查询一条数据，这个数据就会被放在当前会话的一级缓存中；
  		2. 如果会话关闭；一级缓存中的数据会被保存到二级缓存中；新的会话查询信息，就可以参照二级缓存中的内容。
  		3. 不同namespace查询的数据会放在自己对应的缓存中（map）；数据会从二级缓存中获取；
  	 查出的数据会默认先放在一级缓存中。只有会话提交或者关闭以后，一级缓存中的数据才会转移到二级缓存中。
  
  	使用：
  		1. 去mybatis-config.xml中开启全局二级缓存配置: <setting name="cacheEnabled" value="true"/>
  		2. 去mapper.xml中配置使用二级缓存: <cache></cache>
  		3. pojo需要实现序列化接口
  ```

- 缓存相关的设置

  ```ini
  缓存相关的设置/属性：
  	1. <setting name="cacheEnabled" value="false"/>
  		一级缓存: 未关闭；
  		二级缓存: 关闭
  	2. <select ... useCache="false">
  		一级缓存: 未关闭；
  		二级缓存: 关闭
  	3. <insert ... flushCache="false">
  		一级缓存: 关闭；
  		二级缓存: 关闭
  ```

## 7. MyBatis Spring整合

## 8. MyBatis 逆向工程

## 9. Mybaits 工作原理

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

        /// ------------------------------------------------------------------------------

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



## 10. MyBatis 插件开发

### 10.1. 插件开发简介

- MyBatis在==四大对象的创建过程中，都会有插件进行介入。==插件可以利用动态代理机制一层层的包装目标队形，而实现在目标对象执行目标方法之前进行拦截的效果。
- MyBatis允许在已映射语句执行过程中的某一点进行拦截调用。
- 默认情况下，MyBatis允许使用插件来拦截的方法调用包括：
  - ==**Executor**==（update、query、flushStatements、commit、rollback、getTransaction、close、isClosed）
  - ==**ParameterHandler**==（getParameterObject、setParameters）
  - ==**ResultSetHandler**==（handleResultSets、handleOutputParameters）
  - ==**StatementHandler**==（prepare、parameterize、batch、update、query）

