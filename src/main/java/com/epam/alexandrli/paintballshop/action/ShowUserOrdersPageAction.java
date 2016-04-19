package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.Order;
import com.epam.alexandrli.paintballshop.entity.User;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowUserOrdersPageAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        User user = (User) req.getSession(false).getAttribute("user");
        List<Order> orders;
        try {
            UserService userService = new UserService();
            orders = userService.getUserOrders(user.getId());
        } catch (ServiceException e) {
            throw new ActionException("Could not get orders", e);
        }
        req.setAttribute("orders", orders);
        return new ActionResult("userorders");
    }
}
