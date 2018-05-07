package cn.clog.bean;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.clog.SpringBoot03ConfigApplicationTest;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 *
 * @author colg
 */
@Slf4j
public class TaxiTest extends SpringBoot03ConfigApplicationTest {

    @Autowired
    private Taxi taxi;

    /**
     * Test method for {@link cn.clog.bean.Taxi#Taxi()}.
     */
    @Test
    public void testTaxi() {
        log.info("testTaxi() >> taxi : {}", taxi);
    }

}
