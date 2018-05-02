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

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 任务调度器；
 * 
 * <pre>
 * 浅谈JobDetail：
 *  JobDetail为Job实例提供了许多设置属性，以及JobDataMap成员变量属性，它用来存储特定Job实例的状态信息、调度器需要借助JobDetail对象来添加Job实例。
 *  重要属性：
 *      name：                        任务名称
 *      group：                      任务分组，默认：DEFAULT
 *      jobClass：            任务的实现类
 *      jobDataMap：       任务参数
 *      
 * 浅谈Trigger：
 *  触发器通用属性：
 *      JobKey：                     表示Job实例的标识，触发器被触发时，该指定的job实例会执行。
 *      StartTime：            表示触发器的时间表首次被触发的时间。它的值的类型是Java.util.Date。
 *      EndTime：                   指定触发器的不再被触发的时间。它的值的类型是Java.util.Date。
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
                                        .usingJobData("message", "hello myjob1")
                                        .usingJobData("floatJobValue", 3.14F)
                                        .build();
        
        // 获取距离当前时间3秒后的时间
        DateTime startDate = DateUtil.offsetSecond(new Date(), 3);
        // 获取距离当前时间10秒后的时间
        DateTime endDate = DateUtil.offsetSecond(new Date(), 10);
        
        log.info("HelloScheduler.startHelloJob() : {}", JSON.toJSONString(jobDetail));
        
        // 创建一个Trigger实例，定义该job立即执行，并且每个两秒钟重复执行一次，直到永远
        Trigger trigger = TriggerBuilder.newTrigger()
                                        .withIdentity("myTrigger", "group1")
                                        .usingJobData("message", "hello myTrigger1")
                                        .usingJobData("doubleTriggerValue", 2.0D)
                                        .startAt(startDate)
                                        .endAt(endDate)
                                        .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                                        .build();
        
        // 创建Scheduler实例
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        
        // 交由Scheduler安排触发
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
