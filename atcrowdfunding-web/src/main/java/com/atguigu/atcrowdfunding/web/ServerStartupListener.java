package com.atguigu.atcrowdfunding.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServerStartupListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext application = servletContextEvent.getServletContext();
        String path = application.getContextPath();
        application.setAttribute("APP_PATH", path);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
