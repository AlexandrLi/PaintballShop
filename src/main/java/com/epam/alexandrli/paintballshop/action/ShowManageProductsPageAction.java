package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.Product;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowManageProductsPageAction implements Action {
    public final String FIRST_PAGE = "1";
    public final String DEFAULT_PAGE_SIZE = "3";

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        ShopService shopService = new ShopService();
        List<Product> products;
        String page = req.getParameter("page");
        if (page == null) {
            page = FIRST_PAGE;
        }
        String pageSize = DEFAULT_PAGE_SIZE;
        if (req.getParameter("pageSize") != null) {
            pageSize = req.getParameter("pageSize");
        }
        int productsCount;
        try {
            products = shopService.getAllProductsOnPage(Integer.parseInt(page), Integer.parseInt(pageSize));
            productsCount = shopService.getProductsCount();
        } catch (ServiceException e) {
            throw new ActionException("Could not show manage users page", e);
        }
        int pageCount;
        if (productsCount % Integer.parseInt(pageSize) == 0) {
            pageCount = productsCount / Integer.parseInt(pageSize);
        } else {
            pageCount = productsCount / Integer.parseInt(pageSize) + 1;
        }
        req.setAttribute("products", products);
        req.setAttribute("pagesCount", pageCount);
        req.setAttribute("page", page);
        req.setAttribute("pageSize", pageSize);
        return new ActionResult("products");
    }

}
