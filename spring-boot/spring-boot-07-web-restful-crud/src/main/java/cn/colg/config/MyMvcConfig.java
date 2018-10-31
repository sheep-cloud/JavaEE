package cn.colg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import cn.colg.component.LoginHandlerInterceptor;
import cn.colg.component.MyLocaleResolver;

/**
 * <pre>
 * SpringMVC 扩展
 *  `WebMvcConfigurerAdapter`： 扩展SpringMVC的功能
 *  
 *  // @EnableWebMvc 不要接管SpringMVC
 * </pre>
 *
 * @author colg
 */
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {

    /**
     * 视图映射 <br>
     * 所有的WebMVcConfigurerAdapter组件都会一起起作用
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 浏览器发送 /colg 请求来到 success 页面
        // 请求: http://localhost:8080/colg 跳转: classpath:/templates/success.html
        registry.addViewController("/colg").setViewName("success");

        // 跳转: classpath:/templates/login.html
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");

        // 跳转: classpath:/templates/dashboard.html
        registry.addViewController("/main.html").setViewName("dashboard");
    }

    /**
     * 区域解析
     *
     * @return
     * @author colg
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }

    /**
     * 拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // SpringBoot已经做好了静态资源映射，不需要再处理
        registry.addInterceptor(new LoginHandlerInterceptor())
                // 拦截的请求
                .addPathPatterns("/**")
                // 不拦截的请求
                .excludePathPatterns("/", "/index", "/index.html", "/user/login", "/success*", "/hello/**");
    }
}
