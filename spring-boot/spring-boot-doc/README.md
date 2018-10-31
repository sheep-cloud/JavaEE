# spring-boot

## 1. SpringBoot 入门

### 1.1. SpingBoot简介

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

	 	javaBean：

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

  ​	

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

### 3.5. SpringBoot 日志默认配置

```java
/**
 * Spring Boot 日志默认配置 测试
 *
 * @author colg
 */
public class LoggingTest extends SpringBoot06LoggingApplicationTests {

    /** 日志记录器 */
    Logger logger = LoggerFactory.getLogger(LoggingTest.class);

    @Test
    public void loggerTest() {
        /*
         * colg  [日志级别]
         *  由低到高: trace < debug < info < warn < error
         *  可以调整输出的日志级别；日志就会只在这个级别以后的高级别生效
         */
        logger.trace("这是trace日志...");
        logger.debug("这是debug日志...");
        // Spring Boot 默认使用的是info级别的
        logger.info("这是info日志...");
        logger.warn("这是warn日志...");
        logger.error("这是error日志...");
    }
```

```yaml
logging:
  level:
    cn.colg.logging: trace
# 日志文件名：当前项目下生成springboot.log日志，也可以指定完整的路径
#  file: springboot.log
  file: D:/workspace-all/atguigu/Java-all/JavaEE/spring-boot/spring-boot-doc/springboot.log
  
# 日志文件的位置：当前磁盘的根路径下创建spirng文件夹和log文件夹；使用spring.log作为默认文件
#  path: /spring/log
  path: D:/workspace-all/atguigu/Java-all/JavaEE/spring-boot/spring-boot-doc/spring/log
  
# logging.file和logging.path同时指定时，以logging.file为准
```

| logging.file | logging.path | Example        | Description                        |
| ------------ | ------------ | -------------- | ---------------------------------- |
| (none)       | (none)       | 只在控制台输出 |                                    |
| 指定文件名   | (none)       | my.log         | 输出日志到my.log文件               |
| (none)       | 指定目录     | /var/log       | 输出到指定目录的 spring.log 文件中 |

### 3.6. SpringBoot 日志指定配置

给类路径下放上每个日志框架自己的日志文件即可；SpringBoot就不使用默认的配置了。

- 自定义日志规则

| Logging System          | Customization                                                |
| ----------------------- | ------------------------------------------------------------ |
| Logback                 | `logback-spring.xml`, `logback-spring.groovy`, `logback.xml` or `logback.groovy` |
| Log4j2                  | `log4j2-spring.xml` or `log4j2.xml`                          |
| JDK (Java Util Logging) | `logging.properties`                                         |

​		

- Logback使用的日志文件

  - logback.xml：直接就被日志框架识别了
  - logback-spring.xml：日志框架就不直接加载日志的配置项，由SpringBoot解析日志配置，可以使用SpringBoot的高级Profile功能

  ```xml
  <!-- 可以指定某段配置只在某个环境下生效 -->
  <springProfile name="staging">
      <!-- configuration to be enabled when the "staging" profile is active -->
  </springProfile>
  
  <springProfile name="dev, staging">
      <!-- configuration to be enabled when the "dev" or "staging" profiles are active -->
  </springProfile>
  
  <springProfile name="!production">
      <!-- configuration to be enabled when the "production" profile is not active -->
  </springProfile>
  ```

## 4. SpringBoot Web开发

### 4.1. 使用SpringBoot

- 创建SpringBoot应用，选中需要的模块
- SpringBoot已经默认将这些场景配置好了，只需要在配置文件中指定少量配置就可以运行起来
- 自己编写业务代码

### 4.2. 自动配置原理

- 这个场景SpringBoot帮我们配置了什么？能不能修改？能修改哪些配置？能不能扩展？xxx

```ini
WebMvcAutoConfiguration: 给容器中自动配置组件；
WebMvcProperties: 配置类来封装配置文件的内容；
```

### 4.3. 静态资源映射规则

```java
@ConfigurationProperties(prefix = "spring.resources", ignoreUnknownFields = false)
public class ResourceProperties implements ResourceLoaderAware, InitializingBean {
    // 可以设置和资源有关的参数，缓存时间
```

```java
		// 配置静态资源映射
		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			if (!this.resourceProperties.isAddMappings()) {
				logger.debug("Default resource handling disabled");
				return;
			}
            // webjars 资源映射
			Integer cachePeriod = this.resourceProperties.getCachePeriod();
			if (!registry.hasMappingForPattern("/webjars/**")) {
				customizeResourceHandlerRegistration(registry
						.addResourceHandler("/webjars/**")
						.addResourceLocations("classpath:/META-INF/resources/webjars/")
						.setCachePeriod(cachePeriod));
			}
            // 自定义资源映射
			String staticPathPattern = this.mvcProperties.getStaticPathPattern();
			if (!registry.hasMappingForPattern(staticPathPattern)) {
				customizeResourceHandlerRegistration(
						registry.addResourceHandler(staticPathPattern)
								.addResourceLocations(
										this.resourceProperties.getStaticLocations())
								.setCachePeriod(cachePeriod));
			}
		}

		// 配置欢迎页映射
		@Bean
		public WelcomePageHandlerMapping welcomePageHandlerMapping(
				ResourceProperties resourceProperties) {
			return new WelcomePageHandlerMapping(resourceProperties.getWelcomePage(),
					this.mvcProperties.getStaticPathPattern());
		}

		// 配置图标
		@Configuration
		@ConditionalOnProperty(value = "spring.mvc.favicon.enabled", matchIfMissing = true)
		public static class FaviconConfiguration {

			private final ResourceProperties resourceProperties;

			public FaviconConfiguration(ResourceProperties resourceProperties) {
				this.resourceProperties = resourceProperties;
			}

			@Bean
			public SimpleUrlHandlerMapping faviconHandlerMapping() {
				SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
				mapping.setOrder(Ordered.HIGHEST_PRECEDENCE + 1);
                // 所有 **/favicon.ico
				mapping.setUrlMap(Collections.singletonMap("**/favicon.ico",
						faviconRequestHandler()));
				return mapping;
			}

			@Bean
			public ResourceHttpRequestHandler faviconRequestHandler() {
				ResourceHttpRequestHandler requestHandler = new ResourceHttpRequestHandler();
				requestHandler
						.setLocations(this.resourceProperties.getFaviconLocations());
				return requestHandler;
			}

		}
```

```java
	private String[] getStaticWelcomePageLocations() {
		String[] result = new String[this.staticLocations.length];
		for (int i = 0; i < result.length; i++) {
			String location = this.staticLocations[i];
			if (!location.endsWith("/")) {
				location = location + "/";
			}
			result[i] = location + "index.html";
		}
		return result;
	}
```
- `/webjars/**`，都去`classpath:/META-INF/resources/webjars/`找资源;

  - webjars：以jar包的方式引入静态资源 https://www.webjars.org/

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fvo6dep2wlj30ct06tt8m.jpg)

  ```xml
          <!-- 引入jquery-webjars -->
          <dependency>
              <groupId>org.webjars</groupId>
              <artifactId>jquery</artifactId>
              <version>3.3.1-1</version>
          </dependency>
  ```

  ```ini
  # 访问资源
  http://localhost:8080/webjars/jquery/3.3.1-1/jquery.js
  ```

- `/**`，访问当前项目的任何资源，（静态资源的文件夹）

  ```ini
  "classpath:/META-INF/resources/",
  "classpath:/resources/",
  "classpath:/static/",
  "classpath:/public/
  "/": 当前项目的根路径
  ```

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fvo6tko8lsj30dh0cdgln.jpg)

  ```ini
  # 访问资源
  http://localhost:8080/asserts/img/bootstrap-solid.svg
  http://localhost:8080/asserts/css/bootstrap.min.css
  http://localhost:8080/asserts/js/jquery-3.2.1.slim.min.js
  ```

- `index.html，欢迎页，静态资源文件夹下的所有index.html页面；被`/**`映射

  ```ini
  # 欢迎页，找index.html页面
  http://localhost:8080/
  ```

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fvo739zs35j308t0300sl.jpg)

- `**/favicon.ico`，图标都是在静态资源换文件下找

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fvo7b5swk7j308u03r746.jpg)

### 4.4. Thymeleaf  模版引擎

#### 4.4.1. 整合Thymeleaf

- 引入thymeleaf

  ```xml
          <!-- 覆盖 thymeleaf 版本号 --> 
          <thymeleaf.version>3.0.9.RELEASE</thymeleaf.version>
          <!-- 布局功能的支持程序，thymeleaf3主程序适配layout2以上版本 -->
          <thymeleaf-layout-dialect.version>2.3.0</thymeleaf-layout-dialect.version>        
  		<!-- 引入thymeleaf，默认为2.16版本，推荐修改为3.x版本 -->
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-thymeleaf</artifactId>
          </dependency>
  ```

#### 4.4.2. Thymeleaf使用&语法

##### 4.4.2.1. Thymeleaf使用

```java
@ConfigurationProperties(prefix = "spring.thymeleaf")
public class ThymeleafProperties {

	private static final Charset DEFAULT_ENCODING = Charset.forName("UTF-8");

	private static final MimeType DEFAULT_CONTENT_TYPE = MimeType.valueOf("text/html");

	public static final String DEFAULT_PREFIX = "classpath:/templates/";

	public static final String DEFAULT_SUFFIX = ".html";
    // 只要把HTML页面放在classpath:/templates/，thymeleaf就能自动渲染；
```

只要把HTML页面放在`classpath:/templates/`，thymeleaf就能自动渲染

```java
/**
 * ThymeleafController
 *
 * @author colg
 */
@Controller
public class ThymeleafController {

    @GetMapping("/success")
    public String success() {
        // classpath:/templates/success.html
        return "success";
    }
}

```

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fvo84g1hcij309l04bq2u.jpg)

- 使用：

  - 导入thymeleaf的名称空间

    ```html
    <html lang="en" xmlns:th="http://www.thymeleaf.org">
    ```

  - 使用thymeleaf语法

    ```html
    <!DOCTYPE html>
    <html lang="en" xmlns:th="http://www.thymeleaf.org">
    <head>
    <meta charset="UTF-8">
    <title>success2</title>
    </head>
    <body>
        <h1>Spring Boot Success2</h1>
        <!-- th:text 将div里面的文本内容设置为 -->
        <div th:text="${hello}">这是显示欢迎信息</div>
    </body>
    </html>
    ```

##### 4.4.2.2. Thymeleaf语法

```ini
# 简单表达
${...}: 变量表达式
*{...}: 选择变量表达式，和${...}在功能上是一样的
#{...}: 获取国际化消息
@{...}: url表达式
~{...}: 片段引用表达式
# 字面
文本文字: 'one text'，'Another one!'，...
号码文字: 0，34，3.0，12.3，...
布尔文字: true, false
空字面: null
文字标记: one, sometext，main，...
# 文字操作
字符串连接: +
文字替换: |The name is ${name}|
# 算术运算
二元运算符: +，-，*，/，%
减号（一元运算符）: -
布尔运算:
二元运算符: and, or
布尔否定（一元运算符）: !, not
# 比较运算
比较: >，<，>=，<=（gt，lt，ge，le）
相等: ==，!=（eq，ne）
# 条件运算
IF-THEN: (if) ? (then)
IF-THEN-ELSE: (if) ? (then) : (else)
默认: (value) ?: (defaultvalue)
# 特殊
无操作: _
```

#### 4.4.3. SpringMVC自动配置

##### 4.4.3.1. SpringMVC自动配置

https://docs.spring.io/spring-boot/docs/1.5.16.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications

Spring 
Boot自动配置好了SpringMVC

以下是SpringBoot对SpringMVC的默认配置：

- 自动配置了ViewResolver（视图解析器：根据方法的返回值得到视图对象（View），视图对象决定如何渲染（转发？重定向））
  - ``ContentNegotiatingViewResolver` `：组合所有的视图解析器；
  - 如何定制：可以给容器中添加一个视图解析器，自动将其组合起来
- 静态资源文件夹路径、webjars
- 自动注册了`Converter`，`GenericConverter`，`Formatter` 
  - `Converter`：转换器；public String hello(User user); 类型转换器使用`Converter`
  - `Formatter` ：格式化器；2018/09/28 == Date;
  - 自己添加的转换器只需要放在容器中即可
- `HttpMessageConverters ：SpringMVC用来转换Http请求和响应的；user=json
  - `HttpMessageConverters`：是从容器中确定的；获取所有的`HttpMessageConverter`

##### 4.3.3.2. 扩展SpringMVC

```xml
    <mvc:view-controller path="/success" view-name="success"/>
    <mvc:interceptors>
        <mvc:interceptor>
            <mvc:mapping path="success"/>
            <bean></bean>
        </mvc:interceptor>
    </mvc:interceptors>
```

编写一个配置类（@Configuration），是`WebMvcConfigurerAdapter`类型；不能标注`EnableWebMvc`

#### 4.4.4. 如何修改默认配置

- 模式

  - SpringBoot在自动配置很多组件的时候，先看容器中有没有用户自己配置的（@Bean、@Component），如果有就用用户配置的，如果没有，才自动配置；如果有些组件可以有多个（ViewResolver）将用户配置的和自己默认的组合起来；

  ```java
  /**
   * <pre>
   * SpringMVC 扩展
   *  `WebMvcConfigurerAdapter`： 扩展SpringMVC的功能
   * </pre>
   *
   * @author colg
   */
  @Configuration
  public class MyMvcConfig extends WebMvcConfigurerAdapter{
  
      /**
       * 视图映射
       *
       * @param registry
       */
      @Override
      public void addViewControllers(ViewControllerRegistry registry) {
          // 浏览器发送 /colg 请求来到 success 页面
          // 请求: http://localhost:8080/colg
          // 跳转: classpath:/templates/success.html
          registry.addViewController("/colg").setViewName("success");
      }
  }
  ```

  - 既保留了所有的自动配置，也能用扩展的配置

- 原理：

  - `WebMvcAutoConfiguration`：SpringMVC的自动配置类
  - `@Import(EnableWebMvcConfiguration.class)`：在做其他自动配置时会导入`EnableWebMvcConfiguration`

  ```java
  	@Configuration
  	public static class EnableWebMvcConfiguration extends DelegatingWebMvcConfiguration {
  ```

  ```java
  	// 从容器中获取所有的WebMvcConfigurer
  	@Autowired(required = false)
  	public void setConfigurers(List<WebMvcConfigurer> configurers) {
  		if (!CollectionUtils.isEmpty(configurers)) {
  			this.configurers.addWebMvcConfigurers(configurers);
              // 一个参考实现；将所有的WebMvcConfigurer相关配置都来一起调用
              /*
              @Override
              protected void addViewControllers(ViewControllerRegistry registry) {
                  this.configurers.addViewControllers(registry);
              }
              */
  		}
  	}
  ```

  - 容器中所有的 WebMvcConfigurer都会一起起作用

  - 自己写的配置类也会被调用

    - 效果：SpringMVC的自动配置和扩展配置都会起作用

  - **全面接管SpringMVC**

    - SpringBot对SprngMVC的自动配置就不需要了，所有的都是自己配置；所有的SpringMVC自动配置都时效
    - 在配置类中添加`@EnableWebMvc`即可

  - `@EnableWebMvc`原理

    ```java
    @Import(DelegatingWebMvcConfiguration.class)
    public @interface EnableWebMvc {
    }
    ```

    ```java
    @Configuration
    public class DelegatingWebMvcConfiguration extends WebMvcConfigurationSupport {
    ```

    - 自动配置类

    ```java
    @Configuration
    @ConditionalOnWebApplication
    @ConditionalOnClass({ Servlet.class, DispatcherServlet.class,
    		WebMvcConfigurerAdapter.class })
    // 容器中没有这个组件的时候，这个自动配置类才生效
    @ConditionalOnMissingBean(WebMvcConfigurationSupport.class)
    @AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE + 10)
    @AutoConfigureAfter({ DispatcherServletAutoConfiguration.class,
    		ValidationAutoConfiguration.class })
    public class WebMvcAutoConfiguration {
    ```

    - `@EnableWebMvc`将`WebMvcConfigurationSupport`组件导入进来了，导入了SpringMVC最基本的功能
    - 在SpringBoot中会有非常多的`xxxConfigurer`帮助扩展配置

#### 4.4.5. RestfulCRUD

##### 4.4.5.1. 配置首页

- 方式一

  ```java
  /**
   * LoginController
   *
   * @author colg
   */
  @Controller
  public class LoginController {
  
      /**
       * 使用空的controller跳转登录页
       *
       * @return
       * @author colg
       */
      @GetMapping({"/", "/index", "index.html"})
      public String login() {
          return "login";
      }
  
  }
  ```

- 方式二

  ```java
  /**
   * <pre>
   * SpringMVC 扩展
   *  `WebMvcConfigurerAdapter`： 扩展SpringMVC的功能
   * </pre>
   *
   * @author colg
   */
  // @EnableWebMvc 不要接管SpringMVC
  @Configuration
  public class MyMvcConfig extends WebMvcConfigurerAdapter {
  
      /**
       * 视图映射 <br>
       * 所有的WebMVcConfigurerAdapter组件都会一起起作用
       *
       * @param registry
       */
      @Override
      public void addViewControllers(ViewControllerRegistry registry) {
          // 浏览器发送 /colg 请求来到 success 页面
          // 请求: http://localhost:8080/colg 跳转: classpath:/templates/success.html
          registry.addViewController("/colg").setViewName("success");
          
          // 跳转: classpath:/templates/login.html
          registry.addViewController("/").setViewName("login");
          registry.addViewController("/index").setViewName("login");
          registry.addViewController("/index.html").setViewName("login");
      }
  
  }
  ```

##### 4.4.5.2. 引入静态资源

```html
<!-- Bootstrap core CSS -->
<link href="asserts/css/bootstrap.min.css" th:href="@{/asserts/css/bootstrap.min.css}" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="asserts/css/signin.css" th:href="@{/asserts/css/signin.css}" rel="stylesheet">
```

```yaml
server:
  context-path: /crud
```

```html
<!-- Bootstrap core CSS -->
<link href="/crud/asserts/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="/crud/asserts/css/signin.css" rel="stylesheet">
```

修改server.context-path后，不受影响

##### 4.4.5.3. 国际化

- SpringMVC国际化步骤
  - 编写国际化配置文件
  - 使用ResourceBundleMessageSource管理国际化资源文件
  - 在页面使用fmt:message取出国际化内容

- SpringBoot国际化步骤

  - 编写国际化配置文件，抽取页面需要显示的国际化消息

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fvq7k10xgoj307c061745.jpg)

  ```ini
  login.tip=请登录
  login.username=用户名
  login.password=密码
  login.remember=记住我
  login.btn=登录
  login.chinese=中文
  login.english=英文
  ```

  - SpringBoot自动配置好了管理国际化资源文件的组件；

    - SpringBoot自动配置国际化

      ```java
      @ConfigurationProperties(prefix = "spring.messages")
      public class MessageSourceAutoConfiguration {
          // 配置文件可以直接放在类路径下叫messages.properties
      	private String basename = "messages";
          
      	@Bean
      	public MessageSource messageSource() {
      		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
      		if (StringUtils.hasText(this.basename)) {
                  // 设置国际化资源文件的基础名（去掉语言国家代码）
      			messageSource.setBasenames(StringUtils.commaDelimitedListToStringArray(
      					StringUtils.trimAllWhitespace(this.basename)));
      		}
      		if (this.encoding != null) {
      			messageSource.setDefaultEncoding(this.encoding.name());
      		}
      		messageSource.setFallbackToSystemLocale(this.fallbackToSystemLocale);
      		messageSource.setCacheSeconds(this.cacheSeconds);
      		messageSource.setAlwaysUseMessageFormat(this.alwaysUseMessageFormat);
      		return messageSource;
      	}
      ```

    - 修改配置文件名称

      ```yaml
      spring:
        messages:
          basename: i18n.login
      ```

  - 在页面获取国际化内容

    ```html
    <body class="text-center">
        <form class="form-signin" action="dashboard.html">
            <img class="mb-4" src="asserts/img/bootstrap-solid.svg" th:src="@{/asserts/img/bootstrap-solid.svg}" alt="" width="72" height="72">
            <h1 class="h3 mb-3 font-weight-normal">[[#{login.tip}]]</h1>
            <label class="sr-only">[[#{login.username}]]</label>
            <input type="text" class="form-control" placeholder="Username" th:placeholder="#{login.username}" required="" autofocus="">
            <label class="sr-only">[[#{login.password}]]</label>
            <input type="password" class="form-control" placeholder="Password" th:placeholder="#{login.password}" required="">
            <div class="checkbox mb-3">
                <label>
                    <input type="checkbox" value="remember-me"> [[#{login.remember}]]
                </label>
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit">[[#{login.btn}]]</button>
            <p class="mt-5 mb-3 text-muted">© 2017-2018</p>
            <a class="btn btn-sm">[[#{login.chinese}]]</a>
            <a class="btn btn-sm">[[#{login.english}]]</a>
        </form>
    </body>
    ```

  - 国际化原理：

    - Locale（区域信息对象）；LocaleResolver（获取区域信息对象）

    ```java
    		@Bean
    		@ConditionalOnMissingBean
    		@ConditionalOnProperty(prefix = "spring.mvc", name = "locale")
    		public LocaleResolver localeResolver() {
    			if (this.mvcProperties
    					.getLocaleResolver() == WebMvcProperties.LocaleResolver.FIXED) {
    				return new FixedLocaleResolver(this.mvcProperties.getLocale());
    			}
                // 如果没有设置，就根据请求头"Accept-Language"获取区域信息进行国际化
    			AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
    			localeResolver.setDefaultLocale(this.mvcProperties.getLocale());
    			return localeResolver;
    		}
    ```

    - 点击链接实现国际化

    ```html
            <a class="btn btn-sm" th:href="@{/index.html(language='zh_CN')}">[[#{login.chinese}]]</a>
            <a class="btn btn-sm" th:href="@{/index.html(language='en_US')}">[[#{login.english}]]</a>
    ```

    ```java
    /**
     * 可以在链接上携带区域信息
     *
     * @author colg
     */
    public class MyLocaleResolver implements LocaleResolver {
    
        /**
         * 区域信息解析器 <br>
         * 通过给定的请求解析当前的语言环境
         *
         * @param request
         * @return
         */
        @Override
        public Locale resolveLocale(HttpServletRequest request) {
            String language = request.getParameter("language");
            Locale locale = Locale.getDefault();
            if (StrUtil.isNotEmpty(language)) {
                // 获取 语言_国家
                String[] split = language.split("_");
                locale = new Locale(split[0], split[1]);
            }
            return locale;
        }
    
        @Override
        public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {}
    
    }
    ```

    ```java
    /**
     * <pre>
     * SpringMVC 扩展
     *  `WebMvcConfigurerAdapter`： 扩展SpringMVC的功能
     * </pre>
     *
     * @author colg
     */
    // @EnableWebMvc 不要接管SpringMVC
    @Configuration
    public class MyMvcConfig extends WebMvcConfigurerAdapter {
    
        /**
         * 区域解析
         *
         * @return
         * @author colg
         */
        @Bean
        public LocaleResolver localeResolver() {
            return new MyLocaleResolver();
        }
    }
    ```

##### 4.4.5.4. 登录

- 开发期间模版引擎页面修改以后，要实时生效

  ```yaml
  spring:
    thymeleaf:
      cache: false              # 禁用模版引擎缓存
  ```

- 登录错误消息的显示

  ```java
      @PostMapping("/user/login")
      public String login(String username, String password, Model model) {
          if (StrUtil.isNotBlank(username) && "123456".equals(password)) {
              // 登录成功
              // return "dashboard";
              // 防止表单重复提交，使用重定向到主页
              return "redirect:/main.html";
          } else {
              // 登录失败
              model.addAttribute("msg", "用户名或密码错误");
              return "login";
          }
      }
  ```

  ```html
          <!--/* 条件判断 */-->
          <p style="color: red;" th:if="${!#strings.isEmpty(msg)}">[[${msg}]]</p>
  ```

  ```java
      // 跳转: classpath:/templates/dashboard.html
      registry.addViewController("/main.html").setViewName("dashboard");
  ```

- 拦截器进行登录检查

  - 登录成功，用户信息放入session

  ```java
      @PostMapping("/user/login")
      public String login(String username, String password, Model model, HttpSession session) {
          if (StrUtil.isNotBlank(username) && "123456".equals(password)) {
              // 登录成功
              session.setAttribute("loginUser", username);
              
              // return "dashboard";
              // 防止表单重复提交，使用重定向到主页
              return "redirect:/main.html";
          } else {
              // 登录失败
              model.addAttribute("msg", "用户名或密码错误");
              return "login";
          }
      }
  ```

  - 配置拦截器

  ```java
  public class LoginHandlerInterceptor implements HandlerInterceptor {
  
      @Override
      public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
          Object loginUser = request.getSession().getAttribute("loginUser");
          if (loginUser == null) {
              // 未登录，返回登录页面
              request.setAttribute("msg", "没有权限，请先登录");
              request.getRequestDispatcher("/index.html").forward(request, response);
              return false;
          } else {
              // 已登录，放行
              return true;
          }
      }
  }
  ```

  ```java
      @Override
      public void addInterceptors(InterceptorRegistry registry) {
          // SpringBoot已经做好了静态资源映射，不需要再处理
          registry.addInterceptor(new LoginHandlerInterceptor())
                  // 拦截的请求
                  .addPathPatterns("/**")
                  // 不拦截的请求
                  .excludePathPatterns("/", "/index", "/index.html", "/user/login");
      }
  ```

##### 4.4.5.5. CRUD-员工列表

###### 4.4.5.5.1. 要求

- CRUD满足Restful风格

  - URI：/资源名称/资源标识  HTTP请求区分对资源CRUD操作

  |      | 普通CRUD（uri来区分操作） | RestfulCR         |
  | ---- | ------------------------- | ----------------- |
  | 查询 | getEmp                    | emp---GET         |
  | 添加 | addEmp?xxx                | emp---POST        |
  | 修改 | updateEmp?id=xxx&xxx=xxx  | emp/{id}---PUT    |
  | 删除 | deleteEmp?id=xxx          | emp/{id}---DELETE |

- 请求架构

  |                                  | 请求URI  | 请求方式 |
  | -------------------------------- | -------- | -------- |
  | 查询所有员工                     | emps     | GET      |
  | 查询某个员工                     | emp/{id} | GET      |
  | 跳转添加页面                     | emp      | GET      |
  | 添加员工                         | emp      | POST     |
  | 跳转修改页面（查出员工信息回显） | emp/{id} | GET      |
  | 修改员工                         | emp      | PUT      |
  | 删除员工                         | emp/{id} | DELETE   |

###### 4.4.5.5.1. 实现

- 员工列表

  - thymeleaf公共页面元素抽取，引用

    ```html
    <!-- 1. 抽取公共片段 -->
    	<div th:fragment="copy">
          &copy; 2011 The Good Thymes Virtual Grocery
        </div>
    
    <!-- 2. thymeleaf引用公共页面 -->
    <div th:replace="~{footer :: copy}"></div>
    <!-- 
    	~{templatename::selector} 模板名::选择器
    	~{templatename::fragmentname} 模板名::片段名
    -->
    
    <!-- 3. 默认效果
    		insert的功能片段写在div标签里
    		如果使用th:insert等属性进行引入，可以不用写~{}
    		行内写法需要加上~{}：[[~{}]], [(~{})]
    -->
    ```

  - 页面抽取，引用三种方式

    ```html
    <!-- 抽取 -->
    <footer th:fragment="copy">
      &copy; 2011 The Good Thymes Virtual Grocery
    </footer>
    
    <!-- 引用 -->
    <div th:insert="footer :: copy"></div>
    <div th:replace="footer :: copy"></div>
    <div th:include="footer :: copy"></div>
    
    <!-- 效果 -->
    <div>
        <footer>
            &copy; 2011 The Good Thymes Virtual Grocery
        </footer>
    </div>
    
    <footer>
        &copy; 2011 The Good Thymes Virtual Grocery
    </footer>
    
    <div>
        &copy; 2011 The Good Thymes Virtual Grocery
    </div>
    ```

  - 引入片段的时候传入参数

    ```html
    <nav th:replace="commons/bar::#sidebar(activeUri = 'main.html')"></nav>
    <nav th:replace="commons/bar::#sidebar(activeUri = 'emps')"></nav>
    ```

- 员工添加

  - SpringBoot日期的格式化（页面提交数据）

    - 默认日期格式

    ```java
    	/**
    	 * Date format to use (e.g. dd/MM/yyyy).
    	 */
    	private String dateFormat;
    ```

    - 修改默认格式

    ```yaml
      mvc:
        date-format: yyyy-MM-dd HH:mm:ss
    ```

- 员工修改

  - 回显radio、select

  ```html
  th:value="${#dates.format(emp.birth, 'yyyy-MM-dd HH:mm:ss')}"
  th:checked="${emp.gender == 1}
  th:selected="${emp.department.id == dept.id}
  ```

- 员工删除

  ```html
  <!--/* 设置自定义属性 */-->
  <button class="btn btn-sm btn-danger deleteBtn" th:attr="del_uri=@{/emp/{empId}(empId=${emp.id})}">删除</button>
  
  <!--/* 删除，delete请求 */-->
  <form id="deleteEmpForm" method="post" style="display: inline-block;">
      <input type="hidden" name="_method" value="delete" />
  </form>
  
  $(".deleteBtn").click(function() {
      // 删除当前员工
      let delUri = $(this).attr("del_uri")
      // 修改删除表单的action地址
      $("#deleteEmpForm").attr("action", delUri).submit()
      return false
  })
  ```

### 4.4.6. SprignBoot错误处理

#### 4.4.6.1. SpringBoot默认的错误处理

- 默认效果

  - 返回一个默认的错误页面

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fvrlzn9ly4j30kb066q2z.jpg)
  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fvrnog2j70j30ia03qmx2.jpg)

  - 如果是其他客户端，默认响应一个json数据

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fvrnldm1t5j30c606kq2u.jpg)
  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fvrnqhmbrqj30m105bglm.jpg)

- 原理：参照`ErrorMvcAutoConfiguration`；错误处理的自动配置

- 给容器中添加了以下组件

    - `DefaultErrorAttributes`

    - `BasicErrorController`：处理默认/error请求

      ```java
      @Controller
      @RequestMapping("${server.error.path:${error.path:/error}}")
      public class BasicErrorController extends AbstractErrorController {
          
          // 产生html类型的数据；浏览器发送的请求来到这个方法处理
      	@RequestMapping(produces = "text/html")
      	public ModelAndView errorHtml(HttpServletRequest request,
      			HttpServletResponse response) {
      		HttpStatus status = getStatus(request);
      		Map<String, Object> model = Collections.unmodifiableMap(getErrorAttributes(
      				request, isIncludeStackTrace(request, MediaType.TEXT_HTML)));
      		response.setStatus(status.value());
              // 去哪个页面作为错误页面；包含页面地址和页面内容
      		ModelAndView modelAndView = resolveErrorView(request, response, status, model);
      		return (modelAndView != null) ? modelAndView : new ModelAndView("error", model);
      	}
      
          // 产生json数据；其他客户端来到这个方法处理
      	@RequestMapping
      	@ResponseBody
      	public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
      		Map<String, Object> body = getErrorAttributes(request,
      				isIncludeStackTrace(request, MediaType.ALL));
      		HttpStatus status = getStatus(request);
      		return new ResponseEntity<Map<String, Object>>(body, status);
      	}
      ```

    - `ErrorPageCustomizer`

      ```java
      	@Value("${error.path:/error}")
      	private String path = "/error"; // 系统出现错误以后来到error请求进行处理；web.xml
      ```

    - `DefaultErrorViewResolver`

      ```java
      	@Override
      	public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status,
      			Map<String, Object> model) {
      		ModelAndView modelAndView = resolve(String.valueOf(status), model);
      		if (modelAndView == null && SERIES_VIEWS.containsKey(status.series())) {
      			modelAndView = resolve(SERIES_VIEWS.get(status.series()), model);
      		}
      		return modelAndView;
      	}
      
      	private ModelAndView resolve(String viewName, Map<String, Object> model) {
              // 默认SpringBoot可以去找到一个页面 ? error/404
      		String errorViewName = "error/" + viewName;
              // 模版引擎可以解析这个页面地址就用模版引擎解析
      		TemplateAvailabilityProvider provider = this.templateAvailabilityProviders
      				.getProvider(errorViewName, this.applicationContext);
      		if (provider != null) {
                  // 模版引擎可以用的情况下返回到errorViewName指定的视图地址
      			return new ModelAndView(errorViewName, model);
      		}
              // 模版引擎不可用，就在静态资源文件夹下找errorViewName对应的页面 error/404.html
      		return resolveResource(errorViewName, model);
      	}
      ```

  - 步骤：

    - 一旦系统出现4xx或者5xx之类的错误；`ErrorPageCustomizer`就会生效（定制错误的响应规则）；就会来到/error请求；就会被`BasicErrorController`处理；
    - 响应页面；去哪个页面是由`DefaultErrorViewResolver`解析得到

    ```java
    	protected ModelAndView resolveErrorView(HttpServletRequest request,
    			HttpServletResponse response, HttpStatus status, Map<String, Object> model) {
            // 所有的ErrorViewResolver得到ModelAndView
    		for (ErrorViewResolver resolver : this.errorViewResolvers) {
    			ModelAndView modelAndView = resolver.resolveErrorView(request, status, model);
    			if (modelAndView != null) {
    				return modelAndView;
    			}
    		}
    		return null;
    	}
    ```

- 如何定制错误响应：

  - 如何定制错误页面

    - 有模版引擎：error/状态码.html 【将错误页面明明为：错误状态码.html，放在模版引擎文件夹里面的error文件夹下】，发生此状态码的错误就会来到对应的页面；可以使用4xx和5xx作为错误页面的文件名来匹配这种类型的所有错误，精确优先；

      - 页面能获取的信息

      ```ini
      timestamp:时间戳
      status:状态码
      error:错误提示
      exception:异常对象
      message:异常消息
      path:请求路径
      errors:JSR303数据校验的错误都在这里
      ```

    - 没有模版引擎（模版引擎找不到这个错误页面）：静态资源文件夹下找，不推荐；不能使用模版引擎的语法，也不能获取status等页面的信息

    - 以上都没有错误页面，就是默认来到SpringBoot默认的错误提示页面

  - 如何定制错误数据

    - 自定义异常处理&返回定制json数据

      ```java
      @RestControllerAdvice
      public class MyExceptionHandler {
      
          /**
           * 1. 没有自适应效果，浏览器和客户端返回的都是json
           *
           * @param e
           * @return
           * @author colg
           */
          @ExceptionHandler(UserNotExistException.class)
          public Dict handleException(Exception e) {
              Dict dict = Dict.create()
                              .set("code", "user.notexist")
                              .set("message", e.getMessage());
              return dict;
          }
      }
      ```

    - 自定义异常处理&转发到error进行自适应响应效果处理

      ```java
      /**
       * 异常处理器2
       *
       * @author colg
       */
      @ControllerAdvice
      public class MyExceptionHandler2 {
      
          /**
           * 2. 转发到/error进行自定义响应效果处理
           *
           * @param e
           * @return
           * @author colg
           */
          @ExceptionHandler(UserNotExistException.class)
          public String handleException(Exception e, HttpServletRequest request) {
              Dict dict = Dict.create()
                              .set("code", "user.notexist")
                              .set("message", e.getMessage());
              // 传入自己定义的错误状态码 4xx 5xx，否则就不会进入定制错误页面的解析流程
              request.setAttribute("javax.servlet.error.status_code", HttpStatus.BAD_REQUEST.value());
              // 转发到/error
              return "forward:/error";
          }
      }
      ```

    - 自定义异常处理&将定制数据携带出去

      - 出现错误以后，会来到/error请求，会被`BasicErrorController`处理，响应出去可以获取的数据是由`getErrorAttributes`得到的（是`AbstractErrorController`，`ErrorController`规定的方法）；

      -  编写一个`ErrorController`的实现类，或者编写`AbstractErrorController`的子类，放在容器中

      - 页面上能用的数据，或者是json返回能用的数据都是通过`errorAttributes.getErrorAttributes`得到

        - 自定义`ErrorAttributes`

          ```java
          /**
           * 给容器中加入自己定义的ErrorAttributes
           *
           * @author colg
           */
          @Component
          public class MyErrorAttributes extends DefaultErrorAttributes{
          
              @Override
              public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
                  Map<String, Object> map = super.getErrorAttributes(requestAttributes, includeStackTrace);
                  map.put("company", "colg");
                  return map;
              }
          }
          ```

        - 最终的效果：响应是自适应的，可以通过定制`ErrorAttributes`改变需要返回的内容

### 4.4.7. 配置嵌入式Servlet容器

SpringBoot默认使用Tomcat作为嵌入式的Servlet；

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fw20cv8cb5j30cb06odfs.jpg)

#### 4.4.7.1. 定制和修改Servlet容器的相关配置

- 配置方式：修改和server有关的配置`ServerProperties`，也是`EmbeddedServletContainerCustomizer`

  ```yaml
  # 通用的servlet容器设置 server: xxx
  server:
    port: 8081
    context-path: /servlet            # 项目访问路径
  # Tomcat的设置
    tomcat:
      uri-encoding: UTF-8
  ```

- 编码方式：编写一个`EmbeddedServletContainerCustomizer`，嵌入式的Servlet容器的定制器；来修改Servlet容器的配置

  ```java
  /**
   * 修改Servlet配置
   *
   * @author colg
   */
  @Configuration
  public class MyServerConfig extends WebMvcConfigurerAdapter{
  
      /**
       * 配置嵌入式的Servlet
       *
       * @return
       * @author colg
       */
      @Bean
      public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer() {
          return new EmbeddedServletContainerCustomizer() {
  
              /**
               * 定制嵌入式的Servlet容器相关的规则
               *
               * @param container
               */
              @Override
              public void customize(ConfigurableEmbeddedServletContainer container) {
                  container.setPort(8083);
              }
          };
      }
  }
  ```

#### 4.4.7.2. 注册Servlet三大组件（Servlet、Filter、Listener）

由于SpringBoot默认是以jar包的方式启动嵌入式的Servlet容器，没有web.xml文件。

注册三大组件用以下下方式：

- Servlet

  ```java
  /**
   * 标准Servlet
   *
   * @author colg
   */
  public class MyServlet extends HttpServlet{
  
      private static final long serialVersionUID = 1L;
  
      /**
       * 处理get请求
       *
       * @param req
       * @param resp
       * @throws ServletException
       * @throws IOException
       */
      @Override
      protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          doPost(req, resp);
      }
      
      /**
       * 处理post请求
       *
       * @param req
       * @param resp
       * @throws ServletException
       * @throws IOException
       */
      @Override
      protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          resp.getWriter().println("Hello MyServlet");
      }
  }
  ```

  ```java
      /**
       * 注册Servlet
       *
       * @return
       * @author colg
       */
      @Bean
      public ServletRegistrationBean servletRegistrationBean() {
          ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new MyServlet(), "/myServlet");
          return servletRegistrationBean;
      }
  ```

- Filter

  ```java
  /**
   * 标准Filter
   *
   * @author colg
   */
  @Slf4j
  public class MyFilter implements Filter {
  
      @Override
      public void init(FilterConfig filterConfig) throws ServletException {}
  
      @Override
      public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
          log.info("MyFilter 执行");
          // 放行
          chain.doFilter(request, response);
      }
  
      @Override
      public void destroy() {}
  
  }
  ```

  ```java
      /**
       * 注册Filter
       *
       * @return
       * @author colg
       */
      @Bean
      public FilterRegistrationBean filterRegistrationBean() {
          FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
          filterRegistrationBean.setFilter(new MyFilter());
          filterRegistrationBean.setUrlPatterns(Arrays.asList("/hello", "/myServlet"));
          return filterRegistrationBean;
      }
  ```

- Listener

  ```java
  /**
   * 标准Listener
   *
   * @author colg
   */
  @Slf4j
  public class MyListener implements ServletContextListener {
  
      @Override
      public void contextInitialized(ServletContextEvent sce) {
          log.info("contextInitialized... web应用启动");
      }
  
      @Override
      public void contextDestroyed(ServletContextEvent sce) {
          log.info("contextDestroyed... web项目销毁");
      }
  
  }
  ```

  ```java
      /**
       * 注册Listener
       *
       * @return
       * @author colg
       */
      @Bean
      public ServletListenerRegistrationBean<MyListener> servletListenerRegistrationBean() {
          ServletListenerRegistrationBean<MyListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<>(new MyListener());
          return servletListenerRegistrationBean;
      }
  ```

#### 4.4.7.3. 替换为其他嵌入式Servlet容器

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fw21lo1azxj30j2051dfz.jpg)

默认支持：

- Tomcat（默认使用）

  ```xml
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-web</artifactId>
              <!-- 排除Tomcat容器 -->
              <exclusions>
                  <exclusion>
                      <artifactId>spring-boot-starter-tomcat</artifactId>
                      <groupId>org.springframework.boot</groupId>
                  </exclusion>
              </exclusions>
          </dependency>
  ```

- Jetty

  ```xml
          <!-- 引入其他的Servlet容器：jetty -->
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-jetty</artifactId>
          </dependency>
  ```

- Undertow

  ```xml
          <!-- 引入其他的Servlet容器：Undertow -->
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-undertow</artifactId>
          </dependency>
  ```

#### 4.4.7.4. 嵌入式Servlet容器自动配置原理

`EmbeddedServletContainerAutoConfiguration`：嵌入式的Servlet容器自动配置

```java
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@ConditionalOnWebApplication
@Import(BeanPostProcessorsRegistrar.class)
public class EmbeddedServletContainerAutoConfiguration {
	/**
	 * Nested configuration if Tomcat is being used.
	 */
	@Configuration
    // 判断当前是否引入了Tomcat依赖
	@ConditionalOnClass({ Servlet.class, Tomcat.class })
    // 判断当前容器有没有用户自定义EmbeddedServletContainerFactory：嵌入式的Servlet容器工厂；作用：创建嵌入式的Servlet容器
	@ConditionalOnMissingBean(value = EmbeddedServletContainerFactory.class, search = SearchStrategy.CURRENT)
	public static class EmbeddedTomcat {

		@Bean
		public TomcatEmbeddedServletContainerFactory tomcatEmbeddedServletContainerFactory() {
			return new TomcatEmbeddedServletContainerFactory();
		}

	}
    
	/**
	 * Nested configuration if Jetty is being used.
	 */
	@Configuration
    // 判断当前是否引入了Jetty依赖
	@ConditionalOnClass({ Servlet.class, Server.class, Loader.class,
			WebAppContext.class })
	@ConditionalOnMissingBean(value = EmbeddedServletContainerFactory.class, search = SearchStrategy.CURRENT)
	public static class EmbeddedJetty {

		@Bean
		public JettyEmbeddedServletContainerFactory jettyEmbeddedServletContainerFactory() {
			return new JettyEmbeddedServletContainerFactory();
		}

	}

	/**
	 * Nested configuration if Undertow is being used.
	 */
	@Configuration
    // 判断当前是否引入了Undertow依赖
	@ConditionalOnClass({ Servlet.class, Undertow.class, SslClientAuthMode.class })
	@ConditionalOnMissingBean(value = EmbeddedServletContainerFactory.class, search = SearchStrategy.CURRENT)
	public static class EmbeddedUndertow {

		@Bean
		public UndertowEmbeddedServletContainerFactory undertowEmbeddedServletContainerFactory() {
			return new UndertowEmbeddedServletContainerFactory();
		}

	}
```

- `EmbeddedServletContainerFactory`：嵌入式Servlet容器工厂

  ```java
  public interface EmbeddedServletContainerFactory {
  
      // 获取嵌入式的Servlet容器
  	EmbeddedServletContainer getEmbeddedServletContainer(
  			ServletContextInitializer... initializers);
  
  }
  ```

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fw2203v77pj30iz036wei.jpg)

- EmbeddedServletContainer`：嵌入式的Servlet容器

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fw220se3zej30hq02k3yg.jpg)

- 以`TomcatEmbeddedServletContainerFactory`为例

  ```java
  	@Override
  	public EmbeddedServletContainer getEmbeddedServletContainer(
  			ServletContextInitializer... initializers) {
          // 创建一个Tomcat
  		Tomcat tomcat = new Tomcat();
          // 配置Tomcat的基本环节
  		File baseDir = (this.baseDirectory != null) ? this.baseDirectory
  				: createTempDir("tomcat");
  		tomcat.setBaseDir(baseDir.getAbsolutePath());
  		Connector connector = new Connector(this.protocol);
  		tomcat.getService().addConnector(connector);
  		customizeConnector(connector);
  		tomcat.setConnector(connector);
  		tomcat.getHost().setAutoDeploy(false);
  		configureEngine(tomcat.getEngine());
  		for (Connector additionalConnector : this.additionalTomcatConnectors) {
  			tomcat.getService().addConnector(additionalConnector);
  		}
  		prepareContext(tomcat.getHost(), initializers);
          // 将配置好的Tomcat传入进入，返回一个侵入式的Servlet容器，并且启动Tomcat容器
  		return getTomcatEmbeddedServletContainer(tomcat);
  	}
  ```

#### 4.4.7.5. 嵌入式Servlet容器启动原理

- SpringBoot应用启动run方法
- refreshContext(context)：SpringBoot刷新IOC容器（创建IOC容器对象，并初始化容器，创建容器中的每一个组件）
- refresh(context)：刷新刚才创建好的IOC容器
- onRefresh()：web容器重写了onRefresh方法
- webioc容器会创建嵌入式的Servlet容器
- ......

### 4.4.8. 配置外部servlet容器

- 嵌入式Servlet容器：应用打成可执行的jar
  - 优点：简单，便捷
  - 缺点：默认不支持jsp，优化定制比较复杂（使用定制器【ServerProperties、自定义EmbeddedServletContainerCustomizer】，自己编写嵌入式Servlet容器的创建工厂）

- 外置的Servlet容器：应用打成war包

  - 必须创建一个war项目(注意目录结构)

  - 将嵌入式的Tomcat指定为`provided`，不打包该依赖

    ```xml
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-tomcat</artifactId>
                <scope>provided</scope>
            </dependency>
    ```

  - 必须编写一个`SpringBootServletInitializer`的子类，并调用configure方法

    ```java
    /**
     * Servlet 初始化程序
     *
     * @author colg
     */
    public class ServletInitializer extends SpringBootServletInitializer {
    
        @Override
        protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
            // 传入SpringBoot应用的主程序
            return application.sources(SpringBoot09WebJspApplication.class);
        }
    
    }
    ```

  - 启动服务器就可以使用 http://localhost:8080/spring-boot-09-web-jsp

- 原理

  - jar包：执行SpringBoot主类的main方法，启动ioc容器，创建嵌入式的Servlet容器
  - war包：启动服务器，服务器启动SpringBoot应用`SpringBootServletInitializer`，启动ioc容器

## 5. Docker

## 6. SpringBoot与数据访问

### 6.1. JDBC

#### 6.1.1. 引入starter

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
```

#### 6.1.2. 配置application.yml

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3306/spring_boot?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false
    username: spring_boot
    password: 123456
```

```java
/**
 * Jdbc测试
 *
 * @author colg
 */
@Slf4j
public class JdbcTest extends SpringBoot10DataJdbcApplicationTests {

    @Autowired
    private DataSource dataSource;
    
    @Test
    public void testName() throws Exception {
        // driverClassName: class org.apache.tomcat.jdbc.pool.DataSource
        log.info("driverClassName: {}", dataSource.getClass());
        
        Connection connection = dataSource.getConnection();
        // ProxyConnection[PooledConnection[com.mysql.jdbc.JDBC4Connection@6d8796c1]]
        log.info("connection: {}", connection.toString());
        connection.close();
    }
}
```

```ini
driverClassName: class org.apache.tomcat.jdbc.pool.DataSource
connection: ProxyConnection[PooledConnection[com.mysql.jdbc.JDBC4Connection@7c5d1d25]]
```

- 效果
  - 默认是用`org.apache.tomcat.jdbc.pool.DataSource`作为数据源；
  - 数据源的相关配置都在`DataSourceProperties`里面。

- 自动配置原理

  - `org.springframework.boot.autoconfigure.jdbc`包

  - 参考`DataSourceConfiguration`，根据配置创建数据源，默认使用Tomcat连接池；可以使用`spring.datasource.type`指定自定义的数据源类型；

  - SpringBoot默认可以支持：

    ```ini
    org.apache.tomcat.jdbc.pool.DataSource
    com.zaxxer.hikari.HikariDataSource
    org.apache.commons.dbcp.BasicDataSource
    org.apache.commons.dbcp2.BasicDataSource
    ```

- 自定义数据源类型

  ```java
  	/**
  	 * Generic DataSource configuration.
  	 */
  	@ConditionalOnMissingBean(DataSource.class)
  	@ConditionalOnProperty(name = "spring.datasource.type")
  	static class Generic {
          // 使用DataSourceBuilder创建数据源，利用反射创建响应type的数据源，并且绑定相关属性
  		@Bean
  		public DataSource dataSource(DataSourceProperties properties) {
  			return properties.initializeDataSourceBuilder().build();
  		}
  
  	}
  ```

- `DataSourceInitializer`: `ApplicationListener`，作用：

  - `runSchemaScripts()`:  运行建表语句
  - `runDataScripts()`: 运行插入数据的sql语句

- 默认只需要将文件名修改为：

  ```ini
  schema-*.sql, data-*.sql
  默认规则: schema.sql, schema-all.sql
  可以使用:
      schema:
        - classpath:schema-department.sql       # 指定sql文件的位置
        - classpath:schema-employee.sql
  ```

#### 6.1.3. 测试

```java
/**
 * HelloController
 *
 * @author colg
 */
@RestController
public class HelloController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/add")
    public ResultVo add() {
        String sql = "INSERT INTO department(departmentName) VALUES('技术部')";
        int update = jdbcTemplate.update(sql);
        return success(update);
    }

    @GetMapping("/query")
    public ResultVo map() {
        String sql = "SELECT * FROM department";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        if (CollUtil.isEmpty(list)) {
            return fail(1, "数据库里没有数据！");
        }
        return success(list);
    }
}
```

### 6.2. 整合Druid数据源

#### 6.2.1. 导入数据源

```xml
        <!-- 引入 druid 数据源 -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.1.12</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <optional>true</optional>
        </dependency>
```



#### 6.2.2. 配置Druid的监控

```java
/**
 * 数据源配置
 *
 * @author colg
 */
@Configuration
public class DruidConfig {

    /**
     * 配置 Druid数据源
     *
     * @return
     * @author colg
     */
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    /*
     * colg  [配置Druid的监控]
     *  1. 配置一个管理后台的 Servlet
     *  2. 配置一个监控的 Filter
     */

    /**
     * 1. 配置管理后台的 Servlet
     *
     * @return
     * @author colg
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("loginUsername", "admin");
        initParameters.put("loginPassword", "123456");
        bean.setInitParameters(initParameters);
        return bean;
    }

    /**
     * 2. 配置一个web监控的 Filter
     *
     * @return
     * @author colg
     */
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("exclusions", "*.js,*.css,/druid/*");
        bean.setInitParameters(initParameters);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}
```

```yaml
    type: com.alibaba.druid.pool.DruidDataSource    # 指定数据源
# 数据源其他配置
    validation-query: SELECT 1
    initial-size: 2
    min-idle: 2
    max-active: 8
    max-wait: 60000
# 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
    filters: stat, wall, log4j
```



#### 6.2.3. 效果

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwric22awhj30uq09f0sr.jpg)

### 6.3. 整合MyBatis

#### 6.3.1. 导入数据源

```xml
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>1.3.2</version>
        </dependency>
```

```java
/**
 * Sprign Boot 启动类
 * 
 * <pre>
 * `@MapperScan`: 批量扫描操作数据库的mapper
 * </pre>
 *
 * @author colg
 */
@MapperScan("cn.colg.mapper")
@SpringBootApplication
public class SpringBoot11DataMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot11DataMybatisApplication.class, args);
    }
}

```

#### 6.3.2. 注解版

```java
/**
 * DepartmentMapper；注解版
 * 
 * <pre>
 * `@Mapper`: 单独指定这是一个操作数据库的mapper；可在启动类批量扫描
 * </pre>
 *
 * @author colg
 */
public interface DepartmentMapper {
    
    @Select("SELECT * FROM department")
    List<Department> query();

    @Select("SELECT d.* FROM department d WHERE d.id = #{id}")
    Department getDeptById(Integer id);
    
    /**
     * 插入数据，返回主键
     *
     * @param department
     * @return
     * @author colg
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO department(departmentName) VALUES(#{departmentName})")
    int insertDept(Department department);
}
```

- MyBatis全局配置

  ```java
  /**
   * MyBatis 注解版全局配置
   *
   * @author colg
   */
  @Configuration
  public class MyBatisConfig {
  
      @Bean
      public ConfigurationCustomizer configurationCustomizer() {
          return new ConfigurationCustomizer() {
  
              @Override
              public void customize(org.apache.ibatis.session.Configuration configuration) {
                  // 是否开启自动驼峰命名规则
                  configuration.setMapUnderscoreToCamelCase(true);
                  // 延迟加载的全局开关。
                  configuration.setLazyLoadingEnabled(true);
                  // 当开启时，任何方法的调用都会加载该对象的所有属性。
                  configuration.setAggressiveLazyLoading(true);
              }
          };
      }
  }
  ```

#### 6.3.3. 配置版本

```yaml
mybatis:
  config-location: classpath:mybatis/mybatis.cfg.xml  # mybatis 配置文件所在路径
  mapper-locations:
    - classpath:mybatis/mapper/**/*.xml               # mapper 映射文件
```

## 7. SpringBoot启动配置原理

- 几个重要的事件回调机制

  ```ini
  ApplicationContextInitializer
  SpringApplicationRunListener
  ApplicationRunner
  CommandLineRunner
  ```

- 启动流程

  - 创建`SpringApplication`对象, `initialize(sources)`

    ```java
    	private void initialize(Object[] sources) {
            // 保存主配置
    		if (sources != null && sources.length > 0) {
    			this.sources.addAll(Arrays.asList(sources));
    		}
            // 判断当前是否是一个web应用
    		this.webEnvironment = deduceWebEnvironment();
            // 从类路径下找到"META-INF/spring.factories"配置的所有ApplicationContextInitializer; 然后保存起来
    		setInitializers((Collection) getSpringFactoriesInstances(
    				ApplicationContextInitializer.class));
            // 从类路径下找到"META-INF/spring.factories"配置的所有ApplicationListener
    		setListeners((Collection) getSpringFactoriesInstances(ApplicationListener.class));
            // 从多个配置类中找到有main方法的配置类
    		this.mainApplicationClass = deduceMainApplicationClass();
    	}
    ```

  - 运行`run()`方法

    ```java
    	public ConfigurableApplicationContext run(String... args) {
    		StopWatch stopWatch = new StopWatch();
    		stopWatch.start();
    		ConfigurableApplicationContext context = null;
    		FailureAnalyzers analyzers = null;
    		configureHeadlessProperty();
            // 获取SpringApplicationRunListeners，从类路径下"META-INF/spring.factories"
    		SpringApplicationRunListeners listeners = getRunListeners(args);
            // 回调所有的SpringApplicationRunListener.starting()方法
    		listeners.starting();
    		try {
                // 封装命令行参数
    			ApplicationArguments applicationArguments = new DefaultApplicationArguments(
    					args);
                // 准备环境
    			ConfigurableEnvironment environment = prepareEnvironment(listeners,
    					applicationArguments);
                // 创建环境完成后回调SpringApplicationRunListener.environmentPrepared()方法，表示环境准备完成
                // 打印Banner
    			Banner printedBanner = printBanner(environment);
                // 创建ApplicationContext，决定创建web的ioc还是普通的ioc
    			context = createApplicationContext();
    			analyzers = new FailureAnalyzers(context);
                // 准备上下文环境；将environment保存到ioc中；而且applyInitializers(context)
                // applyInitializers(): 回调之前保存的所有的ApplicationContextInitializer.initialize()方法
    			prepareContext(context, environment, listeners, applicationArguments,
    					printedBanner);
                // prepareContext运行完成以后回调所有的SpringApplicationRunListener.contextPrepared()
                
                // 刷新容器；ioc容器初始化；如果是web应用还会创建嵌入式的Tomcat；Spring注解版
    			refreshContext(context);
                // 从ioc容器中获取所有的ApplicationRunner和CommandLineRunner进行回调
                // ApplicationRunner先回调
    			afterRefresh(context, applicationArguments);
                // 所有的SpringApplicationRunListener回调finished()方法
    			listeners.finished(context, null);
    			stopWatch.stop();
    			if (this.logStartupInfo) {
    				new StartupInfoLogger(this.mainApplicationClass)
    						.logStarted(getApplicationLog(), stopWatch);
    			}
                // 整个SpringBoot应用启动完成以后返回启动的ioc容器
    			return context;
    		}
    		catch (Throwable ex) {
    			handleRunFailure(context, listeners, analyzers, ex);
    			throw new IllegalStateException(ex);
    		}
    	}
    ```


## 8. 自定义starter

## 9. 更多SpringBoot整合示例