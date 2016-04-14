package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.User;
import com.epam.alexandrli.paintballshop.service.UserService;

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
        User userWithCash = userService.transferCash(currentUser, req.getParameter("cash"));
        req.getSession(false).setAttribute("user", userWithCash);
        return new ActionResult("userprofile",true);
    }
}
