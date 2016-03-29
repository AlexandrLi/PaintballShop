package com.epam.alexandrli.paintballshop.dao;

import java.sql.Connection;

public abstract class AbstractJdbcDao<T> implements GenericDao<T> {
    Connection connection;

    public AbstractJdbcDao() {
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
