package cn.colg.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.colg.dao.BookDao;
import cn.colg.service.BookService;
import cn.hutool.core.lang.Console;
import lombok.ToString;

/**
 * BookServiceImpl
 *
 * @author colg
 */
@ToString
@Service
public class BookServiceImpl implements BookService {

    @Qualifier("bookDao")
    @Autowired(required = false)
    private BookDao bookDao;

    @Override
    public void console() {
        Console.log(bookDao);
    }

}
