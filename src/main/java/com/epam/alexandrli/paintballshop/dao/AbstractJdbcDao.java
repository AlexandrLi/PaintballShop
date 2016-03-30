package com.epam.alexandrli.paintballshop.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    protected abstract T prepareObject(ResultSet rs) throws DaoException;

    public T read(int id) throws DaoException {
        T resultObject;
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM gender WHERE id=" + id)) {
            resultObject = prepareObject(rs);
        } catch (SQLException e) {
            throw new DaoException("Couldn't read object from db", e);
        }
        return resultObject;
    }
}
