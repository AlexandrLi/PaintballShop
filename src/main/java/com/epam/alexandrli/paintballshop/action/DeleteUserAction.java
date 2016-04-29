package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.User;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserAction implements Action {
    public static final Logger logger = LoggerFactory.getLogger(DeleteUserAction.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        User loggedUser = (User) req.getSession(false).getAttribute("loggedUser");
        String userId = req.getParameter("id");
        if (loggedUser.getId() == Integer.parseInt(userId)) {
            req.setAttribute("flash.deleteError", "true");
            logger.info("{} tried to delete himself when logged in", req.getSession(false).getAttribute("loggedUser"));
            return new ActionResult(req.getHeader("referer"), true);
        }
        try {
            ShopService shopService = new ShopService();
            shopService.deleteUserById(userId);
            logger.info("{} deleted user by id = {}", req.getSession(false).getAttribute("loggedUser"), userId);
        } catch (ServiceException e) {
            throw new ActionException("Could not delete user", e);
        }
        return new ActionResult(req.getHeader("referer"), true);
    }
}
