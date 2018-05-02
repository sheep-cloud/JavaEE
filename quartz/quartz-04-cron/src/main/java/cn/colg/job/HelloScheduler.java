package cn.colg.job;

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
import lombok.extern.slf4j.Slf4j;

/**
 * 任务调度器；
 * 
 * <pre>
 * CronTrigger：
 *  CronTrigger的作用：
 *      基于日历的作业调度器，
 *      而不是像SimpleTrigger那样精确指定间隔时间，比SimpleTrigger更常用。
 *      
 *  Cron表达式：
 *      用于配置CronTrigger实例。
 *      是由7个子表达式组成的字符串，描述了时间表的详细信息。
 *      格式：[秒][分][小时][日][月][周][年]
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
        // 1. 2018年内每天10点15分触发一次
        // String cronExpression = "0 15 10 ? * * 2018";
        
        // 创建一个Trigger实例，定义该job立即执行，并且每个两秒钟重复执行一次，直到永远
        Trigger trigger = TriggerBuilder.newTrigger()
                                        .withIdentity("myTrigger", "group1")
                                        .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                                        .build();
        
        // 创建Scheduler实例
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        
        // 交由Scheduler安排触发
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
