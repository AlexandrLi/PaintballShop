package com.epam.alexandrli.paintballshop.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebFilter(filterName = "FlashScopeFilter", urlPatterns = "/do/*")
public class FlashScopeFilter implements Filter {
    private static final String FLASH_SESSION_KEY = "FLASH_SESSION_KEY";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        doFilter((HttpServletRequest) req, (HttpServletResponse) resp, filterChain);
    }

    @SuppressWarnings("unchecked")
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Map<String, Object> flashParams = (Map<String, Object>) session.getAttribute(FLASH_SESSION_KEY);
            if (flashParams != null) {
                for (Map.Entry<String, Object> flashEntry : flashParams.entrySet()) {
                    request.setAttribute(flashEntry.getKey(), flashEntry.getValue());
                }
                session.removeAttribute(FLASH_SESSION_KEY);
            }
        }
        chain.doFilter(request, response);

        Map<String, Object> flashParams = new HashMap();
        Enumeration attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String paramName = (String) attributeNames.nextElement();
            if (paramName.startsWith("flash.")) {
                Object value = request.getAttribute(paramName);
                paramName = paramName.substring(6, paramName.length());
                flashParams.put(paramName, value);
            }
            if (flashParams.size() > 0) {
                session = request.getSession(false);
                session.setAttribute(FLASH_SESSION_KEY, flashParams);
            }
        }
    }

    @Override
    public void destroy() {

    }
}