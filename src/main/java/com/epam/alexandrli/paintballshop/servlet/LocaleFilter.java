package com.epam.alexandrli.paintballshop.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@WebFilter(filterName = "1LocaleFilter", urlPatterns = "/do/*")
public class LocaleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        if (req instanceof HttpServletRequest && resp instanceof HttpServletResponse) {
            Cookie[] cookies = ((HttpServletRequest) req).getCookies();
            for (Cookie cookie : cookies) {
                if ("locale".equals(cookie.getName())) {
                    ((HttpServletRequest) req).getSession().setAttribute("locale", new Locale(cookie.getValue()));
                }
            }
            if (((HttpServletRequest) req).getSession().getAttribute("locale") == null) {
                ((HttpServletRequest) req).getSession().setAttribute("locale", req.getLocale());
            }
        }
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}