package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditOrderStatusAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {

        ShopService shopService = new ShopService();
        try {
            shopService.updateOrderStatus(req.getParameter("orderId"), req.getParameter("statusId"));
        } catch (ServiceException e) {
            throw new ActionException("Could not edit order status", e);
        }
        return new ActionResult(req.getHeader("referer"), true);
    }
}
