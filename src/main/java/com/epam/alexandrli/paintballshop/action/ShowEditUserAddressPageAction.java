package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.Address;
import com.epam.alexandrli.paintballshop.entity.User;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowEditUserAddressPageAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        UserService userService = new UserService();
        try {
            Address address = userService.getUserAddress((User) req.getSession(false).getAttribute("loggedUser"));
            req.getSession(false).setAttribute("address", address);
        } catch (ServiceException e) {
            throw new ActionException("Could not show user address edit page", e);
        }
        return new ActionResult("edit-user-address");
    }
}
