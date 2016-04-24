package com.epam.alexandrli.paintballshop.servlet;

import com.epam.alexandrli.paintballshop.action.Action;
import com.epam.alexandrli.paintballshop.action.ActionException;
import com.epam.alexandrli.paintballshop.action.ActionFactory;
import com.epam.alexandrli.paintballshop.action.ActionResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FrontControllerServlet", urlPatterns = "/do/*")
@MultipartConfig(maxFileSize = 5 * 1024 * 1024)
public class FrontControllerServlet extends HttpServlet {
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
        ActionResult result;
        try {
            result = action.execute(req, resp);
        } catch (ActionException e) {
            throw new ServletException("Could not perform action", e);
        }
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

