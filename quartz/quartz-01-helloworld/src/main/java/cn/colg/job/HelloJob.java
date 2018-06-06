package cn.colg.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 任务类；
 * 
 * @author colg
 */
@Slf4j
public class HelloJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("HelloJob.execute() >> : {}", DateUtil.now());

        // 编写具体的业务逻辑
        log.info("execute() >> Hello World : {}", "Quartz");
        
        
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        String did = (String)jobDataMap.get("did");
        String air = (String)jobDataMap.get("air");
        log.info("HelloJob.execute() >> did : {}", did);
        log.info("HelloJob.execute() >> air : {}", air);
    }

}
