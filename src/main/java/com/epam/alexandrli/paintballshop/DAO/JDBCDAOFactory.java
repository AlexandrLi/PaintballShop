package com.epam.alexandrli.paintballshop.dao;

import com.epam.alexandrli.paintballshop.connectionpool.ConnectionPool;

import java.sql.Connection;

public class JdbcDaoFactory extends AbstractDaoFactory {
    private Connection connection;

    public JdbcDaoFactory() {
        ConnectionPool pool = ConnectionPool.getInstance();
        connection = pool.getConnection();
    }

    @Override
    public GenericDao getDAO(Class clazz) throws DaoException {
        String inputClassName = clazz.getSimpleName();
        AbstractJdbcDao daoObject;
        try {
            daoObject = (AbstractJdbcDao) Class.forName("com.epam.alexandrli.paintballshop.dao." + inputClassName + "Dao").newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new DaoException("Dao object for " + clazz + " not found.", e);
        }
        daoObject.setConnection(connection);
        return daoObject;
    }
}
