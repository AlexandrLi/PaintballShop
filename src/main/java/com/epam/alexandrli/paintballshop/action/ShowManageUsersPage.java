package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.User;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowManageUsersPage implements Action {

    public static final int FIRST_PAGE = 1;
    public static final int PAGE_SIZE = 2;

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        ShopService shopService = new ShopService();
        List<User> users;
        String page = req.getParameter("page");
        int usersCount;
        try {
            if (page == null) {
                page = String.valueOf(FIRST_PAGE);
            }
            users = shopService.getAllUsersOnPage(Integer.parseInt(page), PAGE_SIZE);
            usersCount = shopService.getUsersCount();
        } catch (ServiceException e) {
            throw new ActionException("Could not show manage users page", e);
        }
        int pageCount;
        if (usersCount % PAGE_SIZE == 0) {
            pageCount = usersCount / PAGE_SIZE;
        } else {
            pageCount = usersCount / PAGE_SIZE + 1;
        }
        req.setAttribute("users", users);
        req.setAttribute("pagesCount", pageCount);
        req.setAttribute("page", page);
        return new ActionResult("users");
    }

}
