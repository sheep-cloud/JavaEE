package cn.colg.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/**
 * 数据源配置
 *
 * @author colg
 */
@Configuration
public class DruidConfig {

    /**
     * 配置 Druid数据源
     *
     * @return
     * @author colg
     */
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    /*
     * colg  [配置Druid的监控]
     *  1. 配置一个管理后台的 Servlet
     *  2. 配置一个监控的 Filter
     */

    /**
     * 1. 配置管理后台的 Servlet
     *
     * @return
     * @author colg
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("loginUsername", "admin");
        initParameters.put("loginPassword", "123456");
        bean.setInitParameters(initParameters);
        return bean;
    }

    /**
     * 2. 配置一个web监控的 Filter
     *
     * @return
     * @author colg
     */
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("exclusions", "*.js,*.css,/druid/*");
        bean.setInitParameters(initParameters);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}
