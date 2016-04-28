package com.epam.alexandrli.paintballshop.dao;

import com.epam.alexandrli.paintballshop.dao.jdbc.JdbcDaoFactory;
import com.epam.alexandrli.paintballshop.entity.BaseEntity;

public abstract class DaoFactory implements AutoCloseable {
    public static final int JDBC = 0;

    public static DaoFactory getDaoFactory(int factoryType) throws DaoException {
        switch (factoryType) {
            case JDBC:
                return new JdbcDaoFactory();
            default:
                return new JdbcDaoFactory();
        }
    }

    public abstract <T extends BaseEntity> GenericDao<T> getDao(Class<T> clazz) throws DaoException;

    public abstract void close() throws DaoException;

    public abstract void startTransaction() throws DaoException;

    public abstract void commitTransaction() throws DaoException;

    public abstract void rollbackTransaction() throws DaoException;

}
