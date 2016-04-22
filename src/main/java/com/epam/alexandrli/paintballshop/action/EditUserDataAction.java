package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.User;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.UserService;
import com.epam.alexandrli.paintballshop.service.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.alexandrli.paintballshop.service.Validator.*;

public class EditUserDataAction implements Action {
    private Validator validator = new Validator();
    private boolean invalid;

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        UserService userService;
        userService = new UserService();
        User currentUser = (User) req.getSession(false).getAttribute("loggedUser");
        String password = req.getParameter("password");
        if (validator.validate(password, PASSWORD)) {
            currentUser.setPassword(password);
        } else {
            req.setAttribute("passwordError", "Must have at least 6 characters");
            invalid = true;
        }
        String firstName = req.getParameter("firstName");
        if (validator.validate(firstName, NOT_EMPTY_TEXT)) {
            currentUser.setFirstName(firstName);
        } else {
            req.setAttribute("firstNameError", "Must not be empty");
            invalid = true;
        }
        String lastName = req.getParameter("lastName");
        if (validator.validate(lastName, NOT_EMPTY_TEXT)) {
            currentUser.setLastName(lastName);
        } else {
            req.setAttribute("lastNameError", "Must not be empty");
            invalid = true;
        }
        String phoneNumber = req.getParameter("phoneNumber");
        if (validator.validate(phoneNumber, PHONE_NUMBER)) {
            currentUser.setPhoneNumber(phoneNumber);
        } else {
            req.setAttribute("phoneNumberError", "Must start from + and contain from 6 to 14 digits ");
            invalid = true;
        }
        if (invalid) {
            return new ActionResult("edit-user-data");
        }
        currentUser.getGender().setId(Integer.valueOf(req.getParameter("gender")));
        try {
            User updatedUser = userService.updateUserProfile(currentUser);
            req.getSession(false).setAttribute("loggedUser", updatedUser);
            req.getSession(false).removeAttribute("genders");
        } catch (ServiceException e) {
            throw new ActionException("Could not update profile", e);
        }
        return new ActionResult("user/profile", true);
    }
}
