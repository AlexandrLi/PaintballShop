package com.epam.alexandrli.paintballshop.servlet;

import com.epam.alexandrli.paintballshop.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(filterName = "SecurityFilter", urlPatterns = "/do/*")
public class SecurityFilter implements Filter {
    List<String> guestAccessList;
    List<String> userAccessList;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        guestAccessList = new ArrayList<>();
        guestAccessList.add("/home");
        guestAccessList.add("/product");
        guestAccessList.add("/catalog");
        guestAccessList.add("/register");
        guestAccessList.add("/login");
        guestAccessList.add("/locale");
        userAccessList = new ArrayList<>(guestAccessList);

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        doFilter((HttpServletRequest) req, (HttpServletResponse) resp, filterChain);
    }

    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        User user = (User) request.getSession(false).getAttribute("loggedUser");
        String pathInfo = request.getPathInfo();
        if (user == null) {
            if (!guestAccessList.contains(pathInfo)) {
                response.sendRedirect(request.getContextPath() + "/do/login");
                return;
            }
        } else if (user.getRole().equals(User.Role.user)) {
            if (pathInfo.startsWith("/manage") || pathInfo.startsWith("/add") || pathInfo.startsWith("/delete") || pathInfo.startsWith("edit")) {
                response.sendError(403,"Access denied");
//                response.sendRedirect(request.getContextPath() + "/do/forbidden");
                return;
            }
        }
        chain.doFilter(request, response);
    }


    @Override
    public void destroy() {

    }
}