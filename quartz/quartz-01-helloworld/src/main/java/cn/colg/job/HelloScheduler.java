package cn.colg.job;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.alibaba.fastjson.JSON;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 任务调度器
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
        // 创建一个JobDetail实例，将该实例与HelloClass绑定
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                                        .withIdentity("myJob", "group1")
                                        .build();
        
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        jobDataMap.put("did", IdUtil.simpleUUID());
        jobDataMap.put("air", IdUtil.simpleUUID());
        
        log.info("HelloScheduler.startHelloJob() : {}", JSON.toJSONString(jobDetail));
        
        // 创建一个Trigger实例，定义该job立即执行，并且每隔两秒钟重复执行一次，直到永远
        Trigger trigger = TriggerBuilder.newTrigger()
                                        .withIdentity("myTrigger")
                                        .startNow()
                                        .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))
                                        .build();
        
        // 创建Scheduler实例
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        
        log.info("HelloScheduler.startHelloJob() : {}", DateUtil.now());
        // 交由Scheduler安排触发
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
