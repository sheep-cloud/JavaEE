package cn.colg.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.hutool.core.date.DateUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 任务类；
 * 
 * <pre>
 * Job定义：   实现业务逻辑的任务接口
 * 
 * 浅谈Job：
 *  - Job接口非常容易实现，只有一个execute方法，类似TimerTask的run方法，在里面编写业务逻辑
 * 
 * Job实例在Quartz中的声明周期
 *  - 每次调度器执行job时，它在调用execute方法前会创建一个新的job实例。
 *  - 当调用完成后，关联的job对象实例会被释放，释放的实例会被垃圾回收机制回收。
 * </pre>
 *
 * @author colg
 */
@Slf4j
@Data
public class HelloJob implements Job {

    private String message;
    private Float floatJobValue;
    private Double doubleTriggerValue;

    /**
     * 浅谈JobExecutionContext
     * 
     * <pre>
     * JobExecutionContext是什么？
     *  - 当Scheduler调用一个Job，就会将JobExecutionContext传递给Job的execute()方法；
     *  - Job能通过JobExecutionContext对象访问到Quartz运行时候的环境以及Job本身的明细数据。
     *  
     * JobDataMap是什么？
     *  - 在进行任务调度时JobDataMap存储在JobExecutionContext中，非常方便获取；
     *  - JobDataMap可以用来装载任何可序列化的数据对象，当job实例对象被执行时这些参数对象会传递给它；
     *  - JobDataMap实现了JDK的Map接口，并且添加了一些非常方便的方法用来存取基本数据类型。
     *  
     * 获取JobDataMap的两种方式
     *  - 从Map中直接获取
     *  - Job实现类中添加setter方法对应JobDataMap的键值（Quarta框架默认的JobFactory实现类在初始化job实例对象时会自动的调用这些setter方法）
     * </pre>
     *
     * @param context
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("HelloJob.execute(context) : {}", DateUtil.now());

        // 编写具体的业务逻辑
        log.info("execute() >> Hello World : {}", "Quartz");

        // 通过getter/setter获取jobDataMap的key的值
        log.info("HelloJob.execute(context) message : {}, floatJobValue : {}, doubleTriggerValue : {}", message, floatJobValue, doubleTriggerValue);

        // 获取触发器信息
        Trigger trigger = context.getTrigger();
        log.info("execute() >> trigger : {}", JSON.toJSONString(trigger, SerializerFeature.WriteDateUseDateFormat));
    }

}
