package com.epam.alexandrli.paintballshop.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutAction implements Action {
    public static final Logger logger = LoggerFactory.getLogger(LogoutAction.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("{} logged out", req.getSession(false).getAttribute("loggedUser"));
        req.getSession(false).removeAttribute("loggedUser");
        return new ActionResult("home", true);
    }
}
