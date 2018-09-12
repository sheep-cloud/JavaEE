[TOC]

# spring-boot

## 1. Spring Boot 入门

### 1.1. Sping Boot简介

- 简化Spring应用开发的一个框架；
- 整个Spring技术站的一个大集合；
- SpringBoot是J2EE的一站式解决方案；
- SpringCloud是分布式整体解决方法。
- 优点
  - 快速创建独立运行的Spirng项目以及与主流框架集成
  - 使用嵌入式的Servlet容器，应用无需打成WAR包
  - starters自动依赖与版本控制
  - 大量的自动配置，简化开发，也可以修改默认值
  - 无需配置XML，无代码生成，开箱即用
  - 准生产环境的运行时应用监控
  - 与云计算的天然集成

### 1.2. 微服务

- 微服务；架构风格（服务微化）
  - 一个应用应该是一组小型服务；可以通过HTTP的方式进行互通。
  - 每一个功能元素最终都是一个可以独立替换和独立升级的软件单元。
- 单体应用
  - ALL IN ONE

### 1.3. 环境准备

```ini
jdk:java version "1.8.0_40"
maven:Apache Maven 3.5.0
ide:Spring Tool Suite 3.7.3.RELEASE
sprign-boot:1.5.16.RELEASE
```

### 1.4. Spring Boot HelloWorld

```ini
一个功能：浏览器发送hello请求，服务器接收请求并处理，响应Hello World字符串；
```

#### 1.4.1. 创建一个Maven工程

#### 1.4.2. 导入Spring Boot相关的依赖

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>
    <groupId>cn.colg</groupId>
    <artifactId>spring-boot-01-helloworld</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>spring-boot-01-helloworld :: sprign-boot 初识</name>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.5.16.RELEASE</version>
    </parent>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>

</project>
```

#### 1.4.3. 编写一个主程序；启动Spring Boot应用

```java
package cn.colg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot 启动类
 * 
 * <pre>
 * '@SpringBootApplication'：    标注一个主程序类，说明这是一个Spring Boot的主配置类，SpringBoot就应该运行这个类的main方法来启动SpringBoot应用
 * </pre>
 *
 * @author colg
 */
@SpringBootApplication
public class SpringBoot01HelloworldApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot01HelloworldApplication.class, args);
    }
}
```

#### 1.4.4. 编写相关的Controler、Service

```java
package cn.colg.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloController
 *
 * @author colg
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }
}
```

#### 1.4.5. 运行主程序测试

#### 1.4.6. 简化部署

```xml
    <build>
        <plugins>
			<!-- 这个插件，可以将应用打包成一个可执行的jar包 -->
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
```



### 1.5. Hello World探究

#### 1.5.1. POM文件

##### 1.5.1.1. 父项目

```xml
    <!-- 
        它的父项目是：
            <parent>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>1.5.13.RELEASE</version>
                <relativePath>../../spring-boot-dependencies</relativePath>
            </parent>
        Spring Boot应用里面的所有依赖版本；
        以后导入依赖默认是不需要写版本；（没有在dependencies里面管理的依赖自然需要声明版本号）
     -->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.5.16.RELEASE</version>
    </parent>
```

##### 1.5.1.2. 启动器

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
```

- spring-boot-starter-web
  - spring-boot-starter：spring-boot场景启动器；帮我们导入了web模块正常运行所依赖的组件；
- Spring Boot将所有功能场景都抽取出来，做成一个个的starters（启动器），只要在项目里面引入这些starter相关场景的所有依赖都会导入进来，要用什么功能就导入什么场景的启动器

#### 1.5.2. 入口类和@SpringBootApplication

- 程序从main方法开始运行
- 使用SpringApplicaiton.run()加载主程序类
- 主程序类需要标注@SpringBootApplicaiton
- @EnableAutoConfiguration是核心注解
- @Import导入所有的自动配置场景
- @AutoConfigurationPackage定义默认的包扫描规则
- 程序启动扫描加载主程序类所在的包以及下面所有子包的组件。

### 1.6. 使用Spring Initialzer快速创建Spring Boot项目

#### 1.6.1. STS使用Spring Starter Project快速创建项目

默认生成的spring boot项目：

- 主程序已经生成好了，测试基础类已经生成好了
- resources文件夹目录结构
  - static：保存所有的静态资源；js、css、images；
  - templates：保存所有的模版页面；（spring boot默认jar包使用嵌入式的tomcat，默认不支持jsp）；可以使用模版引擎（freemarker、thymeleaf）；
  - application.properties：spring boot应用的配置文件，可以修改一些默认设置。

## 2. 配置文件

### 2.1. 配置文件

SpringBoot使用一个全局的配置文件，配置文件名是固定的：

- application.properties
- application.yml

配置文件的作用：修改SpringBoot自动配置的默认值；SpringBoot在底层都给我们自动配置好；

- properties：

```ini
server.port=8001
```

- yml:

```yaml
server:
  port: 8001
```

- xml:

```xml
<server>
	<port>8001</prot>
</server>
```

### 2.2. YAML语法

#### 2.2.1. 基本语法

```ini
k(空格)v: 表示一对键值对（空格必须有）；
以空格的缩进来控制层级关系；只要在左对齐的一列数据，都是同一个层级的，属性和值也是大小写敏感；
```

#### 2.2.2. 值的写法

##### 2.2.2.1. 字面量、数字、字符串、布尔

```ini
k: v
	字面值直接写；
	字符串默认不用加上单引号或者双引号；
	""：	双引号；不会转义字符串里面的特殊字符；特殊字符会作为本身想表达的意思
		name: "Jack \n Rose"；输出：Jack 换行 Rose

	''：	单引号；会转义特殊字符，特殊字符最终知识一个普通的字符串数据
		name: 'Jack \n Rose'；输出：Jack \n Rose
```

##### 2.2.2.2. 对象、Map

```ini
k: v
	在下一行写对象的属性和值的关系；注意缩进
	对象还是k: v的方式
```

- 普通写法

```yaml
friends:
  lastName: Jack
  age: 20
```

- 行内写法：

```yaml
friedns: {lastName: Jack, age: 20}
```

##### 2.2.2.3. 数组、List、Set

- 用-值来表示数组中的一个元素

  - 普通写法

  ```yaml
  pets:
    - cat
    - dog
    - pig
  ```

  - 行内写法

  ```yaml
  pets: [cat, dog, pig]
  ```

### 2.3. 配置文件注入

#### 2.3.1. @ConfigurationProperties获取值

- 配置文件
  - 普通写法：

  ```yaml
  person:
    last-name: hello
    age: 18
    boss: false
    birth: 2017/12/12
    maps:
      k1: v1
      k2: v2
    lists:
      - Jack
      - Rose
    dog:
      name: 小狗
      age: 3
  ```

  - 行内写法：

  ```yaml
  person:
    last-name: hello
    age: 18
    boss: false
    birth: 2017/12/12
    maps: {k1: v1, k2: v2}
    lists: [Jack, Rose]
    dog: {name: 小狗, age: 3}
  ```

```yaml
person:
  last-name: hello
  age: 18
  boss: false
  birth: 2017/12/12
  maps:
    k1: v1
    k2: v2
  lists:
    - Jack
    - Rose
  dog:
    name: 小狗
    age: 3
```

- 	行内写法：

- javaBean：

```java
package cn.clog.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Person 实体 '@ConfigurationProperties' 获取值
 * 
 * <pre>
 * 将配置文件中配置的每一个属性的值，映射到这个组件中
 *  `@ConfigurationProperties`：  告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定，默认从全局配置文件中获取值
 *      `(prefix = "person")`：  配置文件中哪个与下面的所有属性进行一一映射
 *      
 *  只有这个组件是容器中的组件，才能使用容器提供的`@ConfigurationProperties`组件
 * </pre>
 *
 * @author colg
 */
@ConfigurationProperties(prefix = "person")
@Component
@Data
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    private String lastName;
    private Integer age;
    private Boolean boss;
    private Date birth;

    private Map<String, Object> maps;
    private List<Object> lists;

    private Dog dog;
}
```

- spring-boot-configuration-processor依赖：

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <optional>true</optional>
        </dependency>
```

```ini
person : Person(lastName=hello, age=18, boss=false, birth=Tue Dec 12 00:00:00 CST 2017, maps={k1=v1, k2=v2}, lists=[Jack, Rose], dog=Dog(name=小狗, age=3))
```

#### 2.3.2. @Value获取值

```yaml
cat:
  lastName: black
  age: 3
  birth: 2018/03/15
```

```java
package cn.clog.bean;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Cat 实体 '@Value' 获取值
 *
 * @author colg
 */
@Component
@Data
public class Cat implements Serializable {
    
    /*
     * colg  xml配置
     * <bean>
     *  <property name="lastName" value="?" />
     * </bean>
     */

    private static final long serialVersionUID = 1L;

    @Value("${cat.lastName}")
    private String lastName;
    @Value("${cat.age}")
    private Integer age;
    @Value("${cat.birth}")
    private Date birth;
}
```

```ini
cat : Cat(lastName=black, age=3, birth=Thu Mar 15 00:00:00 CST 2018)
```

#### 2.3.3. 获取值比较

|                      | @ConfigurationProperties | @Value     |
| -------------------- | ------------------------ | ---------- |
| 功能                 | 批量注入配置文件中的属性 | 一个个指定 |
| 松散绑定（松散语法） | 支持                     | 不支持     |
| SpEL（#{}）          | 不支持                   | 支持       |
| JSR303数据校验       | 支持                     | 不支持     |
| 复杂数据类型         | 支持                     | 不支持     |

#### 2.3.4. 加载配置文件

##### 2.3.4.1. @PropertySource

```properties
color.red: 红色
color.blue: 蓝色
color.createDate: 2018/01/01
color.maps.k1: v1
color.maps.k2: v2
color.lists: Jack, Rose
```

```java
package cn.clog.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Color 实体 '@PropertySource' 加载指定的配置文件
 *
 * @author colg
 */
@ConfigurationProperties(prefix = "color")
@PropertySource(value = {"classpath:color.properties"})
@Component
@Data
public class Color implements Serializable {

    private static final long serialVersionUID = 1L;

    private String red;
    private String blue;
    private Date createDate;

    private Map<String, Object> maps;
    private List<String> lists;
}
```

```ini
testColor() >> color : Color(red=红色, blue=蓝色, createDate=Mon Jan 01 00:00:00 CST 2018, maps={k2=v2, k1=v1}, lists=[Jack, Rose])
```

##### 2.3.4.2. @ImportResource

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	   xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="phone" class="cn.clog.bean.Phone">
        <property name="name" value="oppo" />
        <property name="color" value="green" />
        <property name="age" value="2" />
        <property name="maps">
            <map>
                <entry key="k1" value="v1" />
                <entry key="k2" value="v2" />
            </map>
        </property>
        <property name="lists">
            <list>
                <value>Jack</value>
                <value>Rose</value>
            </list>
        </property>
    </bean>
    
</beans>

```

```java
package cn.clog.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Phone 实体 '@ImportResource' 导入Spring的配置文件
 *
 * @author colg
 */
@ImportResource(locations = {"classpath:beans.xml"})
@Component
@Data
public class Phone implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String color;
    private Integer age;
    
    private Map<String, Object> maps;
    private List<String> lists;
}
```

```ini
phone : Phone(name=oppo, color=green, age=2, maps={k1=v1, k2=v2}, lists=[Jack, Rose])
```

#### 2.3.5. SpringBoot推荐方式

```java
package cn.clog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.clog.bean.Cat;
import cn.clog.bean.Color;
import cn.clog.bean.Person;
import cn.clog.bean.Phone;

/**
 * 配置类；'@Configuration'： 指定当前类是一个配置类；就是来替代之前的Spring配置文件
 *
 * @author colg
 */
@Configuration
public class AppConfig {

    /**
     * 将方法的返回值添加到容器中，容器中这个组件默认的id就是方法名
     *
     * @return
     */
    @Bean
    public Person person() {
        return new Person();
    }

    @Bean
    public Cat cat() {
        return new Cat();
    }

    @Bean
    public Color color() {
        return new Color();
    }

    @Bean
    public Phone phone() {
        return new Phone();
    }
}
```

```ini
person : Person(lastName=hello, age=18, boss=false, birth=Tue Dec 12 00:00:00 CST 2017, maps={k1=v1, k2=v2}, lists=[Jack, Rose], dog=Dog(name=小狗, age=3))
cat : Cat(lastName=black, age=3, birth=Thu Mar 15 00:00:00 CST 2018)
color : Color(red=红色, blue=蓝色, createDate=Mon Jan 01 00:00:00 CST 2018, maps={k2=v2, k1=v1}, lists=[Jack, Rose])
phone : Phone(name=oppo, color=green, age=2, maps={k1=v1, k2=v2}, lists=[Jack, Rose])
```

### 2.4. 配置文件占位符

#### 2.4.1. 随机数

```yaml
taxi:
  name: 丰田卡罗拉${random.uuid}
  plate-number: 鄂FPN18${random.int[10]}
  age: ${random.int[1,3]}
  dog:
    name: 小狗
    age: ${taxi.age}
```

#### 2.4.2. 占位符获取之前配置的值，如果没有可以用:指定默认值

```yaml
taxi:
  name: 丰田卡罗拉${random.uuid}
  plate-number: 鄂FPN18${random.int[10]}
  age: ${random.int[1,3]}
  dog:
    name: ${taxi.hello:小狗}
    age: ${taxi.age}
```

```ini
taxi : Taxi(name=丰田卡罗拉9cf718f9-b218-4deb-b96e-297228c1a33f, plateNumber=鄂FPN185, age=2, dog=Dog(name=小狗, age=2))
```

### 2.5. 多环境Profile

#### 2.5.1. 多Profile文件

- application-{profile}.yml/properties	主配置文件名；默认使用application.yml配置

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fr36l684vsj304h01q0sh.jpg)

#### 2.5.2. 激活指定Profile

```yaml
spring:
  profiles:
    active:
    - dev
```

- 虚拟机参数：-Dspring.profiles.active=dev

#### 2.5.3. yml支持多文档块方式

```yaml
server:
  port: 8001
spring:
  profiles:
    active:
      - dev                     # 激活dev环境

---
server:
  port: 8002
spring:
  profiles: dev

---
server:
  port: 8003
spring:
  profiles: prod
```

### 2.6. 配置文件加载位置

- Spring Boot 启动会扫描以下位置的application.yml/properties作为Sprng Boot的默认配置文件
  - file:/config/
  - file:./
  - **classpath:/config/**
  - **classpath:/**
- 优先级由高到低，高优先级的配置会覆盖第优先级的配置；
- Spring Boot会从这4个位置全部加载主配置文件；互补配置；
- **spring.config.location**: F:/application.yml  手动指定配置文件位置。

### 2.7. 自动配置原理

- [配置文件属性参考](https://docs.spring.io/spring-boot/docs/1.5.12.RELEASE/reference/htmlsingle/#common-application-properties)

#### 2.7.1. 自动配置原理

##### 2.7.1.1. SpirngBoot启动的时候加载主配置类，开启了自动配置功能`@EnableAutoConfiguration`

##### 2.7.1.2. '@EnableAutoConfiguration'

- 利用EnableAutoConfigurationImportSelector给容器中导入一些组件；

- 可以查看AutoConfigurationImportSelector.selectImports(AnnotationMetadata) 方法中的内容；

- List<String> configurations = getCandidateConfigurations(annotationMetadata, attributes);获取候选的位置；

  - ```java
    SpringFactoriesLoader.loadFactoryNames();
    扫描所有jar包类路径下 "META-INF/spring.factories"
    把扫描到的这些文件的内容包装成properties对象
    从properties中获取到EnableAutoConfiguration.class类对应的值，然后把他们添加到容器中
    ```

  - 将类路径下 META-INF/spring.factories 里面配置的所有EnableAutoConfiguration的值加入到了容器中。

## 3. 日志

## 4. Web开发

## 5. Docker

## 6. SpringBoot与数据访问

## 7. 自定义starter

## 8. 更多SpringBoot整合示例