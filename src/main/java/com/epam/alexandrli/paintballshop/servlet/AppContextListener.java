package com.epam.alexandrli.paintballshop.servlet;

import com.epam.alexandrli.paintballshop.pool.ConnectionPool;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class AppContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        DataSource pool = ConnectionPool.getInstance();
        servletContext.setAttribute("pool", pool);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        ConnectionPool pool = (ConnectionPool) servletContext.getAttribute("pool");
        pool.close();
    }
}
