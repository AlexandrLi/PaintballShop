package com.epam.alexandrli.paintballshop.servlet;

import com.epam.alexandrli.paintballshop.action.Action;
import com.epam.alexandrli.paintballshop.action.ActionException;
import com.epam.alexandrli.paintballshop.action.ActionFactory;
import com.epam.alexandrli.paintballshop.action.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FrontControllerServlet", urlPatterns = "/do/*")
@MultipartConfig(maxFileSize = 20 * 1024 * 1024)
public class FrontControllerServlet extends HttpServlet {
    public static final Logger logger = LoggerFactory.getLogger(FrontControllerServlet.class);
    private ActionFactory actionFactory;

    @Override
    public void init() throws ServletException {
        actionFactory = new ActionFactory();
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionName = req.getMethod() + req.getPathInfo();
        Action action = actionFactory.getAction(actionName);
        if (action == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Not found");
            return;
        }
        logger.debug("{} init by key: '{}'", action.getClass().getSimpleName(), actionName);
        ActionResult result;
        try {
            result = action.execute(req, resp);
            logger.debug("Action result view: {}. Redirect: {}", result.getView(), result.isRedirect());
        } catch (ActionException e) {
            throw new ServletException("Could not perform action", e);
        }
        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        resp.setHeader("Pragma", "No-cache");
        resp.setDateHeader("Expires", 0);
        checkActionResult(result, req, resp);
    }

    private void checkActionResult(ActionResult result, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (result.isRedirect()) {
            String location = result.getView();
            if (!location.startsWith("http")) {
                location = req.getContextPath() + "/do/" + result.getView();
            }
            resp.sendRedirect(location);
        } else {
            String path = "/WEB-INF/jsp/" + result.getView() + ".jsp";
            req.getRequestDispatcher(path).forward(req, resp);
        }
    }
}

