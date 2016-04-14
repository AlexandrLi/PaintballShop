package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.Gender;
import com.epam.alexandrli.paintballshop.entity.User;
import com.epam.alexandrli.paintballshop.service.UserService;
import com.epam.alexandrli.paintballshop.service.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

import static com.epam.alexandrli.paintballshop.service.Validator.*;

public class RegisterAction implements Action {
    private Validator validator;
    private boolean invalid;

    public RegisterAction() {
        validator = new Validator();
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        UserService userService = null;
        try {
            userService = new UserService();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        User user = new User();
        String email = req.getParameter("email");
        if (validator.validate(email, EMAIL_REGEX)) {
            user.setEmail(email);
        } else {
            req.setAttribute("emailError", "Wrong email format");
            invalid = true;
        }
        String password = req.getParameter("password");
        if (validator.validate(password, PASSWORD_REGEX)) {
            user.setPassword(password);
        } else {
            req.setAttribute("passwordError", "Must have at least 6 characters");
            invalid = true;
        }
        String firstName = req.getParameter("firstName");
        if (validator.validate(firstName, NAME_REGEX)) {
            user.setFirstName(firstName);
        } else {
            req.setAttribute("firstNameError", "Must start from any letter");
            invalid = true;
        }
        String lastName = req.getParameter("lastName");
        if (validator.validate(lastName, NAME_REGEX)) {
            user.setLastName(lastName);
        } else {
            req.setAttribute("lastNameError", "Must start from any letter");
            invalid = true;
        }
        String phoneNumber = req.getParameter("phoneNumber");
        if (validator.validate(phoneNumber, PHONE_NUMBER_REGEX)) {
            user.setPhoneNumber(phoneNumber);
        } else {
            req.setAttribute("phoneNumberError", "Must start from + and contain from 6 to 14 digits ");
            invalid = true;
        }
        if (invalid) {
            invalid = false;
            return new ActionResult("register");
        }
        Gender gender = new Gender();
        gender.setId(Integer.valueOf(req.getParameter("gender")));
        user.setGender(gender);
        try {
            User registeredUser = userService.registerUser(user);
            req.getSession(false).setAttribute("user", registeredUser);
            req.getSession(false).removeAttribute("genders");
        } catch (SQLException e) {
            // TODO: 06.04.2016 handle exception
        }
        return new ActionResult("home", true);
    }

}
