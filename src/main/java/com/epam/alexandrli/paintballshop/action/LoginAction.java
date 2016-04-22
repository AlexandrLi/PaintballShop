package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.User;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction implements Action {
    private ActionResult home = new ActionResult("home", true);
    private ActionResult loginAgain = new ActionResult("login");


    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user;
        try {
            UserService userService = new UserService();
            user = userService.performUserLogin(email, password);
        } catch (ServiceException e) {
            throw new ActionException("Could not login", e);
        }
        if (user != null) {
            req.getSession(false).setAttribute("loggedUser", user);
            return home;
        } else {
            req.setAttribute("loginError", "Invalid Login or Password");
            return loginAgain;
        }
    }
}
