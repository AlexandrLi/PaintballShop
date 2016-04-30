package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.Address;
import com.epam.alexandrli.paintballshop.entity.Gender;
import com.epam.alexandrli.paintballshop.entity.User;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.ShopService;
import com.epam.alexandrli.paintballshop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowEditUserPageAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            UserService userService = new UserService();
            User user = userService.getFilledUserById(Integer.valueOf(req.getParameter("id")));
            Address userAddress = userService.getUserAddress(user);
            ShopService shopService = new ShopService();
            List<Gender> genders = shopService.getAllGenders();
            genders.stream().filter(gender -> user.getGender().getId().equals(gender.getId())).forEach(user::setGender);
            req.setAttribute("user", user);
            req.setAttribute("address", userAddress);
            req.getSession(false).setAttribute("genders", genders);
        } catch (ServiceException e) {
            throw new ActionException("Could not show user data edit page", e);
        }
        return new ActionResult("edit-user");
    }
}
