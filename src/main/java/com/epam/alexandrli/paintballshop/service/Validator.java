package com.epam.alexandrli.paintballshop.service;

public class Validator {
    public static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String PASSWORD_REGEX = "^.{6,}$";
    public static final String NAME_REGEX = "^\\p{L}+{1,}$";
    public static final String PHONE_NUMBER_REGEX = "^\\+(?:[0-9]?){6,14}[0-9]$";


    public boolean validate(final String input, String regex) {
        return input.matches(regex);
    }
}
