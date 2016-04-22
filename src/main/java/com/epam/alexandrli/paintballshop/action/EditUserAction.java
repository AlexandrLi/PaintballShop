package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.Address;
import com.epam.alexandrli.paintballshop.entity.User;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.UserService;
import com.epam.alexandrli.paintballshop.service.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.alexandrli.paintballshop.service.Validator.*;

public class EditUserAction implements Action {
    private Validator validator = new Validator();
    private boolean invalid;

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        UserService userService = new UserService();
        try {
            User user = userService.getUserById(Integer.valueOf(req.getParameter("userId")));
            String email = req.getParameter("email");
            if (validator.validate(email, EMAIL)) {
                user.setEmail(email);
            } else {
                req.setAttribute("emailError", "Wrong format");
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
                req.setAttribute("firstNameError", "Must not be empty");
                invalid = true;
            }
            String lastName = req.getParameter("lastName");
            if (validator.validate(lastName, NOT_EMPTY_TEXT)) {
                user.setLastName(lastName);
            } else {
                req.setAttribute("lastNameError", "Must not be empty");
                invalid = true;
            }
            String phoneNumber = req.getParameter("phoneNumber");
            if (validator.validate(phoneNumber, PHONE_NUMBER)) {
                user.setPhoneNumber(phoneNumber);
            } else {
                req.setAttribute("phoneNumberError", "Must start from + and contain from 6 to 14 digits ");
                invalid = true;
            }
            Address userAddress = user.getAddress();
            String country = req.getParameter("country");
            if (validator.validate(country, NOT_EMPTY_TEXT)) {
                userAddress.setCountry(country);
            } else {
                req.setAttribute("countryError", "Must be not empty");
                invalid = true;
            }
            String city = req.getParameter("city");
            if (validator.validate(city, NOT_EMPTY_TEXT)) {
                userAddress.setCity(city);
            } else {
                req.setAttribute("cityError", "Must be not empty");
                invalid = true;
            }
            String street = req.getParameter("street");
            if (validator.validate(street, NOT_EMPTY_TEXT)) {
                userAddress.setStreet(street);
            } else {
                req.setAttribute("streetError", "Must be not empty");
                invalid = true;
            }
            String buildingNumber = req.getParameter("buildingNumber");
            if (validator.validate(buildingNumber, NOT_EMPTY_NUMBER)) {
                userAddress.setBuildingNumber(buildingNumber);
            } else {
                req.setAttribute("buildingError", "Must be not empty");
                invalid = true;
            }
            String apartmentNumber = req.getParameter("apartmentNumber");
            if (validator.validate(apartmentNumber, NOT_EMPTY_NUMBER)) {
                userAddress.setApartmentNumber(apartmentNumber);
            } else {
                req.setAttribute("apartmentError", "Must be not empty");
                invalid = true;
            }
            if (invalid) {
                return new ActionResult("edit-user");
            }
            user.getGender().setId(Integer.valueOf(req.getParameter("gender")));
            if (!req.getParameter("role").equals(user.getRole().name())) {
                user.setRole(User.Role.valueOf(req.getParameter("role")));
            }
            userService.updateUserAddress(userAddress);
            userService.updateUserProfile(user);
            req.getSession(false).removeAttribute("genders");
        } catch (ServiceException e) {
            throw new ActionException("Could not update profile", e);
        }
        return new ActionResult("manage/users", true);
    }
}
