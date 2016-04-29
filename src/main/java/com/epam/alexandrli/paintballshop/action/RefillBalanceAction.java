package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.Validator;
import com.epam.alexandrli.paintballshop.entity.User;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.alexandrli.paintballshop.Validator.MONEY;

public class RefillBalanceAction implements Action {
    public static final Logger logger = LoggerFactory.getLogger(RefillBalanceAction.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        Validator validator = new Validator();
        String balance = req.getParameter("balance");
        if (!validator.validate(balance, MONEY)) {
            req.setAttribute("flash.balanceError", "true");
            logger.info("Invalid money format - {}", balance);
            return new ActionResult(req.getHeader("referer"), true);
        }
        UserService userService;
        userService = new UserService();
        User currentUser = (User) req.getSession(false).getAttribute("loggedUser");
        User updatedUser;
        try {
            updatedUser = userService.refillBalance(currentUser.getId(), balance);
            req.getSession().setAttribute("loggedUser", updatedUser);
            logger.info("{} refill balance for {}", updatedUser, balance);
            return new ActionResult("user/profile", true);
        } catch (ServiceException e) {
            throw new ActionException("Could not refill balance", e);
        }
    }
}
