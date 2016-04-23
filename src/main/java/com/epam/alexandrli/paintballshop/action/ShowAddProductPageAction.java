package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.ProductType;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowAddProductPageAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        List<ProductType> types;
        try {
            ShopService shopService = new ShopService();
            types = shopService.getAllProductTypes();
        } catch (ServiceException e) {
            throw new ActionException("Could not show add product page", e);
        }
        req.getSession(false).setAttribute("types", types);
        return new ActionResult("add-product");
    }

}
