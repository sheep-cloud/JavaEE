package cn.colg.spring.test.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.colg.controller.BookController;
import cn.colg.service.BookService;
import lombok.extern.slf4j.Slf4j;

/**
 * 配置类，测试
 *
 * @author colg
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class BookControllerTest {

    @Autowired
    private BookController bookController;
    @Autowired
    private BookService bookService;

    /**
     * Test method for {@link cn.colg.controller.BookController#BookController()}.
     */
    @Test
    public void testBookController() {
        log.info("testBookController() >> bookController.getClass() : {}", bookController.getClass());
        log.info("testBookController() >> bookService.getClass() : {}", bookService.getClass());
    }

}
