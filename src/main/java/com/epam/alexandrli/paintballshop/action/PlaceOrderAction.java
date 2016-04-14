package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.Order;
import com.epam.alexandrli.paintballshop.entity.User;
import com.epam.alexandrli.paintballshop.service.ShopService;
import com.epam.alexandrli.paintballshop.service.ShopServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class PlaceOrderAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Order cart = (Order) req.getSession(false).getAttribute("cart");
        User user = (User) req.getSession(false).getAttribute("user");
        try {
            ShopService shopService = new ShopService();
            user = shopService.placeOrder(cart);

        } catch (SQLException | ShopServiceException e) {
            e.printStackTrace();
        }
        req.getSession(false).setAttribute("user", user);
        req.getSession(false).removeAttribute("cart");
        return new ActionResult("userorders", true);
    }
}
