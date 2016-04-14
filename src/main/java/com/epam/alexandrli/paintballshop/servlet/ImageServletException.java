package com.epam.alexandrli.paintballshop.servlet;

import java.sql.SQLException;

public class ImageServletException extends RuntimeException {

    public ImageServletException(String message, SQLException e) {
        super(message, e);
    }
}
