package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteStorageItemAction implements Action {
    public static final Logger logger = LoggerFactory.getLogger(DeleteStorageItemAction.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            ShopService shopService = new ShopService();
            String storageItemId = req.getParameter("id");
            shopService.deleteStorageItemById(storageItemId);
            logger.info("{} deleted storage item by id = {}", req.getSession(false).getAttribute("loggedUser"), storageItemId);
        } catch (ServiceException e) {
            throw new ActionException("Could not delete storage item", e);
        }
        return new ActionResult(req.getHeader("referer"), true);
    }
}
