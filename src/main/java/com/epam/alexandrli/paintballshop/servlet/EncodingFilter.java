package com.epam.alexandrli.paintballshop.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "0EncodingFilter", urlPatterns = "/do/*")
public class EncodingFilter implements Filter {

    private String encoding = "utf-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        req.setCharacterEncoding(encoding);
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}