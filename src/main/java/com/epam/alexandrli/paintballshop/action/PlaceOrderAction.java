package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.Order;
import com.epam.alexandrli.paintballshop.service.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class PlaceOrderAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Order cart = (Order) req.getSession(false).getAttribute("cart");
        try {
            ShopService shopService = new ShopService();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
