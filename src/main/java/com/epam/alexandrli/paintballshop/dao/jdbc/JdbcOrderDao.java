package com.epam.alexandrli.paintballshop.dao.jdbc;

import com.epam.alexandrli.paintballshop.entity.Order;
import org.joda.time.DateTime;

import java.sql.*;

public class JdbcOrderDao extends AbstractJdbcDao<Order> {

    public static final String INSERT_ORDER = "INSERT `order`(user_id,created, description) VALUES (?,?,?)";
    public static final String UPDATE_ORDER_BY_ID = "UPDATE `order` SET created=?, description=? WHERE id=?";

    @Override
    protected String getQueryForInsert() {
        return INSERT_ORDER;
    }

    @Override
    protected String getQueryToUpdateById() {
        return UPDATE_ORDER_BY_ID;
    }

    @Override
    protected Order getObjectFromResultSet(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt("id"));
        order.setCreated(new DateTime(rs.getTimestamp("created")));
        order.setDescription(rs.getString("description"));
        return order;
    }

    @Override
    protected void setVariablesForPreparedStatementExceptId(Order order, PreparedStatement ps) throws SQLException {
        ps.setInt(1, order.getUser().getId());
        ps.setTimestamp(2, new Timestamp(order.getCreated().getMillis()));
        ps.setString(3, order.getDescription());
    }

    @Override
    protected String getTableName() {
        return "`order`";
    }

}
