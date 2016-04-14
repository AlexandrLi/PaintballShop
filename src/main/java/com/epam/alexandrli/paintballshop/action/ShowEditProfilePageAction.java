package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.Gender;
import com.epam.alexandrli.paintballshop.service.GenderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class ShowEditProfilePageAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Gender> genders = null;
        try {
            GenderService genderService = new GenderService();
            genders = genderService.getAllGenders();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getSession(false).setAttribute("genders", genders);
        return new ActionResult("userprofile");
    }
}
