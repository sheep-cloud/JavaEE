package cn.colg.service.impl;

import org.springframework.stereotype.Service;

import cn.colg.service.BookService;
import lombok.extern.slf4j.Slf4j;

/**
 * BookServiceImpl
 *
 * @author colg
 */
@Slf4j
@Service
public class BookServiceImpl implements BookService {
    
    public BookServiceImpl() {
        log.info("BookServiceImpl() >> : {}", "BookServiceImpl 构造方法");
    }

}
