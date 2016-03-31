package com.epam.alexandrli.paintballshop.dao;

import java.sql.SQLException;

public abstract class DaoFactory implements AutoCloseable {
    public static final int JDBC = 0;

    public static DaoFactory getDAOFactory(int factoryType) throws SQLException {
        switch (factoryType) {
            case JDBC:
                return new JdbcDaoFactory();
            default:
                return null;
        }
    }

    public abstract <T> GenericDao<T> getDao(Class<T> clazz);

    public abstract void beginTransaction() throws DaoException;

    public abstract void commit() throws DaoException;

    public abstract void rollback() throws DaoException;

}
