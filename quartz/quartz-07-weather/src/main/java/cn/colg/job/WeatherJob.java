package cn.colg.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 天气预报任务类
 *
 * @author colg
 */
@Slf4j
public class WeatherJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("WeatherJob.execute() >>  : {}", DateUtil.now());
        
        String result = HttpUtil.get("https://api.thinkpage.cn/v2/weather/all.json?city=101190408&language=zh-chs&unit=c&aqi=city&key=VQZU1H5TOT");
        Console.log(result);
    }

}
