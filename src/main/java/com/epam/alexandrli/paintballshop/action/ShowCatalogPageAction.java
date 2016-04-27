package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.Product;
import com.epam.alexandrli.paintballshop.service.ProductService;
import com.epam.alexandrli.paintballshop.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowCatalogPageAction implements Action {
    public final String FIRST_PAGE = "1";
    public final String DEFAULT_PAGE_SIZE = "3";

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        List<Product> products;
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
        List<Product> productsOnPage;
        String type = req.getParameter("type");
        try {
            ProductService productService = new ProductService();
            products = productService.getAllProductsByType(type);
            if (products.size() < pageInt * pageSizeInt) {
                productsOnPage = products.subList(((pageInt - 1) * pageSizeInt), products.size());
            } else {
                productsOnPage = products.subList(((pageInt - 1) * pageSizeInt), pageInt * pageSizeInt);
            }
        } catch (ServiceException e) {
            throw new ActionException("Could not show catalog page", e);
        }
        int pageCount;
        if (products.size() % Integer.parseInt(pageSize) == 0) {
            pageCount = products.size() / Integer.parseInt(pageSize);
        } else {
            pageCount = products.size() / Integer.parseInt(pageSize) + 1;
        }
        req.setAttribute("products", productsOnPage);
        req.setAttribute("type", type);
        req.setAttribute("pagesCount", pageCount);
        req.setAttribute("page", page);
        req.setAttribute("pageSize", pageSize);
        return new ActionResult("type-catalog");
    }
}
