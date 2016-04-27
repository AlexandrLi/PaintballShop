package com.epam.alexandrli.paintballshop.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetPageSizeAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        String pageSize = req.getParameter("pageSize");
        Cookie cookie = new Cookie("pageSize", pageSize);
        cookie.setMaxAge(24 * 60 * 60);
        resp.addCookie(cookie);
        return new ActionResult(req.getHeader("referer"), true);
    }
}
