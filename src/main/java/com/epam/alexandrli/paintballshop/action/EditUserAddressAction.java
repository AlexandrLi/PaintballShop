package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.entity.Address;
import com.epam.alexandrli.paintballshop.entity.User;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.UserService;
import com.epam.alexandrli.paintballshop.service.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.alexandrli.paintballshop.service.Validator.NOT_EMPTY_NUMBER;
import static com.epam.alexandrli.paintballshop.service.Validator.NOT_EMPTY_TEXT;

public class EditUserAddressAction implements Action {
    private Validator validator = new Validator();
    private boolean invalid;

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        User currentUser = (User) req.getSession(false).getAttribute("loggedUser");
        Address currentUserAddress = currentUser.getAddress();
        String country = req.getParameter("country");
        if (validator.validate(country, NOT_EMPTY_TEXT)) {
            currentUserAddress.setCountry(country);
        } else {
            req.setAttribute("countryError", "Must be not empty");
            invalid = true;
        }
        String city = req.getParameter("city");
        if (validator.validate(city, NOT_EMPTY_TEXT)) {
            currentUserAddress.setCity(city);
        } else {
            req.setAttribute("cityError", "Must be not empty");
            invalid = true;
        }
        String street = req.getParameter("street");
        if (validator.validate(street, NOT_EMPTY_TEXT)) {
            currentUserAddress.setStreet(street);
        } else {
            req.setAttribute("streetError", "Must be not empty");
            invalid = true;
        }
        String buildingNumber = req.getParameter("buildingNumber");
        if (validator.validate(buildingNumber, NOT_EMPTY_NUMBER)) {
            currentUserAddress.setBuildingNumber(buildingNumber);
        } else {
            req.setAttribute("buildingError", "Must be not empty");
            invalid = true;
        }
        String apartmentNumber = req.getParameter("apartmentNumber");
        if (validator.validate(apartmentNumber, NOT_EMPTY_NUMBER)) {
            currentUserAddress.setApartmentNumber(apartmentNumber);
        } else {
            req.setAttribute("apartmentError", "Must be not empty");
            invalid = true;
        }
        if (invalid) {
            return new ActionResult("edit-user-address");
        }
        try {
            UserService userService = new UserService();
            Address updatedAddress = userService.updateUserAddress(currentUserAddress);
            req.getSession(false).removeAttribute("address");
            req.setAttribute("address", updatedAddress);
        } catch (ServiceException e) {
            throw new ActionException("Could not update profile", e);
        }
        return new ActionResult("user/profile", true);
    }
}
