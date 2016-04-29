package com.epam.alexandrli.paintballshop.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "0EncodingFilter", urlPatterns = "/do/*")
public class EncodingFilter implements Filter {
    public static final Logger logger = LoggerFactory.getLogger(EncodingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        logger.trace("Character encoding set to UTF-8");
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
    }
}