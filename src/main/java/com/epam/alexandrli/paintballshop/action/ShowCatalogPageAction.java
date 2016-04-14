package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.Product;
import com.epam.alexandrli.paintballshop.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class ShowCatalogPageAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {

        List<Product> products = null;
        try {
            ProductService productService = new ProductService();
            products = productService.getAllProductsByType(req.getParameter("type"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("products", products);
        return new ActionResult("typecatalog");
    }
}
