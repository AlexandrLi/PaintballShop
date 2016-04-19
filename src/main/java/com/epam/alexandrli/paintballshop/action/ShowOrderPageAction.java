package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.Order;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowOrderPageAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        String orderId = req.getParameter("id");
        Order order;
        try {
            ShopService shopService = new ShopService();
            order = shopService.getOrder(Integer.parseInt(orderId));
        } catch (ServiceException e) {
            throw new ActionException("Could not get order", e);
        }
        req.setAttribute("order", order);
        return new ActionResult("order");
    }
}
