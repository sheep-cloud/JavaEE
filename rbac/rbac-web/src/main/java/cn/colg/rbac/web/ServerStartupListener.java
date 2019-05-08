package cn.colg.rbac.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import lombok.extern.slf4j.Slf4j;

/**
 * 监听器
 *
 * @author colg
 */
@Slf4j
public class ServerStartupListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 将 web 应用名称（路径）保存到application范围中
        ServletContext application = sce.getServletContext();
        String contextPath = application.getContextPath();
        log.info("APP_PATH : {}", contextPath);
        application.setAttribute("APP_PATH", contextPath);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
