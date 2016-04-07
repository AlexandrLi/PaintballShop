package com.epam.alexandrli.paintballshop.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class SelectLocaleAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String language = req.getParameter("locale");
        Locale locale = new Locale(language);
        req.getSession(false).setAttribute("locale", locale);
        String referer = req.getHeader("referer");
        String[] split = referer.split("/");
        return new ActionResult(split[split.length - 1],true);
    }
}
