package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.User;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowManageUsersPageAction implements Action {
    public final String FIRST_PAGE = "1";
    public final String DEFAULT_PAGE_SIZE = "3";

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        ShopService shopService = new ShopService();
        List<User> users;
        String page = req.getParameter("page");
        if (page == null) {
            page = FIRST_PAGE;
        }
        String pageSize = DEFAULT_PAGE_SIZE;
        if (req.getParameter("pageSize") != null) {
            pageSize = req.getParameter("pageSize");
        }
        int usersCount;
        try {
            users = shopService.getAllUsersOnPage(Integer.parseInt(page), Integer.parseInt(pageSize));
            usersCount = shopService.getUsersCount();
        } catch (ServiceException e) {
            throw new ActionException("Could not show manage users page", e);
        }
        int pageCount;
        if (usersCount % Integer.parseInt(pageSize) == 0) {
            pageCount = usersCount / Integer.parseInt(pageSize);
        } else {
            pageCount = usersCount / Integer.parseInt(pageSize) + 1;
        }
        req.setAttribute("users", users);
        req.setAttribute("pagesCount", pageCount);
        req.setAttribute("page", page);
        req.setAttribute("pageSize", pageSize);
        return new ActionResult("users");
    }

}
