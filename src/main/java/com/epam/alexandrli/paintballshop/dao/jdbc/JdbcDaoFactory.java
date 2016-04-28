package com.epam.alexandrli.paintballshop.dao.jdbc;

import com.epam.alexandrli.paintballshop.connectionpool.ConnectionPool;
import com.epam.alexandrli.paintballshop.dao.DaoException;
import com.epam.alexandrli.paintballshop.dao.DaoFactory;
import com.epam.alexandrli.paintballshop.dao.GenericDao;
import com.epam.alexandrli.paintballshop.entity.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDaoFactory extends DaoFactory {
    public static final Logger logger = LoggerFactory.getLogger(JdbcDaoFactory.class);
    private Connection connection;
    private DataSource pool;

    public JdbcDaoFactory() throws DaoException {
        this.pool = ConnectionPool.getInstance();
        try {
            this.connection = pool.getConnection();
            logger.debug("JdbcDaoFactory has been initialized");
        } catch (SQLException e) {
            throw new DaoException("Could not get connection", e);
        }
    }

    public void close() throws DaoException {
        try {
            connection.close();
            logger.debug("JdbcDaoFactory closed");
        } catch (SQLException e) {
            throw new DaoException("Could not close factory", e);
        }
    }

    @Override
    public <T extends BaseEntity> GenericDao<T> getDao(Class<T> clazz) throws DaoException {
        AbstractJdbcDao<T> daoObject;
        try {
            String inputClassName = clazz.getSimpleName();
            String packageName = this.getClass().getPackage().getName();
            String resultClassName = String.format("%s.Jdbc%sDao", packageName, inputClassName);
            daoObject = (AbstractJdbcDao<T>) Class.forName(resultClassName).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new DaoException("Dao object for " + clazz + " not found.", e);
        }
        daoObject.setConnection(connection);
        return daoObject;
    }

    public void startTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            logger.debug("Transaction starts. Transaction level - {}", Connection.TRANSACTION_SERIALIZABLE);
        } catch (SQLException e) {
            throw new DaoException("Could not start transaction", e);
        }
    }

    public void commitTransaction() throws DaoException {
        try {
            connection.commit();
            logger.debug("Commit transaction changes");
        } catch (SQLException e) {
            throw new DaoException("Could not commit transaction transaction", e);
        }
    }

    public void rollbackTransaction() throws DaoException {
        try {
            connection.rollback();
            logger.debug("Rollback transaction changes");
        } catch (SQLException e) {
            throw new DaoException("Could not rollback transaction changes", e);
        }
    }
}
