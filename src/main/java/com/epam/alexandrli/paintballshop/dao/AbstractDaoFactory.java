package com.epam.alexandrli.paintballshop.dao;

public abstract class AbstractDaoFactory {
    public static final int JDBC = 0;

    public static AbstractDaoFactory getDAOFactory(int factoryType) {
        switch (factoryType) {
            case JDBC:
                return new JdbcDaoFactory();
            default:
                return null;
        }
    }

    public abstract GenericDao getDAO(Class clazz);
}
