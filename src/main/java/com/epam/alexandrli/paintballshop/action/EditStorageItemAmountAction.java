package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.ShopService;
import com.epam.alexandrli.paintballshop.service.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static com.epam.alexandrli.paintballshop.service.Validator.STORAGE_AMOUNT;

public class EditStorageItemAmountAction implements Action {
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
            } else {
                try {
                    shopService.updateStorageItemAmount(req.getParameter("itemId" + i), amount);
                } catch (ServiceException e) {
                    throw new ActionException("Could not edit storage item amount", e);
                }
            }
        }
        req.setAttribute("flash.errorMap", errorMap);
        return new ActionResult(req.getHeader("referer"), true);
    }
}
