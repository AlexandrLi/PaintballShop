package com.epam.alexandrli.paintballshop.dao.jdbc;

import com.epam.alexandrli.paintballshop.entity.Order;
import com.epam.alexandrli.paintballshop.entity.OrderStatus;
import com.epam.alexandrli.paintballshop.entity.User;
import org.joda.time.DateTime;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class JdbcOrderDao extends AbstractJdbcDao<Order> {

    public static final String INSERT_ORDER = "INSERT INTO shopdb.order(created, user_id, description, order_status_id) VALUES (?,?,?,?)";
    public static final String UPDATE_ORDER_BY_ID = "UPDATE shopdb.order SET created=?, user_id=?, description=?, order_status_id=? WHERE id=?";

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
        User user = new User(rs.getInt("user_id"));
        order.setUser(user);
        order.setDescription(rs.getString("description"));
        OrderStatus status = new OrderStatus(rs.getInt("order_status_id"));
        order.setStatus(status);
        order.setDeleted(rs.getBoolean("deleted"));
        return order;
    }

    @Override
    protected void setVariablesForPreparedStatementExceptId(Order order, PreparedStatement ps) throws SQLException {
        ps.setTimestamp(1, new Timestamp(order.getCreated().getMillis()));
        ps.setInt(2, order.getUser().getId());
        ps.setString(3, order.getDescription());
        ps.setInt(4, order.getStatus().getId());
    }

    @Override
    protected String getTableName() {
        return "`order`";
    }

}
