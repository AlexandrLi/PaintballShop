package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.User;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.UserService;
import com.epam.alexandrli.paintballshop.service.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.alexandrli.paintballshop.service.Validator.MONEY;

public class RefillBalanceAction implements Action {

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        Validator validator = new Validator();
        String balance = req.getParameter("balance");
        if (!validator.validate(balance, MONEY)) {
            req.setAttribute("flash.balanceError", "true");
            return new ActionResult(req.getHeader("referer"), true);
        }
        UserService userService;
        userService = new UserService();
        User currentUser = (User) req.getSession(false).getAttribute("loggedUser");
        User updatedUser;
        try {
            updatedUser = userService.refillBalance(currentUser.getId(), balance);
        } catch (ServiceException e) {
            throw new ActionException("Could not refill balance", e);
        }
        req.getSession().setAttribute("loggedUser", updatedUser);
        return new ActionResult("user/profile", true);
    }
}
