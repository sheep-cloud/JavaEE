package cn.colg.job;

import org.quartz.Job;
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
        log.info("HelloJob.execute(context) : {}", DateUtil.now());

        // 编写具体的业务逻辑
        log.info("execute() >> Hello World : {}", "Quartz");
    }

}
