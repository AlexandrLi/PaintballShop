package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.StorageItem;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowManageStoragePageAction implements Action {
    public static final Logger logger = LoggerFactory.getLogger(ShowManageStoragePageAction.class);
    public final String FIRST_PAGE = "1";
    public final String DEFAULT_PAGE_SIZE = "3";

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        List<StorageItem> storageItems;
        String page = req.getParameter("page");
        if (page == null) {
            page = FIRST_PAGE;
        }
        String pageSize = DEFAULT_PAGE_SIZE;
        if (req.getParameter("pageSize") != null) {
            pageSize = req.getParameter("pageSize");
        }
        int storageItemsCount;
        try {
            ShopService shopService = new ShopService();
            storageItems = shopService.getAllStorageItemsOnPage(Integer.parseInt(page), Integer.parseInt(pageSize));
            storageItemsCount = shopService.getStorageItemsCount();
        } catch (ServiceException e) {
            throw new ActionException("Could not show manage storageItems page", e);
        }
        int pageCount;
        if (storageItemsCount % Integer.parseInt(pageSize) == 0) {
            pageCount = storageItemsCount / Integer.parseInt(pageSize);
        } else {
            pageCount = storageItemsCount / Integer.parseInt(pageSize) + 1;
        }
        req.setAttribute("storageItems", storageItems);
        req.setAttribute("pagesCount", pageCount);
        req.setAttribute("page", page);
        req.setAttribute("pageSize", pageSize);
        logger.info("Page number: {}. Page size: {}. Pages count: {}", page, pageSize, pageCount);
        return new ActionResult("storage");

    }
}
