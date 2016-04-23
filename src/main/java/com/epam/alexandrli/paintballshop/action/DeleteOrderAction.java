package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteOrderAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            ShopService shopService = new ShopService();
            shopService.deleteOrderById(req.getParameter("id"));
        } catch (ServiceException e) {
            throw new ActionException("Could not delete order", e);
        }
        return new ActionResult(req.getHeader("referer"), true);
    }
}
