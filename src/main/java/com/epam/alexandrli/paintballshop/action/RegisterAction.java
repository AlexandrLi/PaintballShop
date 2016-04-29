package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.Validator;
import com.epam.alexandrli.paintballshop.entity.Address;
import com.epam.alexandrli.paintballshop.entity.Gender;
import com.epam.alexandrli.paintballshop.entity.User;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.alexandrli.paintballshop.Validator.*;

public class RegisterAction implements Action {
    public static final Logger logger = LoggerFactory.getLogger(RegisterAction.class);
    private Validator validator;
    private boolean invalid;

    public RegisterAction() {
        validator = new Validator();
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        UserService userService = new UserService();
        String email = req.getParameter("email");
        try {
            if (!userService.checkEmail(email)) {
                req.setAttribute("emailError", "used");
                invalid = true;
                logger.info("Email already taken - {}", email);
            } else {
                checkParameter(email, "email", EMAIL, req);
            }

        } catch (ServiceException e) {
            throw new ActionException("Could not register user", e);
        }
        String password = req.getParameter("password");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String phoneNumber = req.getParameter("phoneNumber");
        checkParameter(password, "password", PASSWORD, req);
        checkParameter(firstName, "firstName", NOT_EMPTY_TEXT, req);
        checkParameter(lastName, "lastName", NOT_EMPTY_TEXT, req);
        checkParameter(phoneNumber, "phoneNumber", PHONE_NUMBER, req);
        String country = req.getParameter("country");
        String city = req.getParameter("city");
        String street = req.getParameter("street");
        String buildingNumber = req.getParameter("buildingNumber");
        String apartmentNumber = req.getParameter("apartmentNumber");
        checkParameter(country, "country", NOT_EMPTY_TEXT, req);
        checkParameter(city, "city", NOT_EMPTY_TEXT, req);
        checkParameter(street, "street", NOT_EMPTY_TEXT, req);
        checkParameter(buildingNumber, "buildingNumber", NOT_EMPTY_NUMBER, req);
        checkParameter(apartmentNumber, "apartmentNumber", NOT_EMPTY_NUMBER, req);

        if (invalid) {
            invalid = false;
            req.setAttribute("email", email);
            req.setAttribute("password", password);
            req.setAttribute("firstName", firstName);
            req.setAttribute("lastName", lastName);
            req.setAttribute("phoneNumber", phoneNumber);
            req.setAttribute("country", country);
            req.setAttribute("city", city);
            req.setAttribute("street", street);
            req.setAttribute("buildingNumber", buildingNumber);
            req.setAttribute("apartmentNumber", apartmentNumber);
            req.setAttribute("gender", req.getParameter("gender"));
            return new ActionResult("register");
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhoneNumber(phoneNumber);
        Gender gender = new Gender();
        gender.setId(Integer.valueOf(req.getParameter("gender")));
        user.setGender(gender);
        Address address = new Address();
        address.setCountry(country);
        address.setCity(city);
        address.setStreet(street);
        address.setBuildingNumber(buildingNumber);
        address.setApartmentNumber(apartmentNumber);
        try {
            User registeredUser = userService.registerUser(user, address);
            req.getSession(false).setAttribute("loggedUser", registeredUser);
            req.getSession(false).removeAttribute("genders");
            logger.info("{} registered. Registered {}", registeredUser, address);
            return new ActionResult("home", true);
        } catch (ServiceException e) {
            throw new ActionException("Could not register user", e);
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
