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

将这个应用打成jar包，直接使用 `java -jar spring-boot-01-helloworld-0.0.1-SNAPSHOT.jar`命令执行

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

- **spring-boot-starter**-web


```ini
spring-boot-starter:spring-boot场景启动器；帮我们导入了web模块正常运行所依赖的组件；
```

- Spring Boot将所有功能场景都抽取出来，做成一个个的starters（启动器），只要在项目里面引入这些starter相关场景的所有依赖都会导入进来，要用什么功能就导入什么场景的启动器

#### 1.5.2. 入口类和@SpringBootApplication

- 程序从main方法开始运行
- 使用SpringApplicaiton.run()加载主程序类
- 主程序类需要标注@SpringBootApplicaiton
- @EnableAutoConfiguration是核心注解
- @Import导入所有的自动配置场景
- @AutoConfigurationPackage定义默认的包扫描规则
- 程序启动扫描加载主程序类所在的包以及下面所有子包的组件。

#### 1.5.3. 自动配置

```java
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

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = {
		@Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
		@Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
public @interface SpringBootApplication {
```

- **@SpringBootConfiguration**：SpringBoot的配置类

  - 标注在某个类上，表示这是一个SpringBoot的配置类；
  - **Configuration**:配置类上来标注这个注解
    - 配置类 --- 配置文件

- **@EnableAutoConfiguration**：开启自动配置功能

  - 以前需要配置的东西，SpringBoot帮我们自动配置；

  ```java
  @AutoConfigurationPackage
  @Import(EnableAutoConfigurationImportSelector.class)
  public @interface EnableAutoConfiguration {
  ```

  - **@AutoConfigurationPackage**：自动配置包
    - **@Import(AutoConfigurationPackages.Registrar.class)**：Spring的底层注解@import，给容器中导入一个组件。将主配置类(@SpringBootApplication标注的类)的所在包及下面所有子包里面的所有组件扫描到Spring容器中。
  - **@Import(EnableAutoConfigurationImportSelector.class)**：给容器中导入组件的选择器，将所有需要导入的组件以全类名的方式返回，这些组件就会被添加到容器中。会给容器中导入非常多的自动配置类（xxxAutoConfiguration）；就是给容器中导入这个场景需要的所有组件，并配置好这些组件。

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fv910m7lddj31f20l8ju6.jpg)

  - 有了自动配置，免去了我们手动编写配置注入功能组件等的工作；
    - `List<String> configurations = SpringFactoriesLoader.loadFactoryNames(getSpringFactoriesLoaderFactoryClass(), getBeanClassLoader());`
    - `Enumeration<URL> urls = (classLoader != null ? classLoader.getResources(FACTORIES_RESOURCE_LOCATION) : ClassLoader.getSystemResources(FACTORIES_RESOURCE_LOCATION));`

- **Spring Boot在启动的时候从类路径下的`META-INF/spring.factories`中获取EnableAutoConfiguration指定的值（spring-boot-autoconfigure-1.5.16.RELEASE.jar中包含了所有场景的自动配置类代码），将这些值作为自动配置类导入到容器中，自动配置类就生效，帮我们进行自动配置工作；**以前我们需要自己配置的东西，自动配置类都帮我们做了。

- 这些自动配置类是Spring Boot进行自动配置的精髓

### 1.6. 使用Spring Initialzer快速创建Spring Boot项目

#### 1.6.1. STS使用Spring Starter Project快速创建项目

默认生成的spring boot项目：

- 主程序已经生成好了，测试基础类已经生成好了
- resources文件夹目录结构
  - static：保存所有的静态资源；js、css、images；
  - templates：保存所有的模版页面；（spring boot默认jar包使用嵌入式的tomcat，默认不支持jsp）；可以使用模版引擎（freemarker、thymeleaf）；
  - application.properties：spring boot应用的配置文件，可以修改一些默认设置。

## 2. Spring Boot 配置

### 2.1. 配置文件

SpringBoot使用一个全局的配置文件，配置文件名是固定的：

- application.properties
- application.yml

配置文件的作用：修改SpringBoot自动配置的默认值；SpringBoot在底层都给我们自动配置好；

- properties:

```ini
server.port=8001
```

- yml: 以数据为中心，比json、xml等更适合做配置文件

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
k:(空格)v: 表示一对键值对（空格必须有）；
以空格的缩进来控制层级关系；只要在左对齐的一列数据，都是同一个层级的，属性和值也是大小写敏感；
```

#### 2.2.2. 值的写法

##### 2.2.2.1. 字面量

普通的值：数字、字符串、布尔

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
    last-name: Jack
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
    last-name: Jack
    age: 18
    boss: false
    birth: 2017/12/12
    maps: {k1: v1, k2: v2}
    lists: [Jack, Rose]
    dog: {name: 小狗, age: 3}
  ```

- 	javaBean：

```java
/**
 * Person 实体 '@ConfigurationProperties' 获取值
 * 
 * <pre>
 * 将配置文件中配置的每一个属性的值，映射到这个组件中
 *  `@ConfigurationProperties`: 告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定，默认从全局配置文件中获取值
 *       `(prefix = "person")`: 配置文件中哪个与下面的所有属性进行一一映射
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
        <!-- 导入配置文件处理器，配置文件进行绑定就会有提示 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <optional>true</optional>
        </dependency>
```

```ini
person : Person(lastName=Jack, age=18, boss=false, birth=Tue Dec 12 00:00:00 CST 2017, maps={k1=v1, k2=v2}, lists=[Jack, Rose], dog=Dog(name=小狗, age=3))
```

#### 2.3.2. @Value获取值

```yaml
cat:
  lastName: black
  age: 3
  birth: 2018/03/15
```

```java
/**
 * Cat 实体 '@Value' 获取值
 *
 * @author colg
 */
@Component
@Data
public class Cat implements Serializable {
    
    /*
     * colg  [xml配置]
     *  <bean id="cat" class="cn.colg.bean.Cat">
     *      <property name="lastName" value="black" />
     *      <property name="age" value="3" />
     *      <property name="birth" value="2018/03/15" />
     *  </bean>
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

#####  2.3.3.1. 获取值比较

|                      | @ConfigurationProperties | @Value     |
| -------------------- | ------------------------ | ---------- |
| 功能                 | 批量注入配置文件中的属性 | 一个个指定 |
| 松散绑定（松散语法） | 支持                     | 不支持     |
| SpEL（#{}）          | 不支持                   | 支持       |
| JSR303数据校验       | 支持                     | 不支持     |
| 复杂数据类型         | 支持                     | 不支持     |

##### 2.3.3.2. 获取值使用场景

配置文件yml还是properties他们都能获取到值；

- 如果只是在某个业务逻辑中需要获取一下配置文件中的某项值，使用`@value`

  ```java
  /**
   * HelloController
   *
   * @author colg
   */
  @RestController
  public class HelloController {
  
      @Value("${person.last-name}")
      private String name;
  
      @GetMapping("/hello")
      public String hello() {
          return "hello " + name;
      }
  
      @GetMapping("/hello2")
      public String hello2(@Value("${person.last-name}") String name2) {
          return "hello " + name2;
      }
  }
  ```

- 如果专门编写了一个javaBean来和配置文件进行映射，使用`@ConfigurationProperties`

#### 2.3.4. 加载指定配置文件

##### 2.3.4.1. @PropertySource

```ini
color.red: 红色
color.blue: 蓝色
color.createDate: 2018/01/01
color.maps.k1: v1
color.maps.k2: v2
color.lists: Jack, Rose
```

```java
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
color : Color(red=红色, blue=蓝色, createDate=Mon Jan 01 00:00:00 CST 2018, maps={k2=v2, k1=v1}, lists=[Jack, Rose])
```

##### 2.3.4.2. @ImportResource

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	   xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="phone" class="cn.colg.bean.Phone">
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
person : Person(lastName=Jack, age=18, boss=false, birth=Tue Dec 12 00:00:00 CST 2017, maps={k1=v1, k2=v2}, lists=[Jack, Rose], dog=Dog(name=小狗, age=3))
cat : Cat(lastName=black, age=3, birth=Thu Mar 15 00:00:00 CST 2018)
color : Color(red=红色, blue=蓝色, createDate=Mon Jan 01 00:00:00 CST 2018, maps={k2=v2, k1=v1}, lists=[Jack, Rose])
phone : Phone(name=oppo, color=green, age=2, maps={k1=v1, k2=v2}, lists=[Jack, Rose])
```

### 2.4. 配置文件占位符

#### 2.4.1. 随机数

```ini
${random.value}、 ${random.int}、 ${random.long}
${random.int(10)}、 ${random.int[1024,65536]}
```

#### 2.4.2. 占位符

- 可以在配置文件中引用前面配置过的属性（优先级前面配置过的这里都能用）
- ${app.name:默认值} 来指定找不到属性时的默认值  

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
taxi : Taxi(name=丰田卡罗拉f2454df0-b702-44e5-a63b-b31f9662aa8d, plateNumber=鄂FPN181, age=1, dog=Dog(name=小狗, age=1))
```

### 2.5. 多环境Profile

Profile是Spring对不同环境提 指定参数等方式快速切换环境  

- 多Profile文件

  - 格式：application-{profile}.properties/yml

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fr36l684vsj304h01q0sh.jpg)

- 多Profile文档块模式

```yaml
spring:
  profiles:
    active:
      - dev                     # 激活dev环境

# 三个短横线分隔多个profile区（文档块）
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

# profiles: default 表示未指定默认配置
---
server:
  port: 8001
spring:
  profiles: default
```

- 激活方式

  - 配置文件

  ```yaml
  spring:
    profiles:
      active:
        - dev                     # 激活dev环境
  ```

  - 命令行

  ```ini
  --spring.profiles.active=dev
  ```

  - jvm参数

  ```ini
  –Dspring.profiles.active=dev
  ```

### 2.6. 配置文件加载位置

- Spring Boot 启动会扫描以下位置的application.yml/properties作为Sprng Boot的默认配置文件
  - file:/config/
  - file:./
  - **classpath:/config/**
  - **classpath:/**
- 优先级由高到低，高优先级的配置会覆盖低优先级的配置；
- Spring Boot会从这4个位置全部加载主配置文件；互补配置；
- **spring.config.location**: F:/application.yml  手动指定配置文件位置。

### 2.7. 外部配置加载顺序

- Spring Boot 支持多种外部配置方式，这些方式优先级如下 :

1. **命令行参数**
2. 来自java:comp/env的JNDI属性
3. Java系统属性（System.getProperties()）
4. 操作系统环境变量
5. RandomValuePropertySource配置的random.*属性值
6. **jar包外部的application-{profile}.properties或application.yml(带spring.profile)配置文件**
7. **jar包内部的application-{profile}.properties或application.yml(带spring.profile)配置文件**
8. **jar包外部的application.properties或application.yml(不带spring.profile)配置文件**
9. **jar包内部的application.properties或application.yml(不带spring.profile)配置文件**
10. @Configuration注解类上的@PropertySource
11. 通过SpringApplication.setDefaultProperties指定的默认属性  

- 所有支持的配置加载来源
  - 官方文档，外部配置：https://docs.spring.io/spring-boot/docs/1.5.16.RELEASE/reference/htmlsingle/#boot-features-external-config

### 2.8. 自动配置原理

#### 2.8.1. 配置文件属性参照

https://docs.spring.io/spring-boot/docs/1.5.16.RELEASE/reference/htmlsingle/#common-application-properties

#### 2.8.2. 自动配置原理

- SpringBoot启动的时候记在主配置类，开启了自动配置功能`@EnableAutoConfiguration`

  - `@EnableAutoConfiguration`作用：

    - `@Import(EnableAutoConfigurationImportSelector.class)`：利用`EnableAutoConfigurationImportSelector`给容器中导入一些组件

    - 可以查看`AutoConfigurationImportSelector.selectImports(AnnotationMetadata) `方法中的内容；

    - `List<String> configurations = getCandidateConfigurations(annotationMetadata, attributes);`获取候选的位置

      ```java
      List<String> configurations = SpringFactoriesLoader.loadFactoryNames(getSpringFactoriesLoaderFactoryClass(), getBeanClassLoader());
      // 扫描所有jar包类路径下 "META-INF/spring.factories"
      
      Properties properties = PropertiesLoaderUtils.loadProperties(new UrlResource(url));
      // 把扫描到的这些文件的内容包装成properties对象
      // 从properties中获取到EnableAutoConfiguration.class类（类名）对应的值，然后把他们添加到容器中
      ```

- **将类路径下`META-INF/spring.factories`里面配置的所有`EnableAutoConfiguration`的值加入到了容器中；**

- 每一个自动配置类进行自动配置功能

- 以`org.springframework.boot.autoconfigure.web.HttpEncodingAutoConfiguration`为例解释自动配置原理：

  ```java
  @Configuration // 表示这是一个配置类，类似于编写的配置文件，也可以给容器中添加组件
  
  @EnableConfigurationProperties(HttpEncodingProperties.class) // 启用指定类的ConfigurationProperties功能，将配置文件中对应的值和HttpEncodingProperties进行绑定；并把HttpEncodingProperties加入到ioc容器中
  
  @ConditionalOnWebApplication // Spring底层@Conditional注解，根据不同的条件，如果满足指定的条件，整个配置类里面的配置就会生效； 判断当前应用是否是web应用，如果是，当前配置类生效
  
  @ConditionalOnClass(CharacterEncodingFilter.class) // 判断当前项目有没有指定类，CharacterEncodingFilter: SpringMVC中进行乱码解决的过滤器
  
  @ConditionalOnProperty(prefix = "spring.http.encoding", value = "enabled", matchIfMissing = true) // 判断配置文件中是否存在某个配置 spring.http.encoding，matchIfMissing = true: 如果不存在没，判断也是成立的
  // 即使配置文件中不配置 spring.http.encoding.enabled = true，也是默认生效的
  public class HttpEncodingAutoConfiguration {
      
      // 已经和SpringBoot的配置文件映射了
  	private final HttpEncodingProperties properties;
  
      // 只有一个有参构造器的情况下，参数的值就会从容器中拿
  	public HttpEncodingAutoConfiguration(HttpEncodingProperties properties) {
  		this.properties = properties;
  	}
      
  	@Bean // 给容器中添加一个组件，这个组件的某些值需要从properties中获取
  	@ConditionalOnMissingBean(CharacterEncodingFilter.class)
  	public CharacterEncodingFilter characterEncodingFilter() {
  		CharacterEncodingFilter filter = new OrderedCharacterEncodingFilter();
  		filter.setEncoding(this.properties.getCharset().name());
  		filter.setForceRequestEncoding(this.properties.shouldForce(Type.REQUEST));
  		filter.setForceResponseEncoding(this.properties.shouldForce(Type.RESPONSE));
  		return filter;
  	}
  ```

  - 根据当前不同的条件判断，决定这个配置类是否生效，一旦这个配置类生效；这个配置类就会给容器中添加各种组件；这些组件的属性是从对应的properties类中获取的，这些类里面的每一个属性又是和配置文件绑定的；

- 所有在配置文件中能配置的属性都是在xxxProperties类中封装着，配置文件能配置什么就可以参照某个功能对应的这个属性类

  ```java
  @ConfigurationProperties(prefix = "spring.http.encoding") // 从配置文件中获取指定的值和bean的属性进行绑定
  public class HttpEncodingProperties {
  
  	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
  ```

- **精髓：**

  - **SpringBoot启动会加载大量的自动配置类**
  - **看需要的功能有没有SpringBoot默认写好的自动配置类**
  - **再看这个自动配置类中到底配置了哪些组件（只要我们要用的组件有，就不需要再来配置了）**
  - **给容器中自动配置类添加组件的时候，会从properties类中获取某些属性。我们就可以在配置文件中指定这些属性的值**

- **通用模式**

  - **xxxAutoConfiguration：自动配置类，给容器中添加组件**
  - **xxxProperties：属性配置类，封装配置文件中相关属性**

  ```yaml
  # 能配置的属性都是来源于这个功能的xxxProperties类
  spring:
    http:
      encoding:
        enabled: true
        charset: UTF-8
        force: true
  ```

- `@Conditional`扩展

  | @Conditional扩展注解            | 作用（判断是否满足当前指定条件）                 |
  | ------------------------------- | ------------------------------------------------ |
  | @ConditionalOnJava              | 系统的java版本是否符合要求                       |
  | @ConditionalOnBean              | 容器中存在指定Bean                               |
  | @ConditionalOnMissingBean       | 容器中不存在指定Bean                             |
  | @ConditionalOnExpression        | 满足SpEL表达式指定                               |
  | @ConditionalOnClass             | 系统中有指定的类                                 |
  | @ConditionalOnMissingClass      | 系统中没有指定的类                               |
  | @ConditionalOnSingleCandidate   | 容器中只有一个指定的Bean，或者这个Bean是首选Bean |
  | @ConditionalOnProperty          | 系统中指定的属性是否有指定的值                   |
  | @ConditionalOnResource          | 类路径下是否存在指定资源文件                     |
  | @ConditionalOnWebApplication    | 当前是web环境                                    |
  | @ConditionalOnNotWebApplication | 当前不是web环境                                  |
  | @ConditionalOnJndi              | JNDI存在指定项                                   |

- 自动配置类必须在一定条件下才能生效，可以通过启用`debug: true`属性，来让控制台打印自动配置报告，这样就可以很方便的指定哪些配置类生效

  ```yaml
  # 开启SpringBoot的debug，查看详细的自动配置报告
  debug: true
  ```

  ```java
  =========================
  AUTO-CONFIGURATION REPORT // 自动配置报告
  =========================
  
  
  Positive matches: // 自动配置类启用的
  -----------------
  
     DispatcherServletAutoConfiguration matched:
        - @ConditionalOnClass found required class 'org.springframework.web.servlet.DispatcherServlet'; @ConditionalOnMissingClass did not find unwanted class (OnClassCondition)
        - @ConditionalOnWebApplication (required) found StandardServletEnvironment (OnWebApplicationCondition)
  
     DispatcherServletAutoConfiguration.DispatcherServletConfiguration matched:
        - @ConditionalOnClass found required class 'javax.servlet.ServletRegistration'; @ConditionalOnMissingClass did not find unwanted class (OnClassCondition)
        - Default DispatcherServlet did not find dispatcher servlet beans (DispatcherServletAutoConfiguration.DefaultDispatcherServletCondition)
            
            
  Negative matches: // 自动配置类未启用的
  -----------------
  
     ActiveMQAutoConfiguration:
        Did not match:
           - @ConditionalOnClass did not find required classes 'javax.jms.ConnectionFactory', 'org.apache.activemq.ActiveMQConnectionFactory' (OnClassCondition)
  
     AopAutoConfiguration:
        Did not match:
           - @ConditionalOnClass did not find required classes 'org.aspectj.lang.annotation.Aspect', 'org.aspectj.lang.reflect.Advice' (OnClassCondition) // 缺少 Aspect、Advice类
               
               
  Exclusions: // 排除的自动配置类
  -----------
  
      None
  
  
  Unconditional classes: // 无条件启用的自动配置类
  ----------------------
  
      org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration
  
      org.springframework.boot.autoconfigure.web.WebClientAutoConfiguration
  ```

## 3. Spring Boot 日志

### 3.1. 日志框架

- 案例

  ```ini
  小张，开发一个大型系统：
  	1、System.out.println(""); 将关键数据打印在控制台；去掉？写在一个文件？
  	2、框架来记录系统的一些运行信息；日志框架；zhanglogging.jar
  	3、高大上的几个功能？异步模式？自动归档？xxxx？zhanglogging-good.jar
  	4、将以前框架卸下来？换上新的框架，重新修改之前相关的API；zhanglogging-profect.jar
  	5、JDBC---数据库驱动；
  		写了一个统一的接口层；日志门面（日志的一个抽象层）；logging-abstract.jar
  		给项目中导入具体的日志实现就行了；之前的日志框架都是实现的抽象层
  ```

- 市面上的日志框架

  - JUL（java.util.logging）、JCL（Apache Commons Logging）、Log4j、Log4j2、Logback、SLF4j、jboss-logging等

- SpringBoot使用的日志框架

  - SpringBoot底层是Spring框架，Spring框架默认使用JCL，spirng-boot-starter-logging采用了slf4j+logback形式，Spring Boot也能自动适配（jul、log4j2、logback）并简化配置

  | 日志门面 （日志的抽象层）                                    | 日志实现                                                     |
  | ------------------------------------------------------------ | ------------------------------------------------------------ |
  | ~~JCL（Jakarta Commons Logging）~~<br />**SLF4j（Simple Logging Facade for Java）**<br />~~jboss-logging~~ | Log4j<br />JUL（java.util.logging）<br />Log4j2<br />**Logback** |

  - 左边选一个门面（SLF4j）、右边选一个实现（Logback）

### 3.2. SLF4j使用

- 如何在系统中使用SLF4j

  - 以后开发的时候，日志记录方法的调用，不应该来直接调用日志的实现类，而是调用日志抽象层里面的方法；给系统里导入slf4j的jarjar和logback的实现jar

  ```java
  import org.slf4j.Logger;
  import org.slf4j.LoggerFactory;
  
  /**
   * 使用SLF4j
   *
   * @author colg
   */
  public class HelloWorld {
  
      public static void main(String[] args) {
          Logger logger = LoggerFactory.getLogger(HelloWorld.class);
          logger.info("Hello World");
      }
  
  }
  ```

  - 图示：

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fvn28r56yqj30w00homz4.jpg)

  每一个日志的实现框架都有自己的配置文件。**使用SLF4j以后，配置文件还是做成日志实现框架自己本身的配置文件**；

### 3.3. 遗留问题

- a（slf4j+logback）：Spring（commons-logging）、Hibernate（jboss-logging）、Mybatis、xxx

  - 问题：统一日志记录，即使是别的框架也要统一使用slf4j进行输出？

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fvn2nspe4bj31830v70xc.jpg)

- **如何让系统中所有的日志都统一到slf4j：**
  - **将系统中其他日志框架先排除出去；**
  - **用中间包来替换原有的日志框架；**
  - **导入slf4j其他的实现。**

### 3.4. SpirngBoot 日志关系

- pom.xml

```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter</artifactId>
		</dependency>
```

- SpirngBoot用它来做日志功能：

```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-logging</artifactId>
		</dependency>
```

- 底层依赖关系：

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fvn3ahsd34j30ia0edt9z.jpg)

- 总结：

  - SpringBoot底层也是使用slf4+logback的方式进行日志记录
  - SpringBoot也把其他的日志都替换成了slf4j
  - 中间替换包：jcl-over-slf4j

  ```java
  public abstract class LogFactory {
  
      static String UNSUPPORTED_OPERATION_IN_JCL_OVER_SLF4J = "http://www.slf4j.org/codes.html#unsupported_operation_in_jcl_over_slf4j";
  
      static LogFactory logFactory = new SLF4JLogFactory();
  ```

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fvn3ftxgpvj30it056mxd.jpg)

  	

  - 如果要引入其他框架，一定要把这个框架的默认日志依赖移除掉

  ```xml
  		<dependency>
  			<groupId>org.springframework</groupId>
  			<artifactId>spring-core</artifactId>
  			<exclusions>
  				<exclusion>
  					<groupId>commons-logging</groupId>
  					<artifactId>commons-logging</artifactId>
  				</exclusion>
  			</exclusions>
  		</dependency>
  ```

  - **Spring Boot能自动适配所有的日志，而且底层使用slf4j+logback的方式记录日志，引入其他框架的时候，只需要把这个框架依赖的日志框架排除掉。**

### 3.5. Spring Boot 日志默认配置

## 4. Spring Boot Web开发

## 5. Docker

## 6. SpringBoot与数据访问

## 7. 自定义starter

## 8. 更多SpringBoot整合示例