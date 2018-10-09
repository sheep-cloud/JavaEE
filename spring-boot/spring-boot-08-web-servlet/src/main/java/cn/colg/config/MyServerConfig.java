package cn.colg.config;

import java.util.Arrays;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import cn.colg.filter.MyFilter;
import cn.colg.listener.MyListener;
import cn.colg.servlet.MyServlet;

/**
 * 修改Servlet配置
 *
 * @author colg
 */
@Configuration
public class MyServerConfig extends WebMvcConfigurerAdapter {

    /**
     * 配置嵌入式的Servlet
     *
     * @return
     * @author colg
     */
    @Bean
    public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer() {
        return new EmbeddedServletContainerCustomizer() {

            /**
             * 定制嵌入式的Servlet容器相关的规则
             *
             * @param container
             */
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                container.setPort(8083);
            }
        };
    }

    /**
     * 注册Servlet
     *
     * @return
     * @author colg
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new MyServlet(), "/myServlet");
        return servletRegistrationBean;
    }
    
    /**
     * 注册Filter
     *
     * @return
     * @author colg
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new MyFilter());
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/hello", "/myServlet"));
        return filterRegistrationBean;
    }
    
    /**
     * 注册Listener
     *
     * @return
     * @author colg
     */
    @Bean
    public ServletListenerRegistrationBean<MyListener> servletListenerRegistrationBean() {
        ServletListenerRegistrationBean<MyListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<>(new MyListener());
        return servletListenerRegistrationBean;
    }
}
