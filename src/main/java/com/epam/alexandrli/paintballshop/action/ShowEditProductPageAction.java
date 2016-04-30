package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.Product;
import com.epam.alexandrli.paintballshop.entity.ProductType;
import com.epam.alexandrli.paintballshop.service.ProductService;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowEditProductPageAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        ShopService shopService = new ShopService();
        ProductService productService = new ProductService();
        try {
            Product product = productService.getFilledProduct(req.getParameter("id"));
            req.setAttribute("product", product);
            List<ProductType> types = shopService.getFilledProductTypes();
            req.setAttribute("types", types);
        } catch (ServiceException e) {
            throw new ActionException("Could not show product edit page", e);
        }
        return new ActionResult("edit-product");
    }
}
