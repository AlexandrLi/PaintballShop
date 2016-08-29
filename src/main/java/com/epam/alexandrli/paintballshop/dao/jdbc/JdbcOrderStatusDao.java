package com.epam.alexandrli.paintballshop.dao.jdbc;

import com.epam.alexandrli.paintballshop.dao.DaoException;
import com.epam.alexandrli.paintballshop.entity.OrderStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcOrderStatusDao extends AbstractJdbcDao<OrderStatus> {

    private static final String INSERT_ORDER_STATUS = "INSERT INTO order_status(name_ru, name_en) VALUES (?,?)";
    private static final String UPDATE_ORDER_STATUS_BY_ID = "UPDATE order_status SET name_ru=?, name_en=? WHERE id=?";

    @SuppressWarnings("Duplicates")
    @Override
    protected OrderStatus getObjectFromResultSet(ResultSet rs) throws DaoException {
        OrderStatus orderStatus = new OrderStatus();
        try {
            orderStatus.setId(rs.getInt("id"));
            orderStatus.setNameRu(rs.getString("name_ru"));
            orderStatus.setNameEn(rs.getString("name_en"));
            orderStatus.setDeleted(rs.getBoolean("deleted"));
        } catch (SQLException e) {
            throw new DaoException("Could not get object from result set", e);
        }
        return orderStatus;
    }

    @Override
    protected String getQueryForInsert() {
        return INSERT_ORDER_STATUS;
    }

    @Override
    protected void setVariablesForPreparedStatementExceptId(OrderStatus orderStatus, PreparedStatement ps) throws DaoException {
        try {
            ps.setString(1, orderStatus.getNameRu());
            ps.setString(2, orderStatus.getNameEn());
        } catch (SQLException e) {
            throw new DaoException("Could not set variables for prepared statement", e);
        }
    }

    @Override
    protected String getTableName() {
        return "order_status";
    }

    @Override
    protected String getQueryToUpdateById() {
        return UPDATE_ORDER_STATUS_BY_ID;
    }

}
