package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditOrderStatusAction implements Action {
    public static final Logger logger = LoggerFactory.getLogger(EditOrderStatusAction.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {

        ShopService shopService = new ShopService();
        try {
            String orderId = req.getParameter("orderId");
            String statusId = req.getParameter("statusId");
            shopService.updateOrderStatus(orderId, statusId);
            logger.info("{} changed order (id = {}) statusId to {}", req.getSession(false).getAttribute("loggedUser"), orderId, statusId);
        } catch (ServiceException e) {
            throw new ActionException("Could not edit order status", e);
        }
        return new ActionResult(req.getHeader("referer"), true);
    }
}
