# HystrixDashboard 服务监控仪表盘

## 1. hystrixDashboard 展示
```http
hystrixDashboard 访问地址：http://localhost:9001/hystrix
```

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fquxgrart9j31hc0r20uv.jpg)

## 2. 配置

### 1. pom.xml

```xml
		<!-- actuator 监控和信息配置 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
		<!-- hystrix 依赖 -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-hystrix</artifactId>
        </dependency>
		<!-- hystrix-dashboard 依赖 -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-hystrix-dashboard</artifactId>
        </dependency>
```

### 2. 启动

```java
/**
 * Spirng Boot 启动类
 * 
 * <pre>
 * `@EnableHystrixDashboard`：   启用 hystrixDashboard 服务监控仪表盘
 * </pre>
 * 
 * @author colg
 */
@EnableHystrixDashboard
@SpringBootApplication
public class SpringCloudConsumerHystrixDashboard9001Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConsumerHystrixDashboard9001Application.class, args);
    }
}
```

## 3. application.yml

```yaml
server:
  port: 9001
```

## 4. 使用

### 1. 设置访问地址
![](http://ww1.sinaimg.cn/large/005PjuVtgy1fquyycy8lxj30p7072t8t.jpg)

- 默认的集群监控：通过URL`http://turbine-hostname:port/turbine.stream`开启，实现对默认集群的监控。
- 指定的集群监控：通过URL`http://turbine-hostname:port/turbine.stream?cluster=[clusterName]`开启，实现对clusterName集群的监控。
- 单体应用的监控：通过URL`http://hystrix-app:port/hystrix.stream`开启，实现对具体某个服务实例的监控。

### 2. 仪表盘说明

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fquyznq6bdj319a0i9h21.jpg)
![](http://ww1.sinaimg.cn/large/005PjuVtgy1fquz0972ibj319x067wkp.jpg)