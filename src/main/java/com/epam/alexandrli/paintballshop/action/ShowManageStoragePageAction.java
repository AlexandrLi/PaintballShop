package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.StorageItem;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowManageStoragePageAction implements Action {
    public static final int FIRST_PAGE = 1;
    public static final int PAGE_SIZE = 2;

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        List<StorageItem> storageItems;
        String page = req.getParameter("page");
        int storageItemsCount;
        try {
            if (page == null) {
                page = String.valueOf(FIRST_PAGE);
            }
            ShopService shopService = new ShopService();
            storageItems = shopService.getAllStorageItemsOnPage(Integer.parseInt(page), PAGE_SIZE);
            storageItemsCount = shopService.getStorageItemsCount();
        } catch (ServiceException e) {
            throw new ActionException("Could not show manage storageItems page", e);
        }
        int pageCount;
        if (storageItemsCount % PAGE_SIZE == 0) {
            pageCount = storageItemsCount / PAGE_SIZE;
        } else {
            pageCount = storageItemsCount / PAGE_SIZE + 1;
        }
        req.setAttribute("storageItems", storageItems);
        req.setAttribute("pagesCount", pageCount);
        req.setAttribute("page", page);
        return new ActionResult("storage");

    }
}
