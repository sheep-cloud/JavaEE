# dubbo

## 1. 基础知识

### 1.1. 分布式基础理论

#### 1.1.1. 什么是分布式系统？

- <分布式系统原理与规范>定义:
  - 分布式系统是若干独立计算机的集合, 这些计算机对于用户来说就像单个相关系统
  - 分布式系统(distributed system)是简历在网络之上的软件系统
- 随着互联网的发展, 网站应用的规模不断扩大, 常规的垂直应用架构已无法应对, 分布式服务架构以及流动计算架构势在必行, 亟需一个**治理系统**确保架构有条不紊的演进

#### 1.1.2. 发展演变

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fzgm2fpirbj30jg05u3z5.jpg)

- 单一应用架构
  - 当网站流量很小时, 只需一个应用, 将所有功能都部署在一起, 以减少部署节点和成本. 
  - 此时, 用于简化增删改查工作量的**数据访问框架(ORM)**是关键.
  - 应用场景: 小型网站, 小型管理系统, 将所有功能部署到一起, 简单易用
  - 缺点: 性能扩展比较难; 协同开发问题; 不利于升级维护
- 垂直应用架构
  - 当访问量主键增大, 单一应用增加机器带来的加速度越来越小, 将应用拆成互不相干的几个应用, 以提升效率. 
  - 此时, 用于加速前端页面开发的**Web框架(MVC)**是关键.
  - 通过切分业务来实现各个模块独立部署, 降低了维护和部署的难度, 团队各司其职更易管理, 性能扩展也更方便, 更有针对性
  - 缺点: 公共模块无法重复利用, 开发性的浪费
- 分布式服务架构
  - 当垂直应用越来越多, 应用之间交互不可避免, 将核心业务抽取出来, 作为独立的服务, 逐渐形成稳定的服务中心, 使前端应用能更快速的响应多变的市场需求. 
  - 此时, 用于提高业务复用及整合的**分布式服务框架(RPC)**是关键.
- 流动计算架构
  - 当服务越来越多, 容量的评估, 小服务资源的浪费等问题逐渐显现, 此时需增加一个调度中心基于访问压力实时管理集群容量, 提高集群利用率. 
  - 此时, 用于提高机器的**资源调度和治理中心(SOA)**是关键.

#### 1.1.3. RPC

- 什么叫RPC?

  - RPC(Remote Procedure Call)是指**远程过程调用**, 是一种进程间通信方式, 它是一种技术的思想, 而不是规范. 它允许程序调用另一个地址空间(通常是共享网络的另一台机器上)的过程或函数, 而不用程序员显式编码这个远程调用的细节. 即程序员无论是调用本地的还是远程的函数, 本质上编写的调用代码基本相同.

- RPC基本原理

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fzgmnfhkksj30dv08t77k.jpg)

  ![](http://ww1.sinaimg.cn/large/005PjuVtgy1fzgmnj2elnj30gh0fsq51.jpg)

- RPC两个核心模块: 通讯, 序列化

### 1.2. dubbo核心概念

#### 1.2.1. 简介

- 高性能Java RPC框架
  - `Apache Dubbo (incubating) |ˈdʌbəʊ|` 是一款高性能、轻量级的开源Java RPC框架，它提供了三大核心能力：面向接口的远程方法调用，智能容错和负载均衡，以及服务自动注册和发现。
- 官网: http://dubbo.apache.org/zh-cn/index.html

​	

#### 1.2.2. 基本概念

![](http://ww1.sinaimg.cn/large/005PjuVtgy1fzhh7rdg1nj30tg0mw74f.jpg)

- 节点角色说明

  | 节点        | 角色说明                               |
  | ----------- | -------------------------------------- |
  | `Provider`  | 暴露服务的服务提供方                   |
  | `Consumer`  | 调用远程服务的服务消费方               |
  | `Registry`  | 服务注册与发现的注册中心               |
  | `Monitor`   | 统计服务的调用次数和调用时间的监控中心 |
  | `Container` | 服务运行容器                           |

- 调用关系说明

  - 服务容器负责启动, 加载, 运行服务提供者
  - 服务提供者在启动时, 向注册中心注册注册资金提供的服务
  - 服务消费者在启动时, 向注册中心订阅自己所需的服务
  - 注册中心返回服务提供者地址列表给消费者, 如果有变更, 注册中心将基于长连接推送变更数据给消费者
  - 服务消费者, 从提供者地址列表中, 基于软负载均衡算法, 选一台提供者进行调用, 如果调用失败, 再选另一台调用
  - 服务消费者和提供者, 在内存中累计调用次数和调用时间, 定时每分钟发送一次统计数据到监控中心

- Dubbo 架构具有以下几个特点, 分别是连通性, 健壮性, 伸缩性以及向未来架构的升级性.

### 1.3. dubbo环境搭建

#### 1.3.1. windows

##### 1.3.1.1. 安装 [Zookeeper](http://zookeeper.apache.org/)

- 下载zookeeper https://archive.apache.org/dist/zookeeper/

- 解压zookeeper

- 修改zoo.cfg配置文件

  ```ini
  1. 在zookeeper根目录新建 data 文件夹
  
  2. 将 conf 目录下的zoo_sample.cfg复制一份改名为zoo.cfg即可
      注意几个重要位置:
      dataDir=../data		临时数据存储的目录(可写相对路径)
      clientPort=2181		zookeeper的端口号
  
  3. 运行bin目录下 zkServer.cmd
  	cd  cd D:\Config\zookeeper\zookeeper-3.4.13\bin
  	zkServer.cmd
  ```

- 使用zkCli.cmd测试

  ```ini
  1. 运行bin目录下 zkCli.cmd
  	cd D:\Config\zookeeper\zookeeper-3.4.13\bin
  	zkCli.cmd
  	
  2. 测试
  	lis/						列出zookeeper下保存的所有节点
  	create -e /colg 123456		创建一个colg节点, 值为123456
  	get /colg					获取/colg节点的值
  ```

#### 1.3.2. linux

##### 1.3.2.1. 安装 [Zookeeper](http://dubbo.apache.org/zh-cn/docs/admin/install/zookeeper.html)

### 1.4. demo: dubbo-01-gmall

#### 1.4.1. 提出需求

某个电商系统, 订单服务需要调用用户服务, 获取某个用户的所有地址;

现在需要创建两个模块进行测试

| 模块         | 功能           |
| ------------ | -------------- |
| 订单服务模块 | 创建订单等     |
| 用户服务模块 | 查询用户地址等 |

- 测试语气结果
  - 订单服务模块在A服务器, 用户服务模块在B服务器, A可以远程调用B的功能

#### 1.4.2. 工程架构

根据Dubbo [服务化最佳实践](http://dubbo.apache.org/zh-cn/docs/user/best-practice.html)

- 分包
  - 建议将服务接口、服务模型、服务异常等均放在 API 包中，因为服务模型和异常也是 API 的一部分，这样做也符合分包原则：重用发布等价原则(REP)，共同重用原则(CRP)。
  - 如果需要，也可以考虑在 API 包中放置一份 Spring 的引用配置，这样使用方只需在 Spring 加载过程中引用此配置即可。配置建议放在模块的包目录下，以免冲突，如：`com/alibaba/china/xxx/dubbo-reference.xml`。
- 粒度
  - 服务接口尽可能大粒度，每个服务方法应代表一个功能，而不是某功能的一个步骤，否则将面临分布式事务问题，Dubbo 暂未提供分布式事务支持。
  - 服务接口建议以业务场景为单位划分，并对相近业务做抽象，防止接口数量爆炸。
  - 不建议使用过于抽象的通用接口，如：`Map query(Map)`，这样的接口没有明确语义，会给后期维护带来不便。
- 版本
  - 每个接口都应定义版本号，为后续不兼容升级提供可能，如： `<dubbo:service interface="com.xxx.XxxService" version="1.0" />`。
  - 建议使用两位版本号，因为第三位版本号通常表示兼容升级，只有不兼容时才需要变更服务版本。
  - 当不兼容时，先升级一半提供者为新版本，再将消费者全部升为新版本，然后将剩下的一半提供者升为新版本。
- 创建公共模块 `gmall-api`
- 创建用户服务模块 `gmall-user-service-provider`
- 创建订单服务模块 `gmall-order-service-consumer`

#### 1.4.3. 创建模块

### 1.5. 监控中心

[Dubbo OPS](https://github.com/apache/incubator-dubbo-ops)

### 1.6. 整合SpringBoot

[Dubbo Spring Boot](https://github.com/apache/incubator-dubbo-spring-boot-project) 

```ini
SpringBoot与dubbo整合的三种方式:
	1. 导入dubbo-starter, 在application.yml中配置属性, 使用@Service[暴露服务], 使用@Reference[引用服务]
	2. 导入dubbo-starter, 保留dubbo xml配置文件, 使用@ImportResource导入dubbo的配置文件
	3. 使用注解方式, 将每一个组件手动创建到容器中, 让dubbo来扫描其他的组件
```

## 2. dubbo配置

### 2.1. 配置原则

### 2.2. 重试次数

### 2.3. 超时时间

### 2.4. 版本号

## 3. 高可用

### 3.1. zookeeper宕机与dubbo直连

### 3.2. 集群下dubbo负载均衡配置

### 3.3. 整合hystrix，服务熔断与降级处理

## 4. dubbo原理

### 4.1. RPC原理

### 4.2. netty通信原理

### 4.3. dubbo原理