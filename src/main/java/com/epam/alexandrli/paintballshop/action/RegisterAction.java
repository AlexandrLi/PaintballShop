package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.Address;
import com.epam.alexandrli.paintballshop.entity.Gender;
import com.epam.alexandrli.paintballshop.entity.User;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.UserService;
import com.epam.alexandrli.paintballshop.service.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.alexandrli.paintballshop.service.Validator.*;

public class RegisterAction implements Action {
    private Validator validator;
    private boolean invalid;

    public RegisterAction() {
        validator = new Validator();
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        UserService userService;
        userService = new UserService();

        User user = new User();
        String email = req.getParameter("email");
        if (validator.validate(email, EMAIL)) {
            user.setEmail(email);
        } else {
            req.setAttribute("emailError", "Wrong email format");
            invalid = true;
        }
        String password = req.getParameter("password");
        if (validator.validate(password, PASSWORD)) {
            user.setPassword(password);
        } else {
            req.setAttribute("passwordError", "Must have at least 6 characters");
            invalid = true;
        }
        String firstName = req.getParameter("firstName");
        if (validator.validate(firstName, NOT_EMPTY_TEXT)) {
            user.setFirstName(firstName);
        } else {
            req.setAttribute("firstNameError", "Must be not empty");
            invalid = true;
        }
        String lastName = req.getParameter("lastName");
        if (validator.validate(lastName, NOT_EMPTY_TEXT)) {
            user.setLastName(lastName);
        } else {
            req.setAttribute("lastNameError", "Must be not empty");
            invalid = true;
        }
        String phoneNumber = req.getParameter("phoneNumber");
        if (validator.validate(phoneNumber, PHONE_NUMBER)) {
            user.setPhoneNumber(phoneNumber);
        } else {
            req.setAttribute("phoneNumberError", "Must start from + and contain from 6 to 14 digits ");
            invalid = true;
        }
        Address address = new Address();
        String country = req.getParameter("country");
        if (validator.validate(country, NOT_EMPTY_TEXT)) {
            address.setCountry(country);
        } else {
            req.setAttribute("countryError", "Must be not empty");
            invalid = true;
        }
        String city = req.getParameter("city");
        if (validator.validate(city, NOT_EMPTY_TEXT)) {
            address.setCity(city);
        } else {
            req.setAttribute("cityError", "Must be not empty");
            invalid = true;
        }
        String street = req.getParameter("street");
        if (validator.validate(street, NOT_EMPTY_TEXT)) {
            address.setStreet(street);
        } else {
            req.setAttribute("streetError", "Must be not empty");
            invalid = true;
        }
        String buildingNumber = req.getParameter("buildingNumber");
        if (validator.validate(buildingNumber, NOT_EMPTY_NUMBER)) {
            address.setBuildingNumber(buildingNumber);
        } else {
            req.setAttribute("buildingError", "Must be not empty");
            invalid = true;
        }
        String apartmentNumber = req.getParameter("apartmentNumber");
        if (validator.validate(apartmentNumber, NOT_EMPTY_NUMBER)) {
            address.setApartmentNumber(apartmentNumber);
        } else {
            req.setAttribute("apartmentError", "Must be not empty");
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
            User registeredUser = userService.registerUser(user, address);
            req.getSession(false).setAttribute("loggedUser", registeredUser);
            req.getSession(false).removeAttribute("genders");
        } catch (ServiceException e) {
            throw new ActionException("Could not register user", e);
        }
        return new ActionResult("home", true);
    }

}
