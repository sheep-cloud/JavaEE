# quarta.properties 配置文件

## 1、文档的位置和加载顺序
- 优先读取 resources/quartz.properties
- 如果找不到，则读取 /org/quartz/quartz.properties

## 2、组成部分（常用属性）
- 调度器属性

```
org.quartz.scheduler.instanceName	用来区分特定的调度器实例，可以按照功能用途来给调度器起名。
org.quartz.scheduler.instanceid		必须在所有调度器实例中是唯一的，尤其是在一个集群当中，可为集群的唯一key。假如向让Quartz生成的话，可以设置为AUTO。
```

- 线程池属性

```
org.quartz.threadPool.threadCount
org.quartz.threadPool.threadPriority
org.quartz.threadPool.class			线程池的实现类（一般使用SimpleThreadPool即可满足几乎所有用户的需求）
```

- 作业存储设置

```
描述了在调度器实例的生命周期中，Job和Trigger信息是如何被存储的。
```

- 插件配置

```
满足特定需求用到的Quartz插件的配置。
```