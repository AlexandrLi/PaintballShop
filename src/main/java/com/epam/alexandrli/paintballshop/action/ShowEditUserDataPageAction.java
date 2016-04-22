package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.Gender;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowEditUserDataPageAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        List<Gender> genders;
        try {
            ShopService shopService = new ShopService();
            genders = shopService.getAllGenders();
            req.getSession(false).setAttribute("genders", genders);
        } catch (ServiceException e) {
            throw new ActionException("Could not show user data edit page", e);
        }
        return new ActionResult("edit-user-data");
    }
}
