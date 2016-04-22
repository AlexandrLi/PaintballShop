package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.Order;
import com.epam.alexandrli.paintballshop.entity.OrderItem;
import com.epam.alexandrli.paintballshop.entity.Product;
import com.epam.alexandrli.paintballshop.entity.User;
import com.epam.alexandrli.paintballshop.service.ProductService;
import com.epam.alexandrli.paintballshop.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddToCartAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        User currentUser = (User) req.getSession(false).getAttribute("loggedUser");
        if (currentUser == null) {
            return new ActionResult("login");
        }
        Order cart = (Order) req.getSession(false).getAttribute("cart");
        if (cart == null) {
            cart = new Order();
            cart.setUser(currentUser);
        }
        OrderItem orderItem = new OrderItem();
        orderItem.setAmount(Integer.parseInt(req.getParameter("amount")));
        try {
            ProductService productService = new ProductService();
            Product product = productService.getProductById(req.getParameter("product"));
            orderItem.setProduct(product);
            cart.addProduct(orderItem);
        } catch (ServiceException e) {
            throw new ActionException("Could not add product to cart", e);
        }
        req.getSession(false).setAttribute("cart", cart);
        return new ActionResult(req.getHeader("referer"), true);
    }
}
