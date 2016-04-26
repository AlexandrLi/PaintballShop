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
        User user;
        String email = req.getParameter("email");
        try {
            user = userService.getUserById(Integer.valueOf(req.getParameter("userId")));
            if (!userService.checkEmail(email)) {
                req.setAttribute("emailError", "true");
                invalid = true;
            }

        } catch (ServiceException e) {
            throw new ActionException("Could not register user", e);
        }
        checkParameter(email, "email", EMAIL, req);
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
            req.setAttribute("user",user);
            req.setAttribute("address",user.getAddress());
            return new ActionResult("edit-user");
        }
        try {

            user.setEmail(email);
            user.setPassword(password);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPhoneNumber(phoneNumber);
            user.getGender().setId(Integer.valueOf(req.getParameter("gender")));
            Address userAddress = user.getAddress();
            userAddress.setCountry(country);
            userAddress.setCity(city);
            userAddress.setStreet(street);
            userAddress.setBuildingNumber(buildingNumber);
            userAddress.setApartmentNumber(apartmentNumber);
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

    private void checkParameter(String parameterValue, String parameterName, String regex, HttpServletRequest req) {
        if (!validator.validate(parameterValue, regex)) {
            req.setAttribute(parameterName + "Error", "true");
            invalid = true;
        }
    }
}
