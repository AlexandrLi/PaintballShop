package com.epam.alexandrli.paintballshop.dao;

import com.epam.alexandrli.paintballshop.connectionpool.ConnectionPool;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDaoFactory extends AbstractDaoFactory {
    private Connection connection;

    public JdbcDaoFactory() throws SQLException {
        DataSource pool = ConnectionPool.getInstance();
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

    public void beginTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException("Couldn't setAutoCommit to false", e);
        }
    }

}
