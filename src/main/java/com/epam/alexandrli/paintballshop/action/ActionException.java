package com.epam.alexandrli.paintballshop.action;

import java.sql.SQLException;

public class ActionException extends RuntimeException {

    public ActionException(String message, SQLException e) {
        super(message, e);
    }
}
