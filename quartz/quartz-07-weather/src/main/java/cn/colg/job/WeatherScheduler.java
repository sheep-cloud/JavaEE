package cn.colg.job;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 天气预报任务调度
 *
 * @author colg
 */
public class WeatherScheduler {

    public static void startWeatherJob() throws SchedulerException {
        // 任务详情
        JobDetail jobDetail = JobBuilder.newJob(WeatherJob.class)
                                        .build();
        
        // 任务触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                                        .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * ? * *"))
                                        .build();
        
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        
        // 交由scheduler安排触发
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
