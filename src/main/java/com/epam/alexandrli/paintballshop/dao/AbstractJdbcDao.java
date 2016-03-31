package com.epam.alexandrli.paintballshop.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractJdbcDao<T> implements GenericDao<T> {
    public static final String SELECT_FROM = "SELECT * FROM ";
    public static final String WHERE_ID = " WHERE id = ";
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

    protected abstract String getQueryToUpdateById();

    protected abstract T getObjectFromResultSet(ResultSet rs) throws SQLException;

    protected abstract void setVariablesForPreparedStatementExceptId(T t, PreparedStatement ps) throws SQLException;

    protected abstract void setVariablesForPreparedStatement(T t, PreparedStatement ps) throws SQLException;

    protected abstract void setResultObjectId(T t, ResultSet rs) throws SQLException;


    public void insert(T t) throws DaoException {
        try (PreparedStatement ps = connection.prepareStatement(getQueryForInsert(), Statement.RETURN_GENERATED_KEYS)) {
            setVariablesForPreparedStatementExceptId(t, ps);
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
             ResultSet rs = st.executeQuery(SELECT_FROM + getTableName() + WHERE_ID + id)) {
            rs.next();
            return getObjectFromResultSet(rs);
        } catch (SQLException e) {
            throw new DaoException("Couldn't find object by current id", e);
        }
    }

    public List<T> findAll() throws DaoException {
        List<T> objects = new ArrayList<>();
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(SELECT_FROM + getTableName())) {
            while (rs.next()) {
                objects.add(getObjectFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new DaoException("Couldn't find object by current id", e);
        }
        return objects;
    }

    public List<T> findAll(int pageNumber, int pageSize) throws DaoException {
        List<T> objects = new ArrayList<>();
        int startRow = (pageNumber - 1) * pageSize;
        int endRow = startRow + pageSize;
        try (PreparedStatement st = connection.prepareStatement(SELECT_FROM + getTableName() + " LIMIT ?,?")) {
            st.setInt(1, startRow);
            st.setInt(2, endRow);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                objects.add(getObjectFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new DaoException("Couldn't find object by current id", e);
        }
        return objects;
    }

    public void delete(int id) throws DaoException {
        try (Statement st = connection.createStatement()) {
            st.execute("DELETE FROM " + getTableName() + WHERE_ID + id);
        } catch (SQLException e) {
            throw new DaoException("Couldn't delete object by id", e);
        }
    }

    public void update(T t) throws DaoException {
        try (PreparedStatement ps = connection.prepareStatement(getQueryToUpdateById())) {
            setVariablesForPreparedStatement(t, ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Couldn't update Gender Object in db", e);
        }
    }

    public List<T> findAllByParams(Map<String, String> params) throws DaoException {
        List<T> objects = new ArrayList<>();
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(getQueryToFindAllByParams(params))) {
            while (rs.next()) {
                objects.add(getObjectFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new DaoException("Couldn't find object with this params", e);
        }
        return objects;
    }

    private String getQueryToFindAllByParams(Map<String, String> params) {
        String resultQuery = "SELECT * FROM " + getTableName() + " WHERE ";
        for (Map.Entry<String, String> param : params.entrySet()) {
            if (params.size() == 1) {
                resultQuery += param.getKey() + " = " + param.getValue();
                return resultQuery;
            } else {
                resultQuery += param.getKey() + " = " + param.getValue() + " AND ";
            }
        }
        return resultQuery.substring(0, resultQuery.length() - 5);
    }

    protected abstract String getTableName();


}


