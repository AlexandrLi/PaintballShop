package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.Order;
import com.epam.alexandrli.paintballshop.entity.User;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.ShopService;
import org.joda.money.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BuyCartAction implements Action {
    public static final Logger logger = LoggerFactory.getLogger(BuyCartAction.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        Order cart = (Order) req.getSession(false).getAttribute("cart");
        User currentUser = (User) req.getSession(false).getAttribute("loggedUser");
        cart.setUser(currentUser);
        if (currentUser.getCash().isLessThan(cart.getPrice())) {
            req.setAttribute("flash.balanceError", "notEnough");
            Money balanceNeeded = cart.getPrice().minus(currentUser.getCash());
            req.setAttribute("flash.balanceNeeded", balanceNeeded);
            logger.info("{} has not enough {} money", currentUser, balanceNeeded.getAmount());
            return new ActionResult("user/profile", true);
        }
        try {
            ShopService shopService = new ShopService();
            User user = shopService.buyCart(cart);
            req.getSession().setAttribute("loggedUser", user);
            req.getSession(false).removeAttribute("cart");
            logger.info("{} has been bought by {} - {}", cart, currentUser);
            return new ActionResult("user/orders", true);
        } catch (ServiceException e) {
            throw new ActionException("Could not place order", e);
        }
    }
}
