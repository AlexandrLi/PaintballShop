package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.Validator;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static com.epam.alexandrli.paintballshop.Validator.STORAGE_AMOUNT;

public class EditStorageItemAmountAction implements Action {
    public static final Logger logger = LoggerFactory.getLogger(EditStorageItemAmountAction.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        ShopService shopService = new ShopService();
        Validator validator = new Validator();
        Integer rowsCount = Integer.parseInt(req.getParameter("rowsCount"));
        Map<Integer, String> errorMap = new HashMap<>();
        for (int i = 0; i < rowsCount; i++) {
            String amount = req.getParameter("amount" + i);
            if (!validator.validate(amount, STORAGE_AMOUNT)) {
                errorMap.put(i, "true");
                logger.info("Invalid storage item amount format - {}", amount);
            } else {
                try {
                    String itemId = req.getParameter("itemId" + i);
                    shopService.updateStorageItemAmount(itemId, amount);
                    logger.info("Storage item (id = {}) amount set to {} by {}", itemId, amount, req.getSession(false).getAttribute("loggedUser"));
                } catch (ServiceException e) {
                    throw new ActionException("Could not edit storage item amount", e);
                }
            }
        }
        req.setAttribute("flash.errorMap", errorMap);
        return new ActionResult(req.getHeader("referer"), true);
    }
}
