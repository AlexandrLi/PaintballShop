package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.Product;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowManageProductsPage implements Action {

    public static final int FIRST_PAGE = 1;
    public static final int PAGE_SIZE = 2;

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        ShopService shopService = new ShopService();
        List<Product> products;
        String page = req.getParameter("page");
        int productsCount;
        try {
            if (page == null) {
                page = String.valueOf(FIRST_PAGE);
            }
            products = shopService.getAllProductsOnPage(Integer.parseInt(page), PAGE_SIZE);
            productsCount = shopService.getProductsCount();
        } catch (ServiceException e) {
            throw new ActionException("Could not show manage users page", e);
        }
        int pageCount;
        if (productsCount % PAGE_SIZE == 0) {
            pageCount = productsCount / PAGE_SIZE;
        } else {
            pageCount = productsCount / PAGE_SIZE + 1;
        }
        req.setAttribute("products", products);
        req.setAttribute("pagesCount", pageCount);
        req.setAttribute("page", page);
        return new ActionResult("products");
    }

}
