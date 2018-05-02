package cn.colg.job;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.alibaba.fastjson.JSON;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 任务调度器；
 * 
 * <pre>
 * 浅谈Scheduler
 *  Scheduler - 工厂模式
 *      - 所有的Scheduler实例应该由SchedulerFactory来创建
 *      
 *  StdSchedulerFactory
 *      - 使用一组参数（Java.util.Properties）来创建和初始化Quartz调度器
 *      - 配置参数一般存储在quartz.properties中
 *      - 调用getScheduler方法就能创建和初始化调度器对象
 * </pre>
 * 
 * @author colg
 */
@Slf4j
public class HelloScheduler {

    /**
     * 执行HelloJob任务
     *
     * @throws SchedulerException
     */
    public static void startHelloJob() throws SchedulerException {
        log.info("HelloScheduler.startHelloJob() : {}", DateUtil.now());
        
        // 创建一个JobDetail实例，将该实例与HelloClass绑定
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                                        .withIdentity("myJob", "group1")
                                        .build();
        
        log.info("HelloScheduler.startHelloJob() : {}", JSON.toJSONString(jobDetail));
        
        /// 格式：[秒][分][小时][日][月][周][年]
        String cronExpression = "0/5 * * ? * *";
        
        // 创建一个Trigger实例，定义该job立即执行，并且每个两秒钟重复执行一次，直到永远
        Trigger trigger = TriggerBuilder.newTrigger()
                                        .withIdentity("myTrigger", "group1")
                                        .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                                        .build();
        
        // 创建Scheduler实例
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        // 启动触发器的调度程序线程
        scheduler.start();
        
        // scheduler 执行两秒后挂起
        ThreadUtil.sleep(2000);
        scheduler.standby();
        
        // scheduler 挂起三秒后继续执行
        ThreadUtil.sleep(3000);
        scheduler.start();
        
        // 交由Scheduler安排触发，返回的是最近一次任务将要执行的时间
        Date date = scheduler.scheduleJob(jobDetail, trigger);
        log.info("startHelloJob() >> date : {}", DateUtil.formatDateTime(date));
    }
}
