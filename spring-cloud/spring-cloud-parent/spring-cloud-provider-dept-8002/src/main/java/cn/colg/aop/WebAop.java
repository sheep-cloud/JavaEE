package cn.colg.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import cn.colg.bean.ResultBean;
import cn.colg.exception.CheckException;
import lombok.extern.slf4j.Slf4j;

/**
 * aop 处理、包装异常
 *
 * @author colg
 */
@Slf4j
@Aspect
@Component
public class WebAop {

    @Pointcut("execution(public cn.colg.bean.ResultBean *(..))")
    private void controllerMethod() {}

    @Around("controllerMethod()")
    public ResultBean handlerControllerMethod(ProceedingJoinPoint point) {
        long startTime = System.currentTimeMillis();

        ResultBean resultBean = new ResultBean();
        try {
            // 执行目标方法
            resultBean = (ResultBean)point.proceed();
            log.info(point.getSignature() + " USE TIME: [{}ms]", System.currentTimeMillis() - startTime);
        } catch (Throwable e) {
            // 执行目标方法异常，包装异常
            resultBean = handlerException(point, e);
        }
        return resultBean;
    }

    /**
     * 包装异常信息
     *
     * @param point
     * @param e
     * @return
     */
    private ResultBean handlerException(ProceedingJoinPoint point, Throwable e) {
        ResultBean resultBean = new ResultBean();

        // 已知异常
        if (e instanceof CheckException || e instanceof IllegalArgumentException) {
            // 校验出错，参数非法
            resultBean.setMsg(e.getLocalizedMessage());
            resultBean.setCode(ResultBean.CHECK_FAIL);
        }
        // 未知异常
        else {
            log.error(point.getSignature() + " ERROR {}", e);
            resultBean = new ResultBean(e);
        }

        return resultBean;
    }
}
