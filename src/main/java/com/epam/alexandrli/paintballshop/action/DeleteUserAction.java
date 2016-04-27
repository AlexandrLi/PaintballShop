package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.User;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        User loggedUser = (User) req.getSession(false).getAttribute("loggedUser");
        String userId = req.getParameter("id");
        if (loggedUser.getId() == Integer.parseInt(userId)) {
            req.setAttribute("flash.deleteError", "true");
            return new ActionResult(req.getHeader("referer"), true);
        }
        try {
            ShopService shopService = new ShopService();
            shopService.deleteUserById(userId);
        } catch (ServiceException e) {
            throw new ActionException("Could not delete user", e);
        }
        return new ActionResult(req.getHeader("referer"), true);
    }
}
