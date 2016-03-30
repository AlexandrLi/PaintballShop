package com.epam.alexandrli.paintballshop.dao;

import java.sql.SQLException;

public abstract class AbstractDaoFactory {
    public static final int JDBC = 0;

    public static AbstractDaoFactory getDAOFactory(int factoryType) throws SQLException {
        switch (factoryType) {
            case JDBC:
                return new JdbcDaoFactory();
            default:
                return null;
        }
    }

    public abstract GenericDao getDAO(Class clazz);
}
