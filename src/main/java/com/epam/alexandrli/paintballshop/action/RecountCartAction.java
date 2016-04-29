package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.Validator;
import com.epam.alexandrli.paintballshop.entity.Order;
import com.epam.alexandrli.paintballshop.entity.OrderItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.epam.alexandrli.paintballshop.Validator.PRODUCT_AMOUNT;

public class RecountCartAction implements Action {
    public static final Logger logger = LoggerFactory.getLogger(RecountCartAction.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        Validator validator = new Validator();
        Order cart = (Order) req.getSession(false).getAttribute("cart");
        List<OrderItem> orderItems = cart.getOrderItems();
        Map<Integer, String> errorMap = new HashMap<>();
        for (int i = 0; i < orderItems.size(); i++) {
            String amount = req.getParameter("item" + i);
            if (!validator.validate(amount, PRODUCT_AMOUNT)) {
                errorMap.put(i, "true");
                logger.info("Invalid product amount format - {}", amount);
            } else {
                orderItems.get(i).setAmount(Integer.parseInt(amount));
                logger.info("{} amount set to {}", orderItems.get(i), amount);
            }
        }
        req.setAttribute("flash.errorMap", errorMap);
        req.getSession().setAttribute("cart", cart);
        return new ActionResult(req.getHeader("referer"), true);
    }
}
