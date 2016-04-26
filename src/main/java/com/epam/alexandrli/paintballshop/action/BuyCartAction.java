package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.Order;
import com.epam.alexandrli.paintballshop.entity.User;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BuyCartAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        Order cart = (Order) req.getSession(false).getAttribute("cart");
        User currentUser = (User) req.getSession(false).getAttribute("loggedUser");
        cart.setUser(currentUser);
        if (currentUser.getCash().isLessThan(cart.getPrice())) {
            req.setAttribute("flash.balanceError", "true");
            req.setAttribute("flash.balanceNeeded", cart.getPrice().minus(currentUser.getCash()));
            return new ActionResult("user/profile", true);
        }
        try {
            ShopService shopService = new ShopService();
            User user = shopService.buyCart(cart);
            req.getSession(false).setAttribute("loggedUser", user);
            req.getSession(false).removeAttribute("cart");
            return new ActionResult("user/orders", true);
        } catch (ServiceException e) {
            throw new ActionException("Could not place order", e);
        }
    }
}
