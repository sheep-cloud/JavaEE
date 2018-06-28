# 1、hystrix 断路器 熔断机制-服务端

## 1. pom.xml

```xml
		<!-- hystrix 依赖 -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-hystrix</artifactId>
        </dependency>
```

## 2. 启动

```java
/**
 * Spring Boot 启动类
 * 
 * <pre>
 * '@EnableEurekaClient'：                 启动后自动注册进eureka服务中
 * '@EnableDiscoveryClient'：              启动后发现客户端
 * '@EnableCircuitBreaker'：               开启对 hystrix 熔断机制的支持
 * </pre>
 *
 * @author colg
 */
@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableEurekaClient
@MapperScan("cn.colg.dao")
@SpringBootApplication
public class SpringCloudProviderDeptHystrix8001Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudProviderDeptHystrix8001Application.class, args);
    }
}
```

可以使用 '@SpringCloudApplication' 组合注解简写

```java
/**
 * @author Spencer Gibb
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public @interface SpringCloudApplication {
}
```

```java
/**
 * Spring Boot 启动类
 * 
 * <pre>
 * '@SpringCloudApplication'：           启动后发现客户端，并开启对 hystrix 熔断机制的支持
 * </pre>
 *
 * @author colg
 */
@EnableEurekaClient
@MapperScan("cn.colg.dao")
@SpringCloudApplication
public class SpringCloudProviderDeptHystrix8001Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudProviderDeptHystrix8001Application.class, args);
    }
}
```



## 3. 配置（指定备用逻辑）

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fquxlxhgp5j30u70ghmxt.jpg)

# 2、hystrix 断路器 熔断机制-客户端（服务降级）

## 1. pom.xml

```xml
		<!-- hystrix 依赖 -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-hystrix</artifactId>
        </dependency>
```

## 2. 配置（指定备用逻辑）
![](http://ww1.sinaimg.cn/large/005PjuVtgy1fquy2y313hj30xz0krmxs.jpg)
![](http://ww1.sinaimg.cn/large/005PjuVtgy1fquy3olyonj30n30hidg8.jpg)