package com.epam.alexandrli.paintballshop.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.util.Locale;

public class SelectLocaleAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String language = req.getParameter("locale");
        Config.set(req.getSession(), Config.FMT_LOCALE, new Locale(language));
        Cookie cookie = new Cookie("locale", language);
        cookie.setMaxAge(24 * 60 * 60);
        resp.addCookie(cookie);
        String referer = req.getHeader("referer");
        return new ActionResult(referer, true);
    }
}
