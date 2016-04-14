package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.User;
import com.epam.alexandrli.paintballshop.service.UserService;
import org.joda.money.Money;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class TransferCashAction implements Action {

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        UserService userService = null;
        try {
            userService = new UserService();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        User currentUser = (User) req.getSession(false).getAttribute("user");
        Money totalCash = userService.transferCash(currentUser, req.getParameter("cash"));
        currentUser.setCash(totalCash);
        return new ActionResult("userprofile", true);
    }
}
