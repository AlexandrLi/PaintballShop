package com.epam.alexandrli.paintballshop.dao.jdbc;

import com.epam.alexandrli.paintballshop.dao.DaoException;
import com.epam.alexandrli.paintballshop.entity.OrderStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcOrderStatusDao extends AbstractJdbcDao<OrderStatus> {

    public static final String INSERT_ORDER_STATUS = "INSERT INTO shopdb.order_status(name_ru, name_en) VALUES (?,?)";
    public static final String UPDATE_ORDER_STATUS_BY_ID = "UPDATE shopdb.order_status SET name_ru=?, name_en=? WHERE id=?";

    @Override
    protected OrderStatus getObjectFromResultSet(ResultSet rs) throws SQLException {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setId(rs.getInt("id"));
        orderStatus.setNameRu(rs.getString("name_ru"));
        orderStatus.setNameEn(rs.getString("name_en"));
        orderStatus.setDeleted(rs.getBoolean("deleted"));
        return orderStatus;
    }

    @Override
    protected String getQueryForInsert() throws DaoException {
        return INSERT_ORDER_STATUS;
    }

    @Override
    protected void setVariablesForPreparedStatementExceptId(OrderStatus orderStatus, PreparedStatement ps) throws SQLException {
        ps.setString(1, orderStatus.getNameRu());
        ps.setString(2, orderStatus.getNameEn());
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
