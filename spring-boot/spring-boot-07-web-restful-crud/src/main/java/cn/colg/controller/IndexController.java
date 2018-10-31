package cn.colg.controller;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * LoginController
 *
 * @author colg
 */
public class IndexController {

    /**
     * 使用空的controller跳转登录页；<br>
     * 
     * 可以被视图映射替换: {@link cn.colg.config.MyMvcConfig#addViewControllers(org.springframework.web.servlet.config.annotation.ViewControllerRegistry)}.
     *
     * @return
     * @author colg
     */
    @GetMapping({"/", "/index", "index.html"})
    public String index() {
        // classpath:/templates/login.html
        return "login";
    }

}
