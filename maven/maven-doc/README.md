# Maven - 项目管理工具

## 1. Maven介绍

### 1.1. 开发中遇到的问题

- 都是同样的代码，为什么在我的机器上可以编译执行，而在他的机器上就不行？
- 为什么在我的机器上可以正常打包，而配置管理员却打不出来?
- 项目组加入了新的人员，我要给他说明编译环境如何设置，但是让我挠头的是，有些细节我也记不清楚了。
- 我的项目依赖一些jar包，我应该把他们放哪里？放源码库里？
- 这是我开发的第二个项目，还是需要上面的那些jar包，再把它们复制到我当前项目的svn库里吧
- 现在是第三次，再复制一次吧    ----- 这样真的好吗？
- 我写了一个数据库相关的通用类，并且推荐给了其他项目组，现在已经有五个项目组在使用它了，今天我发现了一个bug，并修正了它，我会把jar包通过邮件发给其他项目组 --- 这不是一个好的分发机制，太多的环节可能导致出现bug
-  项目进入测试阶段，每天都要向测试服务器部署一版。每次都手动部署，太麻烦了。

### 1.2. 什么是maven？

- Maven是基于POM（工程对象模型），通过一小段描述来对项目的代码、报告、文件进管理的工具。
- Maven是一个跨平台的项目管理工具，它是使用java开发的，它要依赖于jdk1.6及以上
- Maven主要有两大功能：管理依赖、项目构建。
  - 依赖指的就是jar包

### 1.3. 什么是构建

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwa3mx3mb2j30hy0engmk.jpg)

### 1.4. 项目构建的方式

- Eclipse
  - 使用eclipse进行项目构建，相对来说，步骤比较零散，不好操作
- Ant
  - 它是一个专门的项目构建工具，它可以通过一些配置来完成项目构建，这些配置要明确告诉ant，源码包在哪？目标class文件应该存放在哪？资源文件应该在哪？
- **Maven**
  - 它是一个项目管理工具，也是一个项目构建工具，通过使用maven，可以对项目进行快速简单的构建，不需要告诉maven很多信息，但是需要按照maven的规范去进行代码的开发。也就是说**maven是有约束的**

## 2. Maven安装配置

### 2.1. 下载maven

官方网站：http://maven.apache.org/

Maven是使用java开发的，需要安装jdk1.6以上，推荐使用1.8

### 2.2. 安装maven

- 安装jdk1.6以上

- 将maven下载的压缩包进行解压

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwa3t64muij30gc05vaa3.jpg)

- 配置maven的环境变量PATH

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwa3tl6ffvj30af05va9x.jpg)

- 测试maven是否安装成功，在系统命令中执行命令：`mvn -v`

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwa3ug4w48j30k3037aa0.jpg)

### 2.3. 配置maven

#### 2.3.1. 全局配置

在maven安装目录的conf文件夹里有一个`setting.xml`文件，这个就是maven的全局配置文件

该文件中的`localRepository`配置了maven本地仓库的地址

默认在系统的用户目录下的`${user.home}/.m2/repository`

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwa3zg6gzbj30kv027745.jpg)

#### 2.3.2. 用户配置

- 用户配置文件的地址：~/.m2/settings.xml，该文件默认是没有，需要将全局配置文件拷贝一份到该目录下。

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwa43f32d0j30h1034jr9.jpg)

- 重新指定本地仓库地址，如果不指定，则默认是~/.m2/repository目录，如果用户配置文件不存在，则使用全局配置文件的配置。

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwa43qp9r1j30jg03xt8m.jpg)

## 3. Maven创建项目

### 3.1. Maven工程结构

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwa497tnajj30ab0513ye.jpg)

### 3.2. 创建maven-01-hello工程

#### 3.2.1. 创建maven-01-hello工程

![1539676590677](C:\Users\BLACKC~1\AppData\Local\Temp\1539676590677.png)

#### 3.2.2. 创建HelloMaven.java

```java
package cn.colg;

/**
 * HelloMaven
 *
 * @author colg
 */
public class HelloMaven {

    public String sayHello(String name) {
        return "hello " + name;
    }
}
```

#### 3.2.3. 编辑pom.xml文件

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <!-- 版本：4.0.0 -->
    <modelVersion>4.0.0</modelVersion>
    <!-- 组织名称：暂时使用 组织名称+项目项目名称 -->
    <!-- 组织名称：实际名称 按照访问路径规范设置，通常以功能作为名称 -->
    <groupId>cn.colg</groupId>
    <!-- 项目名称 -->
    <artifactId>maven-01-hello</artifactId>
    <!-- 当前版本号：同一个项目开发过程中可以发布多个版本 -->
    <!-- 当前版本号：每个工程发布后可以发布多个版本，依赖时调用不同的版本，使用不同的版本号 -->
    <version>0.0.1-SNAPSHOT</version>
    <name>maven-01-hello :: maven 初识</name>
    
    <!-- 依赖关系 -->
    <dependencies>
        <!-- 依赖设置 -->
        <dependency>
            <!-- 依赖组织名称 -->
            <groupId>junit</groupId>
            <!-- 依赖项目名称 -->
            <artifactId>junit</artifactId>
            <!-- 依赖版本号 -->
            <version>4.12</version>
            <!-- 依赖范围：test包下依赖该设置 -->
            <scope>test</scope>
        </dependency>
    </dependencies>

</project>
```

#### 3.2.4. 创建HelloMavenTest.java

```java
package cn.colg;

import org.junit.Assert;
import org.junit.Test;

/**
 * HelloMaven 测试
 *
 * @author colg
 */
public class HelloMavenTest {

    /**
     * Test method for {@link cn.colg.HelloMaven#sayHello(java.lang.String)}.
     */
    @Test
    public void testSayHello() {
        HelloMaven helloMaven = new HelloMaven();
        String result = helloMaven.sayHello("maven");
        Assert.assertEquals("hello maven", result);
    }

}
```

### 3.3. Maven命令

- Maven的命令要在pom.xml所在的目录中去执行

  ```ini
  mvn compile: 编译命令
  mvn clean: 清除命令，清除异己编译好的class文件，具体清除的是target目录中的文件
  mvn test: 测试命令，该命令将test目录中的源码进行编译
  mvn package: 打包命令
  mvn install: 安装命令，会将打好的包，安装到本地仓库
  mvn deploy: 部署命令，会将打好的包，安装到私服，需要配置pom文件
  mvn clean compile: 组合命令，先清空再编译
  mvn clean test: 组合命令，先执行clean，再执行test，通常应用于测试环节
  ```

  - `mvn clean package`：组合命令，先执行clean，再执行package，将项目打包，通常应用于发布前
    - 执行过程
      - 清除 --- 清空环境
      - 编译 --- 编译源码
      - 测试 --- 测试源码
      - 打包 --- 将编译的非测试类打包
  - `mvn clean install`：组合命令，先执行clean，再执行install，将项目打包安装，通常应用于发布前
    - 执行过程
      - 清除 --- 清空环境
      - 编译 --- 编译源码
      - 测试 --- 测试源码
      - 打包 --- 将编译的非测试类打包
      - 安装 --- 将打好的包安装到资源车库中
  - `mvn clean install -Dmaven.test.skip=true`:组合命令，先执行clean，再执行install，并跳过test

## 4. Eclipse 使用Maven

### 4.1. 配置Maven

#### 4.1.1. 设置Maven安装路径

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwa4wmtcyyj30l00dimxe.jpg)

#### 4.1.2. 设置Maven用户配置

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwa4x70zbdj30mv0e174i.jpg)

### 4.2. 创建maven-02-seed工程：通过骨架

- 创建maven工程

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwa5f28wdpj30j10evjrf.jpg)

- Next

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwa50j5rmmj30hi0epmxd.jpg)

- 设置工程信息

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwa5hiyewaj30im0eqt8q.jpg)

- Finish，自带`junit`

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwa5i4ozuxj307h04da9x.jpg)

### 4.3. 创建maven-03-simple工程：简单工程

- 选择简单工程，跳过骨架

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwa5n016upj30iq0er74b.jpg)

- Next

- 设置工程信息

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwa5nynziyj30is0epq2z.jpg)

- Finish

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwa5pi7disj308a05p746.jpg)

- 编辑pom.xml

  ```xml
  <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
      <modelVersion>4.0.0</modelVersion>
      <groupId>cn.colg</groupId>
      <artifactId>maven-03-simple</artifactId>
      <version>0.0.1-SNAPSHOT</version>
      <name>maven-03-simple :: 简单工程，跳过骨架</name>
  
      <dependencies>
          <dependency>
              <groupId>junit</groupId>
              <artifactId>junit</artifactId>
              <version>4.12</version>
              <scope>test</scope>
          </dependency>
      </dependencies>
  </project>
  ```

## 5. Maven核心概念

### 5.1. 坐标

- 什么是坐标？
  - 在平面几何中坐标（x、y）可以标识平面中唯一的一点。在maven中坐标就是为了定位一个唯一确定的jar包
  - Maven世界拥有大量构建，我们需要找一个用来唯一标识一个构建的统一规范。拥有了统一规范，就可以把查找工作交给机器
- **Maven坐标主要组成**
  - **`groupId`：定义当前Maven组织名称**
  - **`artifactId`：定义实际项目名称**
  - **`version`：定义当前项目的当前版本**

### 5.2. 依赖管理

#### 5.2.1. 依赖范围

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwa5xtzb5uj30fp05swgi.jpg)

- 依赖范围`scope`用来控制依赖、编译、测试、运行的classpath的关系，主要的依赖范围如下：
  - `compile`：默认编译依赖范围。对于编译、测试、运行三种classpath都有效
  - `test`：测试依赖范围。只对于测试classpath有效
  - `provided`：已提供依赖范围。对于编译、测试的classpath有效，但对于运行无效。因为容器已经提供，例如`servlet-api`
  - `runtime`：运行时提供。例如`jdbc`驱动

#### 5.2.2. 依赖传递

##### 5.2.2.1. 创建maven-04-simple工程

- 依赖关系
- `A: maven-02-seed`, `B: maven-03-simple`, `C: maven-04-simple`
  - B工程依赖A工程，C工程依赖B工程，那么B工程是C工程的直接依赖，A工程是C工程的间接依赖

##### 5.2.2.2. 分析第一直接依赖和第二直接依赖

- C --> B：第一直接依赖
- B --> A：第二直接依赖
- C --> A：间接依赖

##### 5.2.2.3. 依赖范围传递

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwa6d3j0s0j30g605kjsl.jpg)

- 依赖范围传递解析
  - 列：第一直接依赖范围
  - 行：第二直接依赖范围
  - 交叉单元格：依赖传递范围
- 总结
  - 当第二直接依赖的范围是`compile`时，传递性依赖的范围与第一直接依赖的范围一致
  - 当第二直接依赖的范围是`test`时，依赖不会得以传递
  - 当第二直接依赖的范围是`provided`时，只传递第一直接依赖范围也为`provided`的依赖，且传递性依赖的范围同样为`provided`
  - 当第二直接依赖的范围是`runtime`时，传递性依赖的范围与第一直接依赖的范围一致，但`compile`例外，此时传递的依赖范围为`runtime`

#### 5.2.3. 依赖冲突

- maven中存在两种冲突方式
  - 跨pom文件的冲突：就近使用原则
  - 同一个pom文件的冲突：流式读取原则，从上往下，下面的覆盖上面的

#### 5.2.4. 可选依赖

- `optional`：标识该依赖是否可选，默认是false。如果为true，则标识该依赖不会传递下去，如果为false，则会传递下去

  ```xml
          <!-- maven-03-simple: junit 4.12 版本 -->
          <dependency>
              <groupId>junit</groupId>
              <artifactId>junit</artifactId>
              <version>4.12</version>
              <scope>compile</scope>
              <!-- 默认为 false，会传递下去 -->
              <optional>true</optional>
          </dependency>
  ```

#### 5.2.5. 排除依赖

- `exclusions`：排除依赖

  ```xml
          <!-- maven-03-simple: junit 4.12 版本 -->
          <dependency>
              <groupId>junit</groupId>
              <artifactId>junit</artifactId>
              <version>4.12</version>
              <scope>compile</scope>
              <!-- 默认为 false，会传递下去 -->
              <optional>false</optional>
              <!-- 排除 hamcrest-core 依赖 -->
              <exclusions>
                  <exclusion>
                      <artifactId>hamcrest-core</artifactId>
                      <groupId>org.hamcrest</groupId>
                  </exclusion>
              </exclusions>
          </dependency>
  ```

#### 5.2.6. 生命周期

- Maven有3个生命周期：clean、default、site
- 生命周期可以理解为**项目构建的步骤**集合
- 生命周期是由多个阶段组成。每个阶段都是一个完整的功能，比如mvn clean中的clean就是一个阶段

##### 5.2.6.1. clean生命周期

```ini
pre-clean 	:执行一些需要在clean之前完成的工作 
clean 		:移除所有上一次构建生成的文件 
post-clean 	:执行一些需要在clean之后立刻完成的工作 

mvn clean命令，等同于 mvn pre-clean clean。
只要执行后面的命令，那么前面的命令都会执行，不需要再重新去输入命令。
有clean生命周期，在生命周期又有clean阶段。
```

![1539681988071](C:\Users\BLACKC~1\AppData\Local\Temp\1539681988071.png)

##### 5.2.6.2. default生命周期

```ini
validate 
generate-sources 
process-sources 
generate-resources 
process-resources 		:复制并处理资源文件，至目标目录，准备打包。 
compile 				:!!!编译项目的源代码。 
process-classes 
generate-test-sources 
process-test-sources 
generate-test-resources 
process-test-resources 	:复制并处理资源文件，至目标测试目录。 
test-compile 			:编译测试源代码。 
process-test-classes 
test 					:!!!使用合适的单元测试框架运行测试。这些测试代码不会被打包或部署。 
prepare-package 
package 				:!!!接收编译好的代码，打包成可发布的格式，如 JAR 。 
pre-integration-test 
integration-test 
post-integration-test 
verify 
install 				:!!!将包安装至本地仓库，以让其它项目依赖。 
deploy 					:!!!将最终的包复制到远程的仓库，以让其它开发人员与项目共享。 

在maven中，只要在同一个生命周期，执行后面的阶段，那么前面的阶段也会被执行，而且不需要额外去输入前面的阶段，这样大大减轻了程序员的工作。
```

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwa73am7q7j313c0ncn0p.jpg)

##### 5.2.6.3. site生命周期

```ini
pre-site 	:执行一些需要在生成站点文档之前完成的工作 
site 		:生成项目的站点文档 
post-site 	:执行一些需要在生成站点文档之后完成的工作，并且为部署做准备 
site-deploy :将生成的站点文档部署到特定的服务器上 
```

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwa79r0rfij30ro0kf40g.jpg)

### 5.3. 插件

- `plugin`：每个插件都能实现一个阶段的功能。Maven的核心是生命周期，但是生命周期相当于主要指定了maven命令执行的流程顺序，而没有真正实现流程的功能。功能是有插件来实现的。比如：`compile`就是一个插件实现的功能

#### 5.3.1. 编译插件

```xml
    <build>
        <plugins>
            <!-- 编译插件，指定编译用的jdk版本 -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                    <encoding>UTF-8</encoding>
                </configuration>
            </plugin>
        </plugins>
    </build>
```

#### 5.3.2. Tomcat插件

##### 5.3.2.1. 创建maven的web工程

- 创建web工程

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwa7gm5vd0j30is0f0q2z.jpg)

- 创建`webapp/index.jsp`页面

  ```jsp
  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Insert title here</title>
  </head>
  <body>
      <h1>maven web 工程 - index页面</h1>
  </body>
  </html>
  ```

##### 5.3.2.2. 使用tomcat插件运行web工程

```xml
    <build>
        <plugins>
            <!-- 配置插件：这是tomcat7的插件配置 -->
            <plugin>
                <groupId>org.apache.tomcat.maven</groupId>
                <artifactId>tomcat7-maven-plugin</artifactId>
                <configuration>
                    <port>8080</port>
                    <path>/</path>
                </configuration>
            </plugin>
        </plugins>
    </build>
```

- 使用`tomcat:run`命令启动web工程

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwa7lum71vj30kd0bw0ss.jpg)

- 访问：http://localhost:8080/

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwa7m98xd9j30cr03sa9y.jpg)

### 5.4. 继承

#### 5.4.1. 父工程

- 父工程的打包方式必须为`pom`方式

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwa7oiw0afj30in0f5q2z.jpg)

#### 5.4.2. 子工程

- 创建子工程有两种方式

  - 创建一个新的工程为子工程
  - 修改老的工程为子工程

- 创建一个新的工程为子工程，指定父工程的坐标

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwa7s2a7uoj30hd0eqaa4.jpg)

- 子工程的`pom`

  ```xml
  <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
      <modelVersion>4.0.0</modelVersion>
      <parent>
          <groupId>cn.colg</groupId>
          <artifactId>maven-06-parent</artifactId>
          <version>0.0.1-SNAPSHOT</version>
      </parent>
      
      <artifactId>maven-07-child</artifactId>
      <name>maven-07-child :: 子工程</name>
  </project>
  ```

#### 5.4.3. 父工程统一依赖jar包

- 在父工程队jar包进行依赖，子工程中都会继承此依赖

  ```xml
      <!-- 子工程会继承此依赖 -->
      <dependencies>
          <dependency>
              <groupId>junit</groupId>
              <artifactId>junit</artifactId>
              <version>4.12</version>
              <scope>test</scope>
          </dependency>
      </dependencies>
  ```

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwa7ukhfvmj30f207nglp.jpg)

#### 5.4.4. 父工程统一管理版本号

- `dependencyManagement`：管理依赖的版本

- 父工程的pom

  ```xml
  	<!-- 父工程统一管理版本号，标签管理的依赖，其实没有真正依赖，它只是管理依赖的版本 -->
      <dependencyManagement>
          <dependencies>
              <dependency>
                  <groupId>junit</groupId>
                  <artifactId>junit</artifactId>
                  <version>4.12</version>
                  <scope>test</scope>
              </dependency>
          </dependencies>
      </dependencyManagement>
  ```

- 子工程的pom

  ```xml
  	<!-- 在子工程中使用父工程管理的版本号，此时不需要指定version -->
      <dependencies>
          <dependency>
              <groupId>junit</groupId>
              <artifactId>junit</artifactId>
          </dependency>
      </dependencies>
  ```

#### 5.4.5. 父工程抽取版本号

- `properties`：可以指定任意版本

  ```xml
  	<!-- properties中的子标签可以任意指定 -->
      <properties>
          <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
          <junit.version>4.12</junit.version>
      </properties>
      
  	<!-- 父工程统一管理版本号，标签管理的依赖，其实没有真正依赖，它只是管理依赖的版本 -->
      <dependencyManagement>
          <dependencies>
              <dependency>
                  <groupId>junit</groupId>
                  <artifactId>junit</artifactId>
                  <version>${junit.version}</version>
                  <scope>test</scope>
              </dependency>
          </dependencies>
      </dependencyManagement>
  ```

### 5.5. 聚合

- 在真实项目中，一个项目有表现层、持久层，对于业务层和持久层，它们可以在多个工程中被使用，所以一般会将业务层和持久层单独创建为java工程，为其他工程依赖

#### 5.5.1. 创建聚合工程

- 聚合工程的打包方式也是pom

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwa9krz3r8j30gq0f00sr.jpg)

#### 5.5.2. 创建持久层

- 聚合工程的子工程创建选项为`Maven Module`

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwa9lvchl4j30hc0etglm.jpg)

#### 5.5.3. 创建业务层

与创建持久层方式一样

#### 5.5.4. 创建表现层

- 表现层为`web`工程

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwa9r3jodpj30hf0euglo.jpg)

#### 5.5.5. 聚合为一个工程来运行

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>cn.colg</groupId>
    <artifactId>maven-08-parent</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <packaging>pom</packaging>
    <name>maven-08-parent :: maven 聚合工程</name>

    <modules>
        <module>maven-08-dao</module>
        <module>maven-08-service</module>
        <module>maven-08-web</module>
    </modules>
    
    <!-- 在聚合工程中配置tomcat7插件，运行tomcat -->
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.tomcat.maven</groupId>
                <artifactId>tomcat7-maven-plugin</artifactId>
                <configuration>
                    <port>8080</port>
                    <path>/</path>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```

- 访问：http://localhost:8080/

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwa9tuf12wj30c6045q2u.jpg)

## 6. Maven仓库管理

- 什么是Maven仓库

  - 用来统一存储所有Maven共享构建的位置就是仓库。根据Maven坐标定义每个构建在仓库中唯一存储路径，大致为：`groupId/artifactId/version/artifactId-version.packaging`

- Maven仓库的分类

  - 本地仓库

    - 默认在`~/.m2/repository`，如果在用户配置中有配置，则以用户配置的地址为准

  - 默认仓库

    - 中央仓库：http://repo1.maven.org/maven2

    - 私服（本地仓库 -> 私服 -> 中央仓库）

      ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwa9wwxaxwj30k10860sx.jpg)

- 本地jar包手动添加到maven仓库

  ```ini
  用maven命令将jar包移动到maven的repository中
  语法:
  mvn install:install-file -Dfile=jar包的位置(参数一) -DgroupId=groupId(参数二) -DartifactId=artifactId(参数三) -Dversion=version(参数四) -Dpackaging=jar
  
  把”ojdbc-102.4.0.jar”放到”D:\Program Files\mvn\”下
  注意: “Program Files”中间有空格，所以要加双引号，另外三个参数，从上面复制过来即可，下面是安装ojdbc-10.2.0.4.0.jar包使用的命令：
  mvn install:install-file -Dfile="D:\Program Files\mvn\ojdbc-10.2.0.4.0.jar" -DgroupId=com.oracle -DartifactId=ojdbc14 -Dversion=10.2.0.4.0 -Dpackaging=jar
  需要注意以下几点：
  1.注意"-"不能缺少 install后面的"-"是没有空格的
  2.注意"-Dfile"中jar包的路径和jar包的名字.
  3.注意看cmd命令提示,查看本地repository中是否成功的复制了jar包.
  
  重点：Jar包默认都安装在“C:\Users\Administrator\.m2\repository\”下，其实上面的(参数二，参数三，参数四)就是指定安装具体的安装路径。
  ```

## 7. Maven私服

### 7.1. 安装Nexus

- 下载网站：http://nexus.sonatype.org/

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwaa10ztecj30nm0azdl2.jpg)

- 安装版本：nexus-2.14.4-03-bundle.tar.gz

- 安装环境：Linux(CentOS)

- 安装步骤：

  - 将安装包解压到`/usr/local/nexus`目录下

    ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwaa2kudxhj307m02k3ya.jpg)

  - 修改`nexus`端口

    ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwaa2w3b79j30gx06mt8o.jpg)

  - 修改运行的用户

    ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwaa3lqi7rj30h705qglk.jpg)

  - 启动`nexux`

    ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwaa43b8q9j30gx0bvglv.jpg)

### 7.2. 访问Nexus

- 访问：http://192.168.1.117:9901/nexus/#welcome

  ```ini
  默认帐号:
  用户名:      admin
  密码:        admin123
  修改密码:    jiantou
  ```

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwaa5qmw3fj30i109eaai.jpg)

### 7.3. Nexus的仓库和仓库组

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwaa6jd8w7j30g407d0ta.jpg)

- 仓库有4种类型：

  - group(仓库组)：一组仓库的集合
  - hosted(宿主)：配置第三方仓库（包括公司内部私服）
  - proxy(代理)：私服会对中央仓库进行代理，用户连接私服，私服自动去中央仓库下载jar包或插件
  - virtual(虚拟)：兼容`Maven 1`版本的jar或插件

- `Nuexus`的仓库和仓库组介绍：

  - 3rd party：一个策略为Release的宿主类型仓库，用来部署无法从公共仓库获得的第三方发布版本构建
  - Apache Snapshots：l   一个策略为Snapshot的代理仓库，用来代理Apache Maven仓库的快照版本构建
  - Central： 代理Maven中央仓库
  - Central M1 shadow：代理Maven1 版本 中央仓库
  - Codehaus Snapshots：一个策略为Snapshot的代理仓库，用来代理Codehaus Maven仓库的快照版本构件
  - Releases：一个策略为Release的宿主类型仓库，用来部署组织内部的发布版本构件
  - Snapshots：一个策略为Snapshot的宿主类型仓库，用来部署组织内部的快照版本构件
  - **Public Repositories：该仓库组将上述所有策略为Release的仓库聚合并通过一致的地址提供服务**

- **配置私服远程下载地址为阿里云仓库**

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwaafvfs19j30h50cbwf9.jpg)

### 7.4. 配置所有构建均从私服下载

- 在本地仓库的`setting.xml`中配置如下：

  ```xml
  		<!--此处配置所有的构建均从私有仓库中下载 *代表所有，也可以写central -->
  		<mirror>
  			<id>jt</id>
  			<mirrorOf>central</mirrorOf>
  			<name>jiantou maven</name>
  			<url>http://192.168.1.117:9901/nexus/content/groups/public/</url>
  		</mirror>
  ```

### 7.5. 部署构建到Nexus

#### 7.5.1. `Nexus`的访问权限控制

- 在本地仓库的`setting.xml`中配置如下：

  ```xml
  	<!-- nexus访问控制权限 -->
  	<server>
  		<id>releases</id>
  		<username>admin</username>
  		<password>jiantou</password>
  	</server>
  	<server>
  		<id>snapshots</id>
  		<username>admin</username>
  		<password>jiantou</password>
  	</server>
  ```

#### 7.5.2. 配置pom文件

- 在需要构建的项目中修改pom文件

  ```xml
  	<distributionManagement>
  		<repository>
  			<id>releases</id>
  			<name>Internal Releases</name>
  			<url>http://192.168.1.117:9901/nexus/content/repositories/releases/</url>
  		</repository>
  		<snapshotRepository>
  			<id>snapshots</id>
  			<name>Internal Snapshots</name>
  			<url>http://192.168.1.117:9901/nexus/content/repositories/snapshots/</url>
  		</snapshotRepository>
  	</distributionManagement>
  ```

#### 7.5.3. 执行maven的deploy命令

- 执行命令：`mvn deploy`

- 查看是否部署成功：

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwaaownf1hj30sn0lcdhx.jpg)

### 7.6. 宿主库 - 3rd party

- 假如下载了Oracle的驱动程序jar包想给其他项目组使用，就需要上传改jar包

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwaapr58rwj30p60kzgn3.jpg)

## 8. Maven自动部署

### 8.1. Tomcat manager配置

- 编辑tomcat目录下的`conf/tomcat-users.xml`，role节点

  ```xml
  	<role rolename="admin-gui"/>    
  	<role rolename="admin-script"/>    
  	<role rolename="manager-gui"/>    
  	<role rolename="manager-script"/>    
  	<role rolename="manager-jmx"/>    
  	<role rolename="manager-status"/>    
  	<user password="jt_168" roles="manager-gui,manager-script,manager-jmx,manager-status,admin-script,admin-gui" username="jiantou168"/>
  
  ```

  - `script`：让tomcat支持以脚本的形式来管理
  - `gui`：让tomcat支持图形化的管理界面

### 8.2. pom配置

```xml
	<build>
		<plugins>
			<plugin>
				<groupId>org.apache.tomcat.maven</groupId>
				<artifactId>tomcat7-maven-plugin</artifactId>
				<configuration>
					<!-- <url>http://192.168.1.112:8080/manager/text</url> -->
					<!-- 本地测试环境 -->
					<url>http://127.0.0.1:8080/manager/text</url>
					<username>jiantou168</username>
					<password>jt_168</password>
					<update>true</update>
				</configuration>
			</plugin>
		</plugins>
	</build>
```

### 8.3. 部署

- 第一次部署：运行`mvn tomcat7:deploy`进行自动部署(tomcat8,9也是使用tomcat7命令)
- 更新代码后重新部署：运行`mvn tomcat7:redeploy`，如果第一次部署使用`mvn tomcat7:redeploy`，则只会上传war，不会自动解压
- 如果路径在tomcat服务器中已存在并且使用`mvn tomcat7:deploy`命令的话,一定要加`<update>true<update>`配置，不然会报错

### 8.4. 内存泄露问题

#### 8.4.1. 内存泄露原因

使用以上方法部署会出现严重的内存泄漏现象.tomcat的manager提供了诊断在部署时是否产生内存泄漏的功能.

在http://localhost:8080/manager/html页面底部有一个”FInd Leaks”的按钮.

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwaaygxkcuj30sk0d5q38.jpg)

- 点击按钮,网页头部出现如下信息说明在部署的时候有内存泄漏.

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fwaayqy5y1j30w706sq2x.jpg)

上面的消息显示部署的weekly-report-server项目存在内存泄漏,如果同一项目多次重新部署,则一个项目名可能会出现多次.

部署时产生内存泄漏的原因是每次(重新)部署时,Tomcat会为项目新建一个类加载器,而旧的类加载器没有被GC回收.

maven的`classloader-leak-preverntion-servlet`可以用来解决这个问题.

#### 8.4.2. 内存泄露解决

##### 8.4.2.1. 添加maven依赖

```xml
		<!-- 解决部署时内存泄漏 -->
		<dependency>
			<groupId>se.jiderhamn.classloader-leak-prevention</groupId>
			<artifactId>classloader-leak-prevention-servlet</artifactId>
			<version>2.6.1</version>
		</dependency>

```

##### 8.4.2.2. 在web.xml中添加一个Listener

```xml
<!-- 必须让此Listener成为web.xml中的第一个Listener,否则不起作用 -->
<listener>
		<listener-class>se.jiderhamn.classloader.leak.prevention.ClassLoaderLeakPreventorListener</listener-class>
	</listener>

```

- 注意：
  - 添加了这个Listener后,默认在tomcat关闭5s后jvm会进行内存回收的操作,具体时间按设置可参考https://www.cnblogs.com/xyb930826/p/5725340.html,所以在关闭的5s内,再次启动tomcat,可能会存在问题,导致启动无效(如果出现tomcat重启后日志显示正常但是服务器不工作的话考虑一下是不是这个问题)
  - 这个Listener只解决部署的内存泄漏,其他问题(如jdbc等)产生的内存泄漏还需要自己解决.