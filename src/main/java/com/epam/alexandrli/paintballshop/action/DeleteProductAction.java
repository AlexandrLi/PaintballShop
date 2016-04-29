package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProductAction implements Action {
    public static final Logger logger = LoggerFactory.getLogger(DeleteProductAction.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            ShopService shopService = new ShopService();
            String productId = req.getParameter("id");
            shopService.deleteProductById(productId);
            logger.info("{} deleted product by id = {}", req.getSession(false).getAttribute("loggedUser"), productId);
        } catch (ServiceException e) {
            throw new ActionException("Could not delete product", e);
        }
        return new ActionResult(req.getHeader("referer"), true);
    }
}
