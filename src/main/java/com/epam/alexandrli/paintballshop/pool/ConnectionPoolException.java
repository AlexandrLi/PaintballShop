package com.epam.alexandrli.paintballshop.pool;

public class ConnectionPoolException extends RuntimeException {
    public ConnectionPoolException(String message, Exception e) {
        super(message, e);
    }

    public ConnectionPoolException(String message) {
        super(message);
    }
}
