package com.epam.alexandrli.paintballshop.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutAction implements Action {
    private ActionResult home = new ActionResult("home", true);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession(false).removeAttribute("user");
        return home;
    }
}
