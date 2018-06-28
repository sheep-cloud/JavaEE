# 一、Eureka 服务注册中心（单机版）

## 1、引入服务端

### 1. 新增一个相关的maven坐标

```xml
        <!-- eureka-server 服务端 -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-eureka-server</artifactId>
        </dependency>
```

### 2. 在主启动类上，标注启动该新组件技术的相关注解标签

  **@EnableEurekaServer**：   服务端启动类，接收其他微服务注册进来

```java
/**
 * Spring Boot 启动类
 * 
 * <pre>
 * '@EnableEurekaServer'：   服务端启动类，接收其他微服务注册进来
 * </pre>
 *
 * @author colg
 */
@EnableEurekaServer
@SpringBootApplication
public class SpringCloudEureka7001Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEureka7001Application.class, args);
    }
}
```

```yaml
server:
  port: 7001
eureka:
  instance:
    hostname: localhost            # erueka 服务端的实例名称
  client:
    register-with-eureka: false    # false 表示不向注册中心注册自己
    fetch-registry: false          # false 表示自己端就是注册中心，并不需要去检索服务
    service-url:                   # 设置与 Eureka Server 交互的地址查询服务和注册服务
      defaultZone: http://${eureka.instance.hostname}:${server.port}/erueka/
```

```http
访问服务中心：http://localhost:7001/
```

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqrh8ozpvfj31gk0e50te.jpg)

No instances available：没有服务被发现，因为没有注册服务进来当然不可能有服务被发现

## 2、引入客户端

### 1. 新增一个相关的maven坐标

```xml
		<!-- eureka 客户端 -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-eureka</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-config</artifactId>
		</dependency>
		<!-- actuator 监控和信息配置 -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
```

### 2. 在主启动类上，标注启动该组件技术的相关注解标签

  **@EnableEurekaClient**：本服务启动后会自动注册进eureka服务中

```java
/**
 * Spring Boot 启动类
 * 
 * <pre>
 * '@EnableEurekaClient'：                 启动后自动注册进eureka服务中
 * '@EnableDiscoveryClient'：              启动后发现客户端
 * </pre>
 *
 * @author colg
 */
@EnableDiscoveryClient
@EnableEurekaClient
@MapperScan("cn.colg.dao")
@SpringBootApplication
public class SpringCloudProviderDept8001Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudProviderDept8001Application.class, args);
    }
}
```

```yaml
eureka:
  client:
    service-url:                            # 将客户端注册进 eureka 服务列表内
      defaultZone: http://localhost:7001/eureka/
```

```http
访问服务中心：http://localhost:7001/
```

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqrjabybuzj31gt0eeq3p.jpg)

## 3、微服务内容完善

### 1. 完善服务名称信息、主机ip显示

```yaml
eureka:
  client:
    service-url:                            		       # 将客户端注册进 eureka 服务列表内
      defaultZone: http://localhost:7001/eureka/
  instance:
    instance-id: ${spring.application.name}-${server.port}  # 自定义 hystrix 相关的服务名称信息
    prefer-ip-address: true                                 # 访问路径可以显示 ip 地址
```

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqrm05csh8j311e0cl0t3.jpg)

## 4、微服务info内容构建

```xml
    <build>
        <finalName>${project.artifactId}-${project.version}</finalName>
        <!-- 获取构建信息（用于修改cloud，服务info信息） -->
        <resources>
            <resource>
                <directory>src/main/resources</directory>
                <filtering>true</filtering>
            </resource>
        </resources>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-resources-plugin</artifactId>
                <version>${maven-resources-plugin.version}</version>
                <configuration>
                    <delimiters>
                        <delimit>$</delimit>
                    </delimiters>
                </configuration>
            </plugin>
        </plugins>
    </build>
```

```yaml
info:                                                    # 配置 eureka info信息
  application:
    name: ${spring.application.name}
    descript: 部门管理，发现服务管理
  build:
    groupId: $project.groupId$
    artifactId: $project.artifactId$
    version: $project.version$
  github: https://github.com/colg-cloud/JavaEE/tree/master/spring-cloud/spring-cloud-parent/spring-cloud-provider-dept-8001
```

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqs6u7pg1nj30tr09mglp.jpg)

# 二、Eureka 服务注册中心（集群版，参考单机版）

## hosts解析（模拟）

```http
# eureka
127.0.0.1 eureka-7001.com
127.0.0.1 eureka-7002.com
127.0.0.1 eureka-7003.com
```

## Eureka 节点

### 节点1：spring-cloud-eureka-7001

```yaml
server:
  port: 7001
  
eureka:
  instance:
    hostname: eureka-7001.com         # 添加hosts解析：127.0.0.1 eureka-7001.com
  client:
    register-with-eureka: false       # false 表示不向注册中心注册自己
    fetch-registry: false             # false 表示自己端就是注册中心，并不需要去检索服务
    service-url:                      # 设置与Eureka Server交互的地址查询服务和注册服务
      defaultZone: http://eureka-7002.com:7002/eureka/,http://eureka-7003.com:7003/eureka/
```

### 节点2：spring-cloud-eureka-7002

```yaml
server:
  port: 7002
  
eureka:
  instance:
    hostname: eureka-7002.com         # 添加hosts解析：127.0.0.1 eureka-7002.com
  client:
    register-with-eureka: false       # false 表示不向注册中心注册自己
    fetch-registry: false             # false 表示自己端就是注册中心，并不需要去检索服务
    service-url:                      # 设置与Eureka Server交互的地址查询服务和注册服务
      defaultZone: http://eureka-7001.com:7001/eureka/,http://eureka-7003.com:7003/eureka/
```

### 节点3：spring-cloud-eureka-7003

```yaml
server:
  port: 7003
  
eureka:
  instance:
    hostname: eureka-7003.com         # 添加hosts解析：127.0.0.1 eureka-7003.com
  client:
    register-with-eureka: false       # false 表示不向注册中心注册自己
    fetch-registry: false             # false 表示自己端就是注册中心，并不需要去检索服务
    service-url:                      # 设置与Eureka Server交互的地址查询服务和注册服务
      defaultZone: http://eureka-7001.com:7001/eureka/,http://eureka-7002.com:7002/eureka/
```

### 客户端

```yaml
eureka:
  client:
    service-url:                                            # 将客户端注册进eureka服务列表内
      defaultZone: http://eureka-7001.com:7001/eureka/,http://eureka-7002.com:7002/eureka/,http://eureka-7003.com:7003/eureka/
  instance:
    instance-id: ${spring.application.name}-${server.port}  # 自定义服务名称信息
    prefer-ip-address: true                                 # 访问路径可以显示 ip 地址
```

### 展示

```http
1. http://eureka-7001.com:7001/
2. http://eureka-7002.com:7002/
3. http://eureka-7003.com:7003/
```

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqt0s3oe0wj31170hpdgk.jpg)
![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqt0sfzp50j311l0hqaas.jpg)
![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqt0sx1sexj31170i1aas.jpg)