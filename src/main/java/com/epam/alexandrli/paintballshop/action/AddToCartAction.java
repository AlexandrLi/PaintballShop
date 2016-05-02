package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.validator.Validator;
import com.epam.alexandrli.paintballshop.entity.Order;
import com.epam.alexandrli.paintballshop.entity.OrderItem;
import com.epam.alexandrli.paintballshop.entity.Product;
import com.epam.alexandrli.paintballshop.service.ProductService;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.alexandrli.paintballshop.validator.Validator.PRODUCT_AMOUNT;

public class AddToCartAction implements Action {
    public static final Logger logger = LoggerFactory.getLogger(AddToCartAction.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        Validator validator = new Validator();
        String amount = req.getParameter("amount");
        if (!validator.validate(amount, PRODUCT_AMOUNT)) {
            req.setAttribute("flash.amountError", "true");
            logger.info("Invalid product amount format - {}", amount);
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
                logger.info("Product amount in cart increased by {}", amount);
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
            req.getSession().setAttribute("cart", cart);
            logger.info("Product added in cart - {} quantity: {}", product, amount);
            return new ActionResult(req.getHeader("referer"), true);
        } catch (ServiceException e) {
            throw new ActionException("Could not add product to cart", e);
        }
    }
}
