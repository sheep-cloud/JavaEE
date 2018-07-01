# 通用 Mapper

## 1、引入

### 1、作用

- 替我们生成常用增删改查操作的SQL语句。

### 2、代码官方发布地址

- https://gitee.com/free/Mapper
- https://gitee.com/free/Mapper/wikis/Home

### 3、前置知识

- MyBatis
- Spring

## 2、快速入门

### 1、创建测试数据

- SQL语句

```sql
DROP DATABASE IF EXISTS mapper;
CREATE DATABASE mapper CHARACTER SET UTF8;
USE mapper;

DROP TABLE IF EXISTS `tabple_emp`;

CREATE TABLE `tabple_emp` (
	`emp_id` INT NOT NULL AUTO_INCREMENT ,
	`emp_name` VARCHAR(500) NULL ,
	`emp_salary` DOUBLE(15,5) NULL ,
	`emp_age` INT NULL ,
	PRIMARY KEY (`emp_id`)
);

INSERT INTO `tabple_emp` (`emp_name`, `emp_salary`, `emp_age`) VALUES ('tom', '1254.37', '27');
INSERT INTO `tabple_emp` (`emp_name`, `emp_salary`, `emp_age`) VALUES ('jerry', '6635.42', '38');
INSERT INTO `tabple_emp` (`emp_name`, `emp_salary`, `emp_age`) VALUES ('bob', '5560.11', '40');
INSERT INTO `tabple_emp` (`emp_name`, `emp_salary`, `emp_age`) VALUES ('kate', '2209.11', '22');
INSERT INTO `tabple_emp` (`emp_name`, `emp_salary`, `emp_age`) VALUES ('justin', '4203.15', '30');
```

- Java实体类
  - 考虑到基本数据类型在Java类中都有默认值，会导致MyBatis在执行相关操作试很难判断当前 字段是否为null，所以在MyBatis环境下使用Java实体类时尽量不要使用基本数据类型，都使用对应的包装类型。

```java
package cn.colg;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 员工实体
 *
 * @author colg
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer empId;
    private String empName;
    private Double empSalary;
    private Integer empAge;
}
```

### 2、搭建MyBatis+Spring开发环境

### 3、集成mapper

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
            <version>4.0.2</version>
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

### 4、Mapper接口

```java
/**
 * 员工Mapper
 * 
 * <pre>
 * 具体操作数据库的Mapper接口，需要继承通用Mapper提供的核心接口：Mapper<T>
 *  反省类型就是实体类的类型
 * </pre>
 *
 * @author colg
 */
public interface EmployeeMapper extends Mapper<Employee> {}
```

## 3、常用注解

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

## 4、常用方法

- selectOne 方法
- xxxByPrimaryKey 方法
  - 需要使用@Id注解，明确标记和数据库表主键字段对应的实体类字段，否则会将所有实体类字段作为联合主键。
- xxxSelective 方法

## 5、QBC查询

### 1、概念

- Query By Criteria
  - Criteria是Criterion的复数形式。意识是：规则、标准、准则。在SQL语句中相当于查询条件。
  - QBC查询是将查询条件通过Java对象进行模块化封装。

### 2、示例代码

## 6、逆向工程

- 参考文档地址

## 7、自定义Mapper<T>接口

## 8、通用Mapper接口扩展

## 9、二级缓存

## 10、类型处理器