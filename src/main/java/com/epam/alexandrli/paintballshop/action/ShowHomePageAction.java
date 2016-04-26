package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.Product;
import com.epam.alexandrli.paintballshop.entity.ProductType;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.ShopService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowHomePageAction implements Action {
    public final String FIRST_PAGE = "1";
    public final String DEFAULT_PAGE_SIZE = "3";

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        List<Product> products;
        List<ProductType> productTypes;
        String page = req.getParameter("page");
        if (page == null) {
            page = FIRST_PAGE;
        }
        String pageSize = DEFAULT_PAGE_SIZE;
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("pageSize")) {
                pageSize = cookie.getValue();
            }
        }
        int productsCount;
        try {
            ShopService shopService = new ShopService();
            products = shopService.getAllProductsOnPage(Integer.parseInt(page), Integer.parseInt(pageSize));
            productsCount = shopService.getProductsCount();
            productTypes = shopService.getAllProductTypes();
        } catch (ServiceException e) {
            throw new ActionException("Could not fill content at homepage", e);
        }
        int pageCount;
        if (productsCount % Integer.parseInt(pageSize) == 0) {
            pageCount = productsCount / Integer.parseInt(pageSize);
        } else {
            pageCount = productsCount / Integer.parseInt(pageSize) + 1;
        }
        req.setAttribute("products", products);
        req.getSession().setAttribute("productTypes", productTypes);
        req.setAttribute("pagesCount", pageCount);
        req.setAttribute("page", page);
        return new ActionResult("home");
    }
}
