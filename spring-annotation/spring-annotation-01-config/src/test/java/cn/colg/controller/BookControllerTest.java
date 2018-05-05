package cn.colg.controller;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cn.colg.config.Config01;
import cn.colg.service.BookService;
import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 配置类，测试
 *
 * @author colg
 */
@Slf4j
public class BookControllerTest {

    /** 创建一个新的AnnotationConfigApplicationContext，从给定的注释类派生bean定义并自动刷新上下文。 */
    private AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config01.class);

    @Test
    public void testApplicationContext() throws Exception {
        // 返回此工厂中定义的所有Bean的名称。
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        CollUtil.newArrayList(definitionNames).forEach(name -> log.info("testApplicationContext() >> name : {}", name));
    }

    /**
     * Test method for {@link cn.colg.controller.BookController#BookController()}.
     */
    @Test
    public void testBookController() {
        BookController bookController = applicationContext.getBean(BookController.class);
        BookService bookService = applicationContext.getBean(BookService.class);

        log.info("testBookController() >> bookController.getClass() : {}", bookController.getClass());
        log.info("testBookController() >> bookService.getClass() : {}", bookService.getClass());
    }

}
