package cn.colg.config;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cn.colg.aop.MathCalculator;
import lombok.extern.slf4j.Slf4j;

/**
 * 
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
        log.info("testMathCalculator() >> mathCalculator.getClass() : {}", mathCalculator.getClass());
        int div = mathCalculator.div(1, 0);
        log.info("testMathCalculator() >> div : {}", div);
    }

    /**
     * Test method for {@link cn.colg.config.Config01OfAop#logAspects()}.
     */
    @Test
    public void testLogAspects() {
        fail("Not yet implemented"); // TODO
    }

}
