package cn.colg.job;

import org.quartz.SchedulerException;

/**
 * 任务调度器 测试
 *
 * @author colg
 */
public class WeatherSchedulerTest {

    /**
     * Test method for {@link cn.colg.job.WeatherScheduler#startWeatherJob()}.
     * 
     * @throws SchedulerException
     */
    public static void main(String[] args) throws SchedulerException {
        WeatherScheduler.startWeatherJob();
    }

}
