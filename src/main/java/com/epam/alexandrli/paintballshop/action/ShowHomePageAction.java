package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.Product;
import com.epam.alexandrli.paintballshop.entity.ProductType;
import com.epam.alexandrli.paintballshop.service.ProductService;
import com.epam.alexandrli.paintballshop.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowHomePageAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        List<Product> featuredList;
        List<ProductType> productTypes;
        try {
            ProductService productService = new ProductService();
            featuredList = productService.getFeaturedProducts();
            productTypes = productService.getAllProductTypes();
        } catch (ServiceException e) {
            throw new ActionException("Could not fill content at homepage", e);
        }
        req.setAttribute("featuredList", featuredList);
        req.getSession(false).setAttribute("productTypes", productTypes);
        return new ActionResult("home");
    }
}
