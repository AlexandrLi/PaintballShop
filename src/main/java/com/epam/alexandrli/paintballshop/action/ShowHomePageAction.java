package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.Product;
import com.epam.alexandrli.paintballshop.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class ShowHomePageAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Product> featuredList = null;
        try {
            ProductService productService = new ProductService();
            featuredList = productService.getFeaturedProducts();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("featuredList", featuredList);
        return new ActionResult("home");
    }
}
