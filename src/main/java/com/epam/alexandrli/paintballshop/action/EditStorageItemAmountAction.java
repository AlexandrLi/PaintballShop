package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditStorageItemAmountAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {

        ShopService shopService = new ShopService();
        try {
            shopService.updateStorageItemAmount(req.getParameter("itemId"), req.getParameter("amount"));
        } catch (ServiceException e) {
            throw new ActionException("Could not edit storage item amount", e);
        }
        return new ActionResult(req.getHeader("referer"), true);
    }
}
