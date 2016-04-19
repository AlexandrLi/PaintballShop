package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.User;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TransferCashAction implements Action {

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        UserService userService;
        userService = new UserService();
        User currentUser = (User) req.getSession(false).getAttribute("user");
        User userWithCash;
        try {
            userWithCash = userService.transferCash(currentUser, req.getParameter("cash"));
        } catch (ServiceException e) {
            throw new ActionException("Could not transfer cash", e);
        }
        req.getSession(false).setAttribute("user", userWithCash);
        return new ActionResult("userprofile", true);
    }
}
