# Spring Cloud
## 1、从面试题开始
### 1. 什么是微服务？
```
微服务的核心就是将传统的一站式应用，根据业务拆分成一个一个的服务，彻底的去耦合，每一个微服务提供单个业务功能的服务，一个服务做一件事，从技术角度看就是一种小而独立的处理过程，类似进程概念，能够自行单独启动或销毁，拥有自己独立的数据库。
微服务强调的是服务的大小，它关注的是某一个点，是具体解决某一个问题/提供落地对应服务的一个服务应用，狭义的看，可以看作Eclipse里面的一个个微服务工程/或者Module。
```

### 2.  微服务之间是如何独立通讯的？

### 3. SpringCloud和Dubbo有哪些区别？

**最大区别：SpringCloud抛弃了Dubbo的RPC通信，采用的是基于HTTP的REST方式。**

严格来说，这两种方式各有优劣。虽然从一定程度上来说，后者牺牲了服务调用的性能，但也避免了上面踢到的原生RPC带来的问题。而且REST相比RPC更为灵活，服务提供方和调用方的依赖只一开一纸契约，不存在代码级别的强依赖，这在强调快速演化的微服务环境下，显得更加合适。

### 4. SpringBoot和SpringCloud，对他们的理解？

-   SpirngBoot

```
微观：专注于快速方便的开发单个个体微服务。
依赖关系：SpringBoot可以离开SpirngCloud独立使用开发项目，但是SpringCloud离不开SpirngBoot，属于依赖的关系。
```

-   SpirngCloud

```
宏观：关注全局的微服务协调整理治理框架，它将SpirngBoot开发的一个个单体微服务整合并管理起来。
依赖关系：必然依赖SpirngBoot
```

**总结：SpringBoot专注于快速、方便的开发单个微服务个体，SpirngCloud关注全局的服务治理框架**

### 5. 什么是服务熔断？什么是服务降级？

#### 1. 服务熔断

熔断机制是应对雪崩效应的一种微服务链路保护机制。

当链路的某个微服务不可用或者响应时间太长时，会进行服务的降级，**进而熔断该节点微服务的调用，快速返回“错误的响应信息。”**当检测到该节点微服务调用响应正常后回复调用链路。在SpringCloud框架里熔断机制通过Hystrix实现。Hystrix会监控服务间调用的状况，当失败的调用到一定阈值，缺省是5秒内20次调用失败就会启动熔断机制。熔断机制的注解是@HystrixCommand。

**一般是某个服务故障或者异常引起，类似现实世界中的“保险丝”，当某个异常条件被触发，直接熔断整个服务，而不是一直等到此服务超时。**

#### 2. 服务降级

整体资源快不够了，忍痛将某些服务先关掉，待渡过难关，再开启回来。

所谓降级，一般是从整体负荷考虑。就是当某个服务熔断之后，服务器将不再被调用。

此时客户端可以自己准备一个本地的fallback回调，返回一个缺省值。

这样做，虽然服务水平下降，但好歹可用，比直接挂掉要强。

### 6. 微服务的优缺点分别是什么？常见的坑有哪些？

- 优点
```
每个服务足够内聚，足够小，代码容易理解这样能聚集一个指定的业务功能或业务需求；
开发简单、开发效率提高，一个服务可能就是专一的只干一件事；
微服务能够被小团队单独开发，这个小团队是2到5人的开发人员组成；
微服务是松耦合的，是有功能意义的服务，无论是在开发阶段或部署阶段都是独立的。
微服务能使用不同的语言开发；
易于和第三方集成，微服务允许容易且灵活的方式集成自动部署，通过持续集成工具，如Jenkins，Hudson，bamboo；
微服务允许你利用融合最新技术；
**微服务知识业务逻辑的代码，不会和html, css或其他界面组件混合。**
**每个微服务都有自己的存储能力，可以有自己的数据库。也可以有统一数据库。**
```
- 缺点
```
开发人员要处理分布式系统的复杂性
多服务运维难度，随着服务的增加，运维的压力也在增大
系统部署依赖
服务件通信成本
数据一致性
系统集成测试
性能监控......
```

### 7. 微服务技术栈有哪些？

- 微服务技术栈：多种技术的集合体
  | 微服务条目                | 维度   | 落地技术                                     | E时代下的数字化生活 |
  | :------------------- | ---- | :--------------------------------------- | ---------- |
  | 服务开发                 |      | SpringBoot、Spring、SpringMVC              | 手机         |
  | 服务配置与管理              |      | Netflix公司的Archaius、阿里的Diamond等           |            |
  | 服务注册与发现              |      | Eureka、Consul、Zookeeper等                 | 电脑         |
  | 服务调用                 |      | Rest、RPC、gRPC                            | 路由器        |
  | 服务熔断器                |      | Hystrix、Envoy等                           | 充电宝        |
  | 负载均衡                 |      | Ribbon、Nginx等                            |            |
  | 服务接口调用（客户端调用服务的简化工具） |      | Feign等                                   |            |
  | 消息队列                 |      | Kafka、RabbitMQ、ActiveMQ等                 |            |
  | 服务配置中心管理             |      | SpringCloudConfig、Chef等                  |            |
  | 服务路由（API网关）          |      | Zuul等                                    |            |
  | 服务监控                 |      | Zabbix、Nagios、Metrics、Spectator等         |            |
  | 全链路追踪                |      | Zipkin、Brave、Dapper等                     |            |
  | 服务部署                 |      | Docker、OpenStack、Kubernetes等             |            |
  | 数据流操作开发包             |      | SpringCloud Stream（封装与Redis、Rabbit、Kafka等发送接收消息） |            |
  | 事件消息总线               |      | SpringCloud Bus                          |            |
  | ...                  |      |                                          |            |

### 8. eureka和zookeeper都可以提供服务注册与发现的功能，他们的区别是什么？

## 2、微服务概述

## 3、SpringCloud入门概述

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fqqkuxnkwaj30sf0oc0uc.jpg)
SpringCloud，基于SpringBoot提供了一整套微服务解决方案，包括服务注册与发现，配置中心，全链路监控，服务网管，负载均衡，熔断器等组件，除了基于NetFlix的开源组件做高度抽象封装之外，还有一些选型中立的开源组件。

SpringCloud利用SpringBoot的开发便利性巧妙的建华路分布式系统基础设置的开发，SpringCloud为开发人员提供了快速构建分布式系统的一些工具，**包括配置管理、服务发现、断路器、路由、微代理、事件总线、全局锁、决策竞选、分布式会话等等**它们都可以用SpringBoot的开发风格做到一键启动和部署。

SpringBoot并没有重复造轮子，它知识将目前各家公司开发的比较成熟、经得起实际考验的服务框架组合起来，通过SpirngBoot风格进行再封装屏蔽掉了复杂的配置和实现原理，**最终给开发者留出了一套简单易懂、易部署和易维护的分布式系统开发工具包**

**总结：SpirngCloud是分布式微服务架构下的一站式解决方案，是各个微服务架构落地技术的集合体，俗称微服务全家桶**

## 4、Rest为服务构建案例工程模块

## 5、Eureka服务注册与发现

### 1. Eureka是什么？

Eureka是Netfix的一个子模块，也是核心模块hi亿。Eureka是一个基于REST的服务，用于定位服务，以实现云端中间服务发现和故障专一。服务注册与发现对于微服务架构来说是非常重要的，有了服务发现与注册，**只需要使用服务的标识符，就可以访问到服务**，而不需要修改服务调用的配置文件了。**功能类似于dubbo的注册中心，比如Zookeeper。**

### 2. Eureka的自我保护机制

在自我保护模式中，Eureka Server会保护服务注册表中的信息，不再注销任何服务实例。当它收到的心跳数重新回复到阈值以上时，该Eureka Server节点就会自动退出自我保护模式。它的设计哲学就是宁可保留错误的服务注册信息，也不盲目注销任何可能健康的服务实例。**一句话讲解：好死不如赖活着。**

在SpringCloud中，可以使用eureka.server.enable-self-preservation=false禁用自我保护模式（没必要）

### 3. Eureka集群配置

#### 1. 配置hosts解析地址：

```
# eureka
127.0.0.1 eureka-7001.com
127.0.0.1 eureka-7002.com
127.0.0.1 eureka-7003.com
```

#### 2. 配置 Eureka 集群

节点1：

```
server:
  port: 7001
  
eureka:
  instance:
# 单机   hostname: eureka-7001.com         # eureka 服务端的实例名称
    hostname: eureka-7001.com         # 添加hosts解析：127.0.0.1 eureka-7001.com
  client:
    register-with-eureka: false       # false 表示不向注册中心注册自己
    fetch-registry: false             # false 表示自己端就是注册中心，职责就是维护服务实例，并不需要去检索服务
    service-url:                      # 设置与Eureka Server交互的地址查询服务和注册服务
# 单机     defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
      defaultZone: http://eureka-7002.com:7002/eureka/,http://eureka-7003.com:7003/eureka/
```

节点2：

```
server:
  port: 7002
  
eureka:
  instance:
    hostname: eureka-7002.com         # 添加hosts解析：127.0.0.1 eureka-7002.com
  client:
    register-with-eureka: false       # false 表示不向注册中心注册自己
    fetch-registry: false             # false 表示自己端就是注册中心，职责就是维护服务实例，并不需要去检索服务
    service-url:                      # 设置与Eureka Server交互的地址查询服务和注册服务
      defaultZone: http://eureka-7001.com:7001/eureka/,http://eureka-7003.com:7003/eureka/
```

节点3：

```
server:
  port: 7003
  
eureka:
  instance:
    hostname: eureka-7003.com         # 添加hosts解析：127.0.0.1 eureka-7003.com
  client:
    register-with-eureka: false       # false 表示不向注册中心注册自己
    fetch-registry: false             # false 表示自己端就是注册中心，职责就是维护服务实例，并不需要去检索服务
    service-url:                      # 设置与Eureka Server交互的地址查询服务和注册服务
      defaultZone: http://eureka-7001.com:7001/eureka/,http://eureka-7002.com:7002/eureka/
```

#### 3. 微服务注册到Eureka集群中

```
eureka:
  client:
    service-url:                                        # 将客户端注册进eureka服务列表内
# 单机     defaultZone: http://localhost:7001/eureka/
      defaultZone: http://eureka-7001.com:7001/eureka/,http://eureka-7002.com:7002/eureka/,http://eureka-7003.com:7003/eureka/
  instance:
    instance-id: spring-cloud-provider-dept-8001         # 自定义服务名称信息
    prefer-ip-address: true                              # 访问路径可以显示ip地址
```



## 6、Ribbon负载均衡

Ribbon是Netflix发布的开源项目，主要功能是提供**客户端的软件负载均衡算法**，将Netflix的中间层服务连接在一起，Ribbon客户端组件提供一系列完善的配置项如连接超时，重试等。简单的说，就是在配置文件中列出Load Balancer（简称LB）后面所有的机器，Ribbon会自动的帮助你基于某种规则（如简单轮训，随机连接等等）去连接这些机器，我们也很容易使用Ribbon实现自定义的负载均衡算法。

## 7、Feign负载均衡

#### 1. Feign是什么？

Feign是一个声明式的Web服务客户端，使得编写Web服务客户端扁的非常容易，**只需要创建一个接口，然后在上面添加注解，通过接口调用即可。**

#### 2. Feign能干什么？

Feign旨在使编写Java Http客户端变得更容易。

前面在使用Ribbon+RestTemplate时，利用RestTemplate对http请求的封装处理，形成了**一套模版化的调用方法。**但是在实际开发中，由于对服务依赖的调用可能不止一处，**往往一个接口会被多出调用，所以通常都会针对每个微服务自行封装一些客户端类来包装这些依赖服务的调用。**所以，Feign在此基础上做了进一步封装，由他来帮助我们定义和实现依赖服务接口的定义。在Feign的实现下，**我们只需要创建一个接口并使用注解的方式来配置它（以前是Dao接口上面标注Mapper注解，现在是一个微服务接口上面标注一个Feign注解）**，即可完成对服务提供方的接口绑定，简化了使用Spring Cloud Ribbon时，自动封装服务调用客户端的开发量。

## 8、Hystrix断路器

#### 1. Hystrix是什么？

Hystrix是一个用于处理分布式系统的**延迟**和**容错**的开源库，在分布式系统里，许多依赖不可避免的会调用失败，比如超时、异常等，Hystrix能够保证在一个依赖出问题的情况下，**不会导致整体服务失败，避免级联故障，以提高分布式系统的弹性。**

"断路器"本身是一种开关装置，当某个服务单元发生故障之后，通过断路器的故障监控（类似熔断保险丝），**向调用房返回一个符合预期的、可处理的备选响应（FallBack），而不是常见的等待或者抛出调用方无法处理的异常，**这样就保证了服务调用方的线程不会长时间、不必要的占用，从而避免了故障在分布式系统中的蔓延，乃至雪崩。

## 9、zuul路由网关

## 10、SpringCloud Config分布式配置中心

