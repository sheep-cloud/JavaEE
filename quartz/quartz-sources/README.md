# Quartz Java定时任务调度工具

## 1、Quartz简介

### 1. 特点
-   强大的调度功能
-   灵活的应用方式
-   分布式和集群能力

### 2. 主要用到的设计模式
-   Builder模式
-   Factory模式
-   组件模式
-   链式写法

### 3. 三个核心概念
-   调度器 scheduler
-   任务 job
-   触发器 trigger

### 4. 重要组成
-   Job
-   JobDetail
-   JobBuilder
-   JobStore
-   Trigger
-   TriggerBuilder
-   ThreadPool
-   Scheduler
-   Calenday； 一个Trigger可以和多个Calendar关联，以排除或包含某些时间点
-   监听器； JobListener，TriggerListener，SchedulerListener。