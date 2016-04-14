package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.Product;
import com.epam.alexandrli.paintballshop.entity.ProductType;
import com.epam.alexandrli.paintballshop.service.ProductService;
import com.epam.alexandrli.paintballshop.service.ProductTypeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class ShowHomePageAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Product> featuredList = null;
        List<ProductType> productTypes = null;
        try {
            ProductService productService = new ProductService();
            ProductTypeService productTypeService = new ProductTypeService();
            featuredList = productService.getFeaturedProducts();
            productTypes = productTypeService.getAllProductTypes();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("featuredList", featuredList);
        req.getSession(false).setAttribute("productTypes", productTypes);
        return new ActionResult("home");
    }
}
