package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddCartDescriptionAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Order cart = (Order) req.getSession(false).getAttribute("cart");
        cart.setDescription(req.getParameter("description"));
        req.getSession(false).setAttribute("cart", cart);
        return new ActionResult("userorders", true);
    }
}
