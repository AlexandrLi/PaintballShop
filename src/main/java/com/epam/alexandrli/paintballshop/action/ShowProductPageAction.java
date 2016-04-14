package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.Product;
import com.epam.alexandrli.paintballshop.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ShowProductPageAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Product product = null;
        try {
            ProductService productService = new ProductService();
            product = productService.getAllProduct(req.getParameter("id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("product", product);
        return new ActionResult("productpage");
    }
}
