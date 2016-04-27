package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.Order;
import com.epam.alexandrli.paintballshop.entity.User;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowUserOrdersPageAction implements Action {
    public final String FIRST_PAGE = "1";
    public final String DEFAULT_PAGE_SIZE = "3";

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        User user = (User) req.getSession(false).getAttribute("loggedUser");
        List<Order> orders;
        String page = req.getParameter("page");
        if (page == null) {
            page = FIRST_PAGE;
        }
        String pageSize = DEFAULT_PAGE_SIZE;
        if (req.getParameter("pageSize") != null) {
            pageSize = req.getParameter("pageSize");
        }
        int pageInt = Integer.parseInt(page);
        int pageSizeInt = Integer.parseInt(pageSize);
        List<Order> ordersOnPage;
        try {
            UserService userService = new UserService();
            orders = userService.getUserOrders(user.getId());
            if (orders.size() < pageInt * pageSizeInt) {
                ordersOnPage = orders.subList(((pageInt - 1) * pageSizeInt), orders.size());
            } else {
                ordersOnPage = orders.subList(((pageInt - 1) * pageSizeInt), pageInt * pageSizeInt);
            }
        } catch (ServiceException e) {
            throw new ActionException("Could not get orders", e);
        }
        int pageCount;
        if (orders.size() % Integer.parseInt(pageSize) == 0) {
            pageCount = orders.size() / Integer.parseInt(pageSize);
        } else {
            pageCount = orders.size() / Integer.parseInt(pageSize) + 1;
        }
        req.setAttribute("orders", ordersOnPage);
        req.setAttribute("pagesCount", pageCount);
        req.setAttribute("page", page);
        req.setAttribute("pageSize", pageSize);
        return new ActionResult("user-orders");
    }
}
