package cn.colg.config;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cn.colg.aop.MathCalculator;
import lombok.extern.slf4j.Slf4j;

/**
 * aop 测试
 *
 * @author colg
 */
@Slf4j
public class Config01OfAopTest {

    private AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config01OfAop.class);

    /**
     * Test method for {@link cn.colg.config.Config01OfAop#mathCalculator()}.
     */
    @Test
    public void testMathCalculator() {
        MathCalculator mathCalculator = applicationContext.getBean(MathCalculator.class);
        log.info("Config01OfAopTest.testMathCalculator() >> mathCalculator.getClass() : {}", mathCalculator.getClass());
        int div = mathCalculator.div(1, 0);
        log.info("Config01OfAopTest.testMathCalculator() >> div : {}", div);
    }

}
