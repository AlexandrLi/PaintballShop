package com.epam.alexandrli.paintballshop.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorHandlerServlet extends HttpServlet {

    public static final Logger logger = LoggerFactory.getLogger(ErrorHandlerServlet.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String message = (String) req.getAttribute("javax.servlet.error.message");
        Integer statusCode = (Integer) req.getAttribute("javax.servlet.error.status_code");
        String requestUri = (String) req.getAttribute("javax.servlet.error.request_uri");
        req.setAttribute("statusCode", statusCode);
        if (statusCode == 404 || statusCode == 403) {
            req.setAttribute("errorMessage", message);
            logger.debug("Exception. Status code: {}. Request URI: {}. Current user: {}.", statusCode, requestUri, req.getSession(false).getAttribute("user"));
        } else {
            Exception exception = (Exception) req.getAttribute("javax.servlet.error.exception");
            req.setAttribute("errorMessage", exception.getMessage());
            logger.debug("Exception. Status code: {}. Request URI: {}. Message: {}.", statusCode, requestUri, exception.fillInStackTrace());
        }
        req.getRequestDispatcher("/WEB-INF/jsp/error-page.jsp").forward(req, resp);
    }
}
