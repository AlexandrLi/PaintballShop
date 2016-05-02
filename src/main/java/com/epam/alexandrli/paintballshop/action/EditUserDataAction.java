package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.validator.Validator;
import com.epam.alexandrli.paintballshop.entity.User;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.alexandrli.paintballshop.validator.Validator.*;

public class EditUserDataAction implements Action {
    public static final Logger logger = LoggerFactory.getLogger(EditUserDataAction.class);
    private Validator validator = new Validator();
    private boolean invalid;

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        UserService userService;
        userService = new UserService();
        User currentUser = (User) req.getSession(false).getAttribute("loggedUser");
        String password = req.getParameter("password");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String phoneNumber = req.getParameter("phoneNumber");
        checkParameter(password, "password", PASSWORD, req);
        checkParameter(firstName, "firstName", NOT_EMPTY_TEXT, req);
        checkParameter(lastName, "lastName", NOT_EMPTY_TEXT, req);
        checkParameter(phoneNumber, "phoneNumber", PHONE_NUMBER, req);
        if (invalid) {
            invalid = false;
            return new ActionResult("edit-user-data");
        }
        currentUser.setPassword(password);
        currentUser.setFirstName(firstName);
        currentUser.setLastName(lastName);
        currentUser.setPhoneNumber(phoneNumber);
        currentUser.getGender().setId(Integer.valueOf(req.getParameter("gender")));
        try {
            User updatedUser = userService.updateUserProfile(currentUser);
            req.getSession(false).setAttribute("loggedUser", updatedUser);
            req.getSession(false).removeAttribute("genders");
            logger.info("{} updated his data to {}", currentUser, updatedUser);
            return new ActionResult("user/profile", true);
        } catch (ServiceException e) {
            throw new ActionException("Could not update profile", e);
        }
    }

    private void checkParameter(String parameterValue, String parameterName, String regex, HttpServletRequest req) {
        if (!validator.validate(parameterValue, regex)) {
            req.setAttribute(parameterName + "Error", "true");
            invalid = true;
            logger.info("Invalid format for {} - {}", parameterName, parameterValue);
        }
    }
}
