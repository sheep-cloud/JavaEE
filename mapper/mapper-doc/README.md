# 通用 Mapper

## 1. 引入

### 1.1. 作用

- 生成常用增删改查操作的SQL语句。

### 1.2. 代码官方发布地址

- https://gitee.com/free/Mapper
- https://gitee.com/free/Mapper/wikis/Home

### 3. 前置知识

- MyBatis
- Spring

## 2. 快速入门

### 2.1. 创建测试数据

- SQL语句

```mysql
DROP DATABASE IF EXISTS mapper;
CREATE DATABASE mapper CHARACTER SET UTF8;
USE mapper;

DROP TABLE IF EXISTS `table_emp`;

CREATE TABLE `table_emp` (
	`emp_id` VARCHAR(64) NOT NULL COMMENT '主键',
	`emp_name` VARCHAR(500) NULL COMMENT '员工姓名',
	`emp_salary` DOUBLE(15,5) NULL COMMENT '员工工资',
	`emp_age` INT NULL COMMENT '员工年龄',
	PRIMARY KEY (`emp_id`)
) COMMENT '员工表';

INSERT INTO
    table_emp(emp_id, emp_name, emp_salary, emp_age)
VALUES
    (REPLACE(UUID(), '-', ''), 'tom', '1254.37', '27'),
    (REPLACE(UUID(), '-', ''), 'jerry', '6635.42', '38'),
    (REPLACE(UUID(), '-', ''), 'bob', '5560.11', '40'),
    (REPLACE(UUID(), '-', ''), 'kate', '2209.11', '22'),
    (REPLACE(UUID(), '-', ''), 'justin', '4203.15', '30');
```

- Java实体类
  - 考虑到基本数据类型在Java类中都有默认值，会导致MyBatis在执行相关操作试很难判断当前字段是否为null，所以在MyBatis环境下使用Java实体类时尽量不要使用基本数据类型，都使用对应的包装类型。

```java
/**
 * 员工实体
 * 
 * - @mbg.generated
 *
 * @author colg
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "table_emp")
public class Employee extends BaseEntity implements Serializable {

    /**
     * 主键
     */
    @KeySql(genId = UuidGenId.class)
    @Id
    @Column(name = "emp_id")
    private String empId;

    /**
     * 员工姓名
     */
    @Column(name = "emp_name")
    private String empName;

    /**
     * 员工工资
     */
    @Column(name = "emp_salary")
    private Double empSalary;

    /**
     * 员工年龄
     */
    @Column(name = "emp_age")
    private Integer empAge;

    private static final long serialVersionUID = 1L;

    /// ----------------------------------------------------------------------------------------------------

    public Employee(String empName, Double empSalary) {
        this.empName = empName;
        this.empSalary = empSalary;
    }

    public Employee(String empName, Double empSalary, Integer empAge) {
        this.empName = empName;
        this.empSalary = empSalary;
        this.empAge = empAge;
    }

}
```

### 2. 搭建MyBatis+Spring开发环境

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	   xmlns:aop="http://www.springframework.org/schema/aop"
	   xmlns:context="http://www.springframework.org/schema/context"
	   xmlns:tx="http://www.springframework.org/schema/tx"
	   xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		                   http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.3.xsd
		                   http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-4.3.xsd
		                   http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-4.3.xsd">
	
	<!-- 配置数据源 -->
	<context:property-placeholder location="classpath:jdbc.properties"/>

	<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource" init-method="init" destroy-method="close">
        <property name="driverClassName" value="${jdbc.driver}" />
        <property name="url" value="${jdbc.url}" />
		<property name="username" value="${jdbc.username}"/>
		<property name="password" value="${jdbc.password}"/>
	</bean>
    
    <!-- Spring 事务管理 -->
    <bean id="dataSourceTransactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>
    
    <!-- 开启基于注解的事务 -->
    <tx:annotation-driven transaction-manager="transactionManager"/>

	<!-- Spring 整合 Mybatis -->
	<bean id="sqlSessionFactoryBean" class="org.mybatis.spring.SqlSessionFactoryBean">
        <!-- 加载数据源 -->
		<property name="dataSource" ref="dataSource"/>
        <!-- 加载mybatis的全局配置文件 -->
		<property name="configLocation" value="classpath:mybatis/mybatis-config.xml"/>
	</bean>
	
	<!-- 整合通用Mapper所需要做的配置修改： -->
	<!-- 原始全类名：org.mybatis.spring.mapper.MapperScannerConfigurer -->
	<!-- 通用Mapper使用：tk.mybatis.spring.mapper.MapperScannerConfigurer -->
	<bean class="tk.mybatis.spring.mapper.MapperScannerConfigurer">
		<property name="basePackage" value="cn.colg.mapper"/>
	</bean>

</beans>

```

### 3. 集成mapper

```xml
		<!-- mybatis 依赖 -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.4.6</version>
        </dependency>
        <!-- mapper 依赖 -->
        <dependency>
            <groupId>tk.mybatis</groupId>
            <artifactId>mapper</artifactId>
            <version>4.0.3</version>
        </dependency>
        <!-- mybatis-spring 依赖 -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>1.3.2</version>
        </dependency>
```

```xml
	<!-- 整合通用Mapper所需要做的配置修改： -->
	<!-- 原始全类名：org.mybatis.spring.mapper.MapperScannerConfigurer -->
	<!-- 通用Mapper使用：tk.mybatis.spring.mapper.MapperScannerConfigurer -->
	<bean class="tk.mybatis.spring.mapper.MapperScannerConfigurer">
		<property name="basePackage" value="cn.colg.mapper"/>
	</bean>
```

### 4. Mapper接口

```java
/**
 * 员工Mapper
 * 
 * <pre>
 * 具体操作数据库的Mapper接口，需要继承通用Mapper提供的核心接口：Mapper<T>
 *  反省类型就是实体类的类型
 * </pre>
 * 
 * - @mbg.generated
 *
 * @author colg
 */
public interface EmployeeMapper extends tk.mybatis.mapper.common.Mapper<Employee> {}
```

## 3. 常用注解

- @Table
  - 作用：建立实体类和数据表之间的对应关系；
  - 规则：实体类类名首字母小写作为表明。Employee类-->emploeyy表；
  - 用法：在@Table注解的name属性中指定目标数据库表的表名。
- @Column
  - 作用：建立实体类字段和数据库表字段之间的对应关系
  - 规则：实体类字段：驼峰命名；数据库字段：使用"_"作为各个单词
  - 用法：在@Column注解的name属性中指定目标字段的字段名
- @Id
  - 情况一：没有使用@Id注解指定主键字段，where字句会将所有字段都放在一起作为联合主键。
  - 情况二：使用@Id主键明确标记和数据库表中主键字段对应的实体类字段。
- ~~@GeneratedValue~~@KeySql
  - 让通用Mapper在执行insert操作之后将数据库自动生成的主键值写到实体对象中
- @Transient

## 4. 常用方法

- selectOne 方法
- xxxByPrimaryKey 方法
  - 需要使用@Id注解，明确标记和数据库表主键字段对应的实体类字段，否则会将所有实体类字段作为联合主键。
- xxxSelective 方法

## 5. QBC查询

### 5.1. 概念

- Query By Criteria
  - Criteria是Criterion的复数形式。意识是：规则. 标准. 准则。在SQL语句中相当于查询条件。
  - QBC查询是将查询条件通过Java对象进行模块化封装。

### 5.2. 示例代码

## 6. 逆向工程

- 参考文档地址

## 7. 自定义Mapper<T>接口

## 8. 通用Mapper接口扩展

## 9. 二级缓存

## 10. 类型处理器