package com.epam.alexandrli.paintballshop.dao.jdbc;

import com.epam.alexandrli.paintballshop.dao.DaoException;
import com.epam.alexandrli.paintballshop.dao.GenericDao;
import com.epam.alexandrli.paintballshop.entity.BaseEntity;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractJdbcDao<T extends BaseEntity> implements GenericDao<T> {
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

    protected abstract String getTableName();

    protected abstract String getQueryForInsert();

    protected abstract String getQueryToUpdateById();

    protected abstract T getObjectFromResultSet(ResultSet rs) throws SQLException;

    protected abstract void setVariablesForPreparedStatementExceptId(T t, PreparedStatement ps) throws SQLException;

    private void setVariablesForPreparedStatement(T t, PreparedStatement ps) throws SQLException {
        setVariablesForPreparedStatementExceptId(t, ps);
        int lastParameterIndex = ps.getParameterMetaData().getParameterCount();
        ps.setInt(lastParameterIndex, t.getId());
    }


    public T insert(T t) throws DaoException {
        try (PreparedStatement ps = connection.prepareStatement(getQueryForInsert(), Statement.RETURN_GENERATED_KEYS)) {
            setVariablesForPreparedStatementExceptId(t, ps);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            t.setId(rs.getInt(1));
        } catch (SQLException e) {
            throw new DaoException("Couldn't insert Object to db", e);
        }
        return t;
    }

    public T findByPK(Integer id) throws DaoException {
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(SELECT_FROM + getTableName() + WHERE_ID + id)) {
            rs.next();
            return getObjectFromResultSet(rs);
        } catch (SQLException e) {
            throw new DaoException("Couldn't find object by current id", e);
        }
    }

    public Integer findColumnByPK(String columnName, Integer id) throws DaoException {
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("SELECT " + columnName + " FROM " + getTableName() + WHERE_ID + id)) {
            rs.next();
            return rs.getInt(columnName);
        } catch (SQLException e) {
            throw new DaoException("Couldn't find object by current id", e);
        }
    }

    public List<T> findAll() throws DaoException {
        List<T> objects = new ArrayList<>();
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(SELECT_FROM + getTableName() + " ORDER BY id")) {
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

    public void delete(Integer id) throws DaoException {
        try (Statement st = connection.createStatement()) {
            st.execute("UPDATE " + getTableName() + " SET deleted=1" + WHERE_ID + id);
        } catch (SQLException e) {
            throw new DaoException("Couldn't delete object by id", e);
        }
    }

    public void update(T t) throws DaoException {
        try (PreparedStatement ps = connection.prepareStatement(getQueryToUpdateById())) {
            setVariablesForPreparedStatement(t, ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Couldn't update Object in db", e);
        }
    }

    public void updateColumnByPK(String columnName, BigDecimal columnValue, Integer id) throws DaoException {
        try (Statement st = connection.createStatement()) {
            st.execute("UPDATE " + getTableName() + " SET " + columnName + "=" + columnValue + WHERE_ID + id);
        } catch (SQLException e) {
            throw new DaoException("Couldn't find object by current id", e);
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
        String resultQuery = SELECT_FROM + getTableName() + " WHERE ";
        for (Map.Entry<String, String> param : params.entrySet()) {
            if (params.size() == 1) {
                resultQuery += param.getKey() + " = '" + param.getValue() + "'";
                return resultQuery;
            } else {
                resultQuery += param.getKey() + " = '" + param.getValue() + "' AND ";
            }
        }
        return resultQuery.substring(0, resultQuery.length() - 5);
    }
}


