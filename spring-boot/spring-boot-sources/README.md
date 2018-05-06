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

```
一个功能：浏览器发送hello请求，服务器接收请求并处理，响应Hello World字符串；
```

#### 1、创建一个Maven工程

#### 2、导入Spring Boot相关的依赖

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fr26wrins4j30ks0bkaab.jpg)

#### 3、编写一个主程序；启动Spring Boot应用

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fr26yr9330j30kt0970st.jpg)

#### 4、编写相关的Controler、Service

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fr270jcy4zj30gk09maa3.jpg)

#### 5、运行主程序测试

#### 6、简化部署

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fr272w2qb2j30hx04s3yf.jpg)

### 5、Hello World探究

#### 1、POM文件

##### 1、父项目

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fr279ofwqhj30p207yt8t.jpg)

##### 2、启动器

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fr27afjpzhj30ds027mwy.jpg)

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

```
server.port=8001
```

yml:

```
server:
  port: 8001
```

xml:

```
<server>
	<port>8001</prot>
</server>
```

### 2、YAML语法

### 3、配置文件注入

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