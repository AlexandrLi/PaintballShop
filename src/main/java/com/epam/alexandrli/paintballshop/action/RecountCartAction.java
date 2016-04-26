package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.Order;
import com.epam.alexandrli.paintballshop.entity.OrderItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class RecountCartAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        Order cart = (Order) req.getSession(false).getAttribute("cart");
        List<OrderItem> orderItems = cart.getOrderItems();
        for (int i = 0; i < orderItems.size(); i++) {
            orderItems.get(i).setAmount(Integer.parseInt(req.getParameter("item" + i)));
        }
        req.getSession(false).setAttribute("cart", cart);
        return new ActionResult("cart", true);
    }
}
