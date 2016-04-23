package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.Order;
import com.epam.alexandrli.paintballshop.entity.OrderStatus;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowManageOrdersPageAction implements Action {
    public static final int FIRST_PAGE = 1;
    public static final int PAGE_SIZE = 2;

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        List<Order> orders;
        List<OrderStatus> statuses;
        String page = req.getParameter("page");
        int ordersCount;
        try {
            if (page == null) {
                page = String.valueOf(FIRST_PAGE);
            }
            ShopService shopService = new ShopService();
            orders = shopService.getAllOrdersOnPage(Integer.parseInt(page), PAGE_SIZE);
            ordersCount = shopService.getOrdersCount();
            statuses = shopService.getAllOrderStatuses();
        } catch (ServiceException e) {
            throw new ActionException("Could not show manage orders page", e);
        }
        int pageCount;
        if (ordersCount % PAGE_SIZE == 0) {
            pageCount = ordersCount / PAGE_SIZE;
        } else {
            pageCount = ordersCount / PAGE_SIZE + 1;
        }
        req.setAttribute("orders", orders);
        req.setAttribute("statuses", statuses);
        req.setAttribute("pagesCount", pageCount);
        req.setAttribute("page", page);
        return new ActionResult("orders");

    }
}
