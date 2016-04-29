package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.Order;
import com.epam.alexandrli.paintballshop.entity.OrderStatus;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowManageOrdersPageAction implements Action {
    public static final Logger logger = LoggerFactory.getLogger(ShowManageOrdersPageAction.class);
    public final String FIRST_PAGE = "1";
    public final String DEFAULT_PAGE_SIZE = "3";

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        List<Order> orders;
        List<OrderStatus> statuses;
        String page = req.getParameter("page");
        if (page == null) {
            page = FIRST_PAGE;
        }
        String pageSize = req.getParameter("pageSize");
        if (req.getParameter("pageSize") == null) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        int ordersCount;
        try {
            ShopService shopService = new ShopService();
            orders = shopService.getAllOrdersOnPage(Integer.parseInt(page), Integer.parseInt(pageSize));
            ordersCount = shopService.getOrdersCount();
            statuses = shopService.getAllOrderStatuses();
        } catch (ServiceException e) {
            throw new ActionException("Could not show manage orders page", e);
        }
        int pageCount;
        if (ordersCount % Integer.parseInt(pageSize) == 0) {
            pageCount = ordersCount / Integer.parseInt(pageSize);
        } else {
            pageCount = ordersCount / Integer.parseInt(pageSize) + 1;
        }
        req.setAttribute("orders", orders);
        req.setAttribute("statuses", statuses);
        req.setAttribute("pagesCount", pageCount);
        req.setAttribute("page", page);
        req.setAttribute("pageSize", pageSize);
        logger.info("Page number: {}. Page size: {}. Pages count: {}", page, pageSize, pageCount);
        return new ActionResult("orders");

    }
}
