package com.epam.alexandrli.paintballshop.dao;

import java.sql.*;

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

    protected abstract String getQueryForInsert();

    protected abstract String getQueryToFindByPK();

    protected abstract void setVariablesForPreparedStatement(T t, PreparedStatement ps) throws SQLException;

    protected abstract void setResultObjectId(T t, ResultSet rs) throws SQLException;

    protected abstract T getObjectFromResultSet(ResultSet rs) throws SQLException;

    public void insert(T t) throws DaoException {
        try (PreparedStatement ps = connection.prepareStatement(getQueryForInsert(), Statement.RETURN_GENERATED_KEYS)) {
            setVariablesForPreparedStatement(t, ps);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                setResultObjectId(t, rs);
            }
        } catch (SQLException e) {
            throw new DaoException("Couldn't insert Gender Object to db", e);
        }
    }

    public T findByPK(int id) throws DaoException {
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(getQueryToFindByPK() + id)) {
            rs.next();
            return getObjectFromResultSet(rs);
        } catch (SQLException e) {
            throw new DaoException("Couldn't find object by current id", e);
        }
    }


}


