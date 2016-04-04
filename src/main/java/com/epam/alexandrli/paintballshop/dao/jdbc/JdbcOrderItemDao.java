package com.epam.alexandrli.paintballshop.dao.jdbc;

import com.epam.alexandrli.paintballshop.entity.OrderItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcOrderItemDao extends AbstractJdbcDao<OrderItem> {

    public static final String INSERT_ORDER_ITEM = "INSERT order_item(amount, order_id, product_id) VALUES (?,?,?)";
    public static final String UPDATE_ORDER_ITEM_BY_ID = "UPDATE order_item SET amount=? WHERE id=?";

    @Override
    protected String getQueryForInsert() {
        return INSERT_ORDER_ITEM;
    }

    @Override
    protected String getQueryToUpdateById() {
        return UPDATE_ORDER_ITEM_BY_ID;
    }

    @Override
    protected OrderItem getObjectFromResultSet(ResultSet rs) throws SQLException {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(rs.getInt("id"));
        orderItem.setAmount(rs.getInt("amount"));
        return orderItem;
    }

    @Override
    protected void setVariablesForPreparedStatementExceptId(OrderItem orderItem, PreparedStatement ps) throws SQLException {
        ps.setInt(1, orderItem.getAmount());
        ps.setInt(2, orderItem.getOrder().getId());
        ps.setInt(3, orderItem.getProduct().getId());
    }

    @Override
    protected String getTableName() {
        return "order_item";
    }

}
