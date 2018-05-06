package cn.colg.xxl.job.jobhandler;

import org.springframework.stereotype.Component;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;

/**
 * HelloWorld任务；
 *
 * @author colg
 */
@JobHandler("helloWorldJobHandler")
@Component
public class HelloWorldJobHandler extends IJobHandler {

    @Override
    public ReturnT<String> execute(String param) throws Exception {
        Console.log("今晚吃鸡，大吉大利 ： {}" , DateUtil.now());
        return SUCCESS;
    }

}
