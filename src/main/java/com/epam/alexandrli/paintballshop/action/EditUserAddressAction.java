package com.epam.alexandrli.paintballshop.action;

import com.epam.alexandrli.paintballshop.Validator;
import com.epam.alexandrli.paintballshop.entity.Address;
import com.epam.alexandrli.paintballshop.entity.User;
import com.epam.alexandrli.paintballshop.service.ServiceException;
import com.epam.alexandrli.paintballshop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.alexandrli.paintballshop.Validator.NOT_EMPTY_NUMBER;
import static com.epam.alexandrli.paintballshop.Validator.NOT_EMPTY_TEXT;

public class EditUserAddressAction implements Action {
    public static final Logger logger = LoggerFactory.getLogger(EditUserAddressAction.class);
    private Validator validator = new Validator();
    private boolean invalid;

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        User currentUser = (User) req.getSession(false).getAttribute("loggedUser");
        Address currentUserAddress = currentUser.getAddress();
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
            return new ActionResult("edit-user-address");
        }
        currentUserAddress.setCountry(country);
        currentUserAddress.setCity(city);
        currentUserAddress.setStreet(street);
        currentUserAddress.setBuildingNumber(buildingNumber);
        currentUserAddress.setApartmentNumber(apartmentNumber);
        try {
            UserService userService = new UserService();
            Address updatedAddress = userService.updateUserAddress(currentUserAddress);
            req.getSession(false).removeAttribute("address");
            req.setAttribute("address", updatedAddress);
            logger.info("{} updated his address to {}", currentUser, updatedAddress);
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
