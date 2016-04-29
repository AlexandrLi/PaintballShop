package com.epam.alexandrli.paintballshop.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClearCartAction implements Action {
    public static final Logger logger = LoggerFactory.getLogger(ClearCartAction.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession(false).removeAttribute("cart");
        logger.info("{} cleared cart", req.getSession(false).getAttribute("loggedUser"));
        return new ActionResult(req.getHeader("referer"), true);
    }
}
