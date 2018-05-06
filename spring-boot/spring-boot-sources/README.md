# spring-boot

## 一、Spring Boot 入门

### 1、Sping Boot简介

- 简化Spring应用开发的一个框架；
- 整个Spring技术站的一个打集合；
- J2EE的一站式解决方案。

### 2、微服务

- 微服务；架构风格（服务微化）
  - 一个应用应该是一组小型服务；可以通过HTTP的方式进行互通。
- 单体应用
  - ALL IN ONE
- 微服务
  - 每一个功能元素最终都是一个可以独立替换和独立升级的软件单元。

### 3、环境准备

- jdk：java version "1.8.0_40"
- maven：Apache Maven 3.5.0
- ide：spring-tool-suite-3.7.3.RELEASE
- sprign-boot：1.5.12.RELEASE

### 4、Spring Boot HelloWorld

```java
一个功能：浏览器发送hello请求，服务器接收请求并处理，响应Hello World字符串；
```

#### 1、创建一个Maven工程

#### 2、导入Spring Boot相关的依赖

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
		<version>1.5.12.RELEASE</version>
	</parent>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
	</dependencies>

</project>
```

#### 3、编写一个主程序；启动Spring Boot应用

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

#### 4、编写相关的Controler、Service

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

#### 5、运行主程序测试

#### 6、简化部署

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



### 5、Hello World探究

#### 1、POM文件

##### 1、父项目

```xml
	<!-- 
		它的父项目是：
			<parent>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-dependencies</artifactId>
				<version>1.5.12.RELEASE</version>
				<relativePath>../../spring-boot-dependencies</relativePath>
			</parent>
		Spring Boot应用里面的所有依赖版本；以后导入依赖默认是不需要写版本；（没有在dependencies里面管理的依赖自然需要声明版本号）
	 -->
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>1.5.12.RELEASE</version>
	</parent>
```

##### 2、启动器

```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
```

- spring-boot-starter-web
  - spring-boot-starter：spring-boot场景启动器；帮我们导入了web模块正常运行所依赖的组件；
- Spring Boot将所有功能场景都抽取出来，做成一个个的starters（启动器），只要在项目里面引入这些starter相关场景的所有依赖都会导入进来，要用什么功能就导入什么场景的启动器

### 6、使用Spring Initialzer快速创建Spring Boot项目

#### 1、STS使用Spring Starter Project快速创建项目

默认生成的spring boot项目：

- 主程序已经生成好了，测试基础类已经生成好了
- resources文件夹目录结构
  - static：保存所有的静态资源；js、css、images；
  - templates：保存所有的模版页面；（spring boot默认jar包使用嵌入式的tomcat，默认不支持jsp）；可以使用模版引擎（freemarker、thymeleaf）；
  - application.properties：spring boot应用的配置文件，可以修改一些默认设置。

## 二、配置文件

### 1、配置文件

SpringBoot使用一个全局的配置文件，配置文件名是固定的：

- application.properties
- application.yml

配置文件的作用：修改SpringBoot自动配置的默认值；SpringBoot在底层都给我们自动配置好；
properties：

```properties
server.port=8001
```

yml:

```yaml
server:
  port: 8001
```

xml:

```xml
<server>
	<port>8001</prot>
</server>
```

### 2、YAML语法

#### 1、基本语法

k(空格)v： 表示一对键值对（空格必须有）；

以空格的缩进来控制层级关系；只要在左对齐的一列数据，都是同一个层级的，属性和值也是大小写敏感；

#### 2、值的写法

##### 1、字面量、数字、字符串、布尔

k:v

​	字面值直接写；

​	字符串默认不用加上单引号或者双引号；

​	""：	双引号；不会转义字符串里面的特殊字符；特殊字符会作为本身想表达的意思

​		name: "Jack \n Rose"；输出：Jack 换行 Rose

​	''：	单引号；会转义特殊字符，特殊字符最终知识一个普通的字符串数据

​		name: 'Jack \n Rose'；输出：Jack \n Rose

##### 2、对象、Map

k: v

​	在下一行写对象的属性和值的关系；注意缩进

​	对象还是k: v的方式

```yaml
friends:
  lastName: Jack
  age: 20
```

​	行内写法：

```yaml
friedns: {lastName: Jack, age: 20}
```

##### 3、数组、List、Set

用-值来表示数组中的一个元素

```yaml
pets:
  - cat
  - dog
  - pig
```

行内写法：

```yaml
pets: [cat, dog, pig]
```

### 3、配置文件注入

配置文件：

​	普通写法：

```yaml
person:
  last-name: hello
  age: 18
  boss: false
  birth:
    2017/12/12
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

​	行内写法：

```yaml
server:
  port: 8001
  
person:
  last-name: hello
  age: 18
  boss: false
  birth: 2017/12/12
  maps: {k1: v1, k2: v2}
  lists: [Jack, Rose]
  dog:
    name: 小狗
    age: 3
```

javaBean：

```java
package cn.clog.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Person 实体
 * 
 * <pre>
 * 将配置文件中配置的每一个属性的值，映射到这个组件中
 *  `@ConfigurationProperties`：  告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定
 *      `(prefix = "person")`：  配置文件中哪个与下面的所有属性进行一一映射
 *      
 *  只有这个组件是容器中的组件，才能使用容器提供的`@ConfigurationProperties`组件
 * </pre>
 *
 * @author colg
 */
@ConfigurationProperties(prefix = "person")
@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
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

spring-boot-configuration-processor依赖：

```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-configuration-processor</artifactId>
			<optional>true</optional>
		</dependency>
```

### 4、配置文件占位符

#### 5、注册环境Profile

#### 6、配置文件加载位置

#### 7、外部配置加载顺序

#### 8、自动配置原理

## 三、日志

## 四、Web开发

## 五、Docker

## 六、SpringBoot与数据访问

## 七、自定义starter

## 八、更多SpringBoot整合示例