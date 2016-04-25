package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.Order;
import com.epam.alexandrli.paintballshop.entity.User;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PlaceOrderAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        Order cart = (Order) req.getSession(false).getAttribute("cart");
        User user;
        try {
            ShopService shopService = new ShopService();
            user = shopService.placeOrder(cart);

        } catch (ServiceException e) {
            throw new ActionException("Could not place order", e);
        }
        req.getSession(false).setAttribute("loggedUser", user);
        req.getSession(false).removeAttribute("cart");
        return new ActionResult("user/orders", true);
    }
}
