package com.epam.alexandrli.paintballshop.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClearCartAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession(false).removeAttribute("cart");
        return new ActionResult(req.getHeader("referer"), true);
    }
}
