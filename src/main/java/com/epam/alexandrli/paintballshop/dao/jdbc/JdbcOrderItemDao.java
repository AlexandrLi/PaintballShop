package com.epam.alexandrli.paintballshop.dao.jdbc;

import com.epam.alexandrli.paintballshop.dao.DaoException;
import com.epam.alexandrli.paintballshop.entity.Order;
import com.epam.alexandrli.paintballshop.entity.OrderItem;
import com.epam.alexandrli.paintballshop.entity.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcOrderItemDao extends AbstractJdbcDao<OrderItem> {

    public static final String INSERT_ORDER_ITEM = "INSERT INTO shopdb.order_item(amount, order_id, product_id) VALUES (?,?,?)";
    public static final String UPDATE_ORDER_ITEM_BY_ID = "UPDATE shopdb.order_item SET amount=?, order_id=?, product_id=? WHERE id=?";

    @Override
    protected String getQueryForInsert() {
        return INSERT_ORDER_ITEM;
    }

    @Override
    protected String getQueryToUpdateById() {
        return UPDATE_ORDER_ITEM_BY_ID;
    }

    @Override
    protected OrderItem getObjectFromResultSet(ResultSet rs) throws DaoException {
        OrderItem orderItem = new OrderItem();
        try {
            orderItem.setId(rs.getInt("id"));
            orderItem.setAmount(rs.getInt("amount"));
            Order order = new Order(rs.getInt("order_id"));
            orderItem.setOrder(order);
            Product product = new Product(rs.getInt("product_id"));
            orderItem.setProduct(product);
            orderItem.setDeleted(rs.getBoolean("deleted"));
        } catch (SQLException e) {
            throw new DaoException("Could not get object from result set", e);
        }
        return orderItem;
    }

    @Override
    protected void setVariablesForPreparedStatementExceptId(OrderItem orderItem, PreparedStatement ps) throws DaoException {
        try {
            ps.setInt(1, orderItem.getAmount());
            ps.setInt(2, orderItem.getOrder().getId());
            ps.setInt(3, orderItem.getProduct().getId());
        } catch (SQLException e) {
            throw new DaoException("Could not set variables for prepared statement", e);
        }
    }

    @Override
    protected String getTableName() {
        return "order_item";
    }

}
