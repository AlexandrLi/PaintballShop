package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddCartDescriptionAction implements Action {
    public static final Logger logger = LoggerFactory.getLogger(AddCartDescriptionAction.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Order cart = (Order) req.getSession(false).getAttribute("cart");
        String description = req.getParameter("description");
        cart.setDescription(description);
        req.getSession(false).setAttribute("cart", cart);
        logger.info("Cart description added - {}", description);
        return new ActionResult("cart", true);
    }
}
