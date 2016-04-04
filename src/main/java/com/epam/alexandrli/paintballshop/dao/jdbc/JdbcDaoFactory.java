package com.epam.alexandrli.paintballshop.dao.jdbc;

import com.epam.alexandrli.paintballshop.connectionpool.ConnectionPool;
import com.epam.alexandrli.paintballshop.dao.DaoException;
import com.epam.alexandrli.paintballshop.dao.DaoFactory;
import com.epam.alexandrli.paintballshop.dao.GenericDao;
import com.epam.alexandrli.paintballshop.entity.BaseEntity;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDaoFactory extends DaoFactory {
    private Connection connection;

    public JdbcDaoFactory() throws SQLException {
        DataSource pool = ConnectionPool.getInstance();
        connection = pool.getConnection();
    }

    public void close() throws DaoException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DaoException("Couldn't close factory", e);
        }
    }

    @Override
    public <T extends BaseEntity> GenericDao<T> getDao(Class<T> clazz) throws DaoException {
        AbstractJdbcDao<T> daoObject;
        try {
            String inputClassName = clazz.getSimpleName();
            String packageName = this.getClass().getPackage().getName();
            String resultClassName = String.format("%s.%sDao", packageName, inputClassName);
            daoObject = (AbstractJdbcDao<T>) Class.forName(resultClassName).newInstance();
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

    public void commit() throws DaoException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException("Couldn't commit transaction", e);
        }
    }

    public void rollback() throws DaoException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DaoException("Couldn't rollback changes", e);
        }
    }
}
