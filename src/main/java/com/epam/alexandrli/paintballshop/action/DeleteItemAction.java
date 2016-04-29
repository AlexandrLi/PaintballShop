package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteItemAction implements Action {
    public static final Logger logger = LoggerFactory.getLogger(DeleteItemAction.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Order cart = (Order) req.getSession(false).getAttribute("cart");
        String itemRowNumber = req.getParameter("item");
        int itemIndex = Integer.parseInt(itemRowNumber);
        logger.info("{} deleted from cart", cart.getOrderItems().get(itemIndex));
        cart.getOrderItems().remove(itemIndex);
        req.getSession(false).setAttribute("cart", cart);
        return new ActionResult(req.getHeader("referer"), true);
    }
}
