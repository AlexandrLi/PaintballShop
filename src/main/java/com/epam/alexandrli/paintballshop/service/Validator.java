package com.epam.alexandrli.paintballshop.service;

public class Validator {
    public static final String EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String PASSWORD = "^.{6,}$";
    public static final String NOT_EMPTY_TEXT = "^\\p{L}+{1,}$";
    public static final String NOT_EMPTY_NUMBER = "^\\p{N}+{1,}$";
    public static final String PRODUCT_AMOUNT = "^[1-9]\\d{0,3}$";
    public static final String STORAGE_AMOUNT = "^\\d{1,4}$";
    public static final String MONEY = "^[1-9]\\d{0,8}$";
    public static final String PHONE_NUMBER = "^\\+(?:[0-9]?){6,14}[0-9]$";


    public boolean validate(final String input, String regex) {
        return input.matches(regex);
    }
}
