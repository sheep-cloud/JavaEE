package cn.colg.job;

import org.quartz.SchedulerException;

/**
 * 
 *
 * @author colg
 */
public class HelloSchedulerTest {
    
    /**
     * Test method for {@link cn.colg.job.HelloScheduler#startHelloJob()}.
     *
     * @param args
     * @throws SchedulerException
     */
    public static void main(String[] args) throws SchedulerException {
        HelloScheduler.startHelloJob();
    }

}
