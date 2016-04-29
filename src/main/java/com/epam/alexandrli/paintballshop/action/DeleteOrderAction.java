package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteOrderAction implements Action {
    public static final Logger logger = LoggerFactory.getLogger(DeleteOrderAction.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            ShopService shopService = new ShopService();
            String orderId = req.getParameter("id");
            shopService.deleteOrderById(orderId);
            logger.info("{} deleted order by id = {}", req.getSession(false).getAttribute("loggedUser"), orderId);
        } catch (ServiceException e) {
            throw new ActionException("Could not delete order", e);
        }
        return new ActionResult(req.getHeader("referer"), true);
    }
}
