package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.Order;
import com.epam.alexandrli.paintballshop.entity.OrderItem;
import com.epam.alexandrli.paintballshop.entity.Product;
import com.epam.alexandrli.paintballshop.service.ProductService;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.alexandrli.paintballshop.service.Validator.PRODUCT_AMOUNT;

public class AddToCartAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        Validator validator = new Validator();
        String amount = req.getParameter("amount");
        if (!validator.validate(amount, PRODUCT_AMOUNT)) {
            req.setAttribute("flash.amountError", "true");
            return new ActionResult(req.getHeader("referer"), true);
        }

        Order cart = (Order) req.getSession(false).getAttribute("cart");
        if (cart == null) {
            cart = new Order();
        }
        String productId = req.getParameter("product");
        Integer amountInt = Integer.parseInt(amount);
        for (OrderItem orderItem : cart.getOrderItems()) {
            if (orderItem.getProduct().getId() == Integer.parseInt(productId)) {
                orderItem.setAmount(orderItem.getAmount() + amountInt);
                req.getSession().setAttribute("cart", cart);
                return new ActionResult(req.getHeader("referer"), true);
            }
        }
        OrderItem orderItem = new OrderItem();
        orderItem.setAmount(amountInt);
        try {
            ProductService productService = new ProductService();
            Product product = productService.getProductById(productId);
            orderItem.setProduct(product);
            cart.addProduct(orderItem);
        } catch (ServiceException e) {
            throw new ActionException("Could not add product to cart", e);
        }
        req.getSession().setAttribute("cart", cart);
        return new ActionResult(req.getHeader("referer"), true);
    }
}
