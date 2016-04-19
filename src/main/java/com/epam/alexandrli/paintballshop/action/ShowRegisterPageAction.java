package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.Gender;
import com.epam.alexandrli.paintballshop.service.ShopService;
import com.epam.alexandrli.paintballshop.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowRegisterPageAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        List<Gender> genders;
        try {
            ShopService shopService = new ShopService();
            genders = shopService.getAllGenders();
        } catch (ServiceException e) {
            throw new ActionException("Gender list unavailable", e);
        }
        req.getSession(false).setAttribute("genders", genders);
        return new ActionResult("register");
    }
}
