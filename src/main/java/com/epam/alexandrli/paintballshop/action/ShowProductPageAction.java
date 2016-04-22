package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.Product;
import com.epam.alexandrli.paintballshop.service.ProductService;
import com.epam.alexandrli.paintballshop.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowProductPageAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        Product product;
        try {
            ProductService productService = new ProductService();
            product = productService.getFilledProduct(req.getParameter("id"));
        } catch (ServiceException e) {
            throw new ActionException("Could not get product", e);
        }
        req.setAttribute("product", product);
        return new ActionResult("product-page");
    }
}
