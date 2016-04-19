package com.epam.alexandrli.paintballshop.action;

public class ActionException extends Exception {

    public ActionException(String message, Exception e) {
        super(message, e);
    }
}
