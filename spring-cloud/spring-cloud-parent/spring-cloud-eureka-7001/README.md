# Eureka 服务注册中心

## 1、引入服务端

### 1. 新增一个相关的maven坐标

```
		<!-- eureka-server 服务端 -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-eureka-server</artifactId>
		</dependency>
```

### 2. 在主启动类上，标注启动该新组件技术的相关注解标签

  **@EnableEurekaServer**：   服务端启动类，接收其他微服务注册进来
  
![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqrj4e1s5sj30ua061q2y.jpg)

	访问服务中心：http://localhost:7001/
	
![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqrh8ozpvfj31gk0e50te.jpg)

No instances available：没有服务被发现，因为没有注册服务进来当然不可能有服务被发现

## 2、引入客户端

### 1. 新增一个相关的maven坐标

```
		<!-- 将微服务provider注册进eureka -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-eureka</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-config</artifactId>
		</dependency>
```

### 2. 在主启动类上，标注启动该组件技术的相关注解标签

  **@EnableEurekaClient**：本服务启动后会自动注册进eureka服务中
  
![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqrj8hl1roj30li02ejr7.jpg)

	访问服务中心：http://localhost:7001/
	
![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqrjabybuzj31gt0eeq3p.jpg)

## 3、微服务内容完善

### 1. 完善服务名称信息、主机ip显示
![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqrlygleg2j30li03wq2u.jpg)
![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqrm05csh8j311e0cl0t3.jpg)

## 4、微服务info内容构建
![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqrm0qxlqaj30kt04imx2.jpg)
![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqrm1ijt71j30e20a1t8p.jpg)