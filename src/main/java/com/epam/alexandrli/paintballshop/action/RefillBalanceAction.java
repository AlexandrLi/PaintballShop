package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.User;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RefillBalanceAction implements Action {

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        UserService userService;
        userService = new UserService();
        User currentUser = (User) req.getSession(false).getAttribute("loggedUser");
        User updatedUser;
        try {
            updatedUser = userService.refillBalance(currentUser, req.getParameter("balance"));
        } catch (ServiceException e) {
            throw new ActionException("Could not refill balance", e);
        }
        req.getSession(false).setAttribute("loggedUser", updatedUser);
        return new ActionResult("user/profile", true);
    }
}
