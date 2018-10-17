package cn.colg.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import lombok.extern.slf4j.Slf4j;

/**
 * 日志切面类；
 * 
 * <pre>
 * `@Aspect`：   告诉Spring当前类是一个切面类
 * </pre>
 *
 * @author colg
 */
@Slf4j
@Aspect
public class LogAspects {

    /**
     * 抽取公共的切入点表达式；
     * 
     * <pre>
     * 1. 本类引用
     * 2. 其他的切面引用：全路径
     * </pre>
     *
     * @author colg
     */
    @Pointcut("execution(public int cn.colg.aop.MathCalculator.*(..))")
    public void pointcut() {};

    /**
     * '@Before'： 在目标方法之前切入：切入点表达式（指定在哪个方法切入）
     *
     * @param point
     * @author colg
     */
    @Before("pointcut()")
    public void logStart(JoinPoint point) {
        log.info("logStart() >> {} 开始。。。参数列表是 : {}", point.getSignature().getName(), Arrays.toString(point.getArgs()));
    }

    /**
     * '@After'：在目标方法运行之后运行，无论方法正常结束还是异常结束
     *
     * @param pointcut
     * @author colg
     */
    @After("pointcut()")
    public void logEnd(JoinPoint pointcut) {
        log.info("logEnd() >> {} 结束。。。", pointcut.getSignature().getName());
    }

    /**
     * '@AfterReturning'：在目标方法正常返回之后运行
     *
     * @param pointcut
     * @param result
     * @author colg
     */
    @AfterReturning(pointcut = "pointcut()", returning = "result")
    public void logRet(JoinPoint pointcut, Object result) {
        log.info("log() >> {} 正常返回。。。运行结果是 : {}", pointcut.getSignature().getName(), result);
    }

    /**
     * '@AfterThrowing'：目标方法出现异常以后运行
     *
     * @param pointcut
     * @param exception
     * @author colg
     */
    @AfterThrowing(pointcut = "pointcut()", throwing = "exception")
    public void logException(JoinPoint pointcut, Exception exception) {
        log.info("logException() >> {} 异常。。。异常信息 : {}", pointcut.getSignature().getName(), exception.getMessage());
    }
}
