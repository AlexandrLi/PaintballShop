package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.Address;
import com.epam.alexandrli.paintballshop.entity.User;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowProfilePageAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        Address address;
        User user = (User) req.getSession(false).getAttribute("loggedUser");
        try {
            UserService userService = new UserService();
            address = userService.getUserAddress(user);
            user.setGender(userService.getUserGender(user));
            req.setAttribute("address", address);
        } catch (ServiceException e) {
            throw new ActionException("Could not show profile page", e);
        }
        return new ActionResult("user-profile");
    }
}
