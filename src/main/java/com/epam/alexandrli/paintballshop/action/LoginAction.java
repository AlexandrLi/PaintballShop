package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.User;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction implements Action {
    public static final Logger logger = LoggerFactory.getLogger(LoginAction.class);

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
            logger.info("{} logged in", user);
            return new ActionResult("home", true);
        } else {
            req.setAttribute("loginError", "Invalid Login or Password");
            logger.info("Wrong login ({}) or password ({})", email, password);
            return new ActionResult("login");
        }
    }
}
