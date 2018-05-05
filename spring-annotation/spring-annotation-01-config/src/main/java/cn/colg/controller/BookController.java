package cn.colg.controller;

import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;

/**
 * BookController
 *
 * @author colg
 */
@Slf4j
@Controller
public class BookController {

    public BookController() {
        log.info("BookController() >> : {}", "BookController 构造方法");
    }

}
