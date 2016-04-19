package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.Product;
import com.epam.alexandrli.paintballshop.service.ProductService;
import com.epam.alexandrli.paintballshop.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowCatalogPageAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {

        List<Product> products;
        try {
            ProductService productService = new ProductService();
            products = productService.getAllProductsByType(req.getParameter("type"));
        } catch (ServiceException e) {
            throw new ActionException("Could not show catalog page", e);
        }
        req.setAttribute("products", products);
        return new ActionResult("typecatalog");
    }
}
