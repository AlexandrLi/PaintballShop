package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.Order;
import com.epam.alexandrli.paintballshop.entity.OrderItem;
import com.epam.alexandrli.paintballshop.service.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.epam.alexandrli.paintballshop.service.Validator.PRODUCT_AMOUNT;

public class RecountCartAction implements Action {
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

            } else {
                orderItems.get(i).setAmount(Integer.parseInt(amount));
            }
        }
        req.setAttribute("flash.errorMap", errorMap);
        req.getSession().setAttribute("cart", cart);
        return new ActionResult(req.getHeader("referer"), true);
    }
}
