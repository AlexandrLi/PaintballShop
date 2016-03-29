package com.epam.alexandrli.paintballshop.DAO2;

public class DaoException extends RuntimeException {
    public DaoException(Exception e) {
        super(e);
    }

    public DaoException(String message, Exception e) {
        super(message, e);
    }
}
