package com.epam.alexandrli.paintballshop.dao;

import com.epam.alexandrli.paintballshop.entity.Order;
import org.joda.time.DateTime;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDao extends AbstractJdbcDao<Order> {

    public static final String INSERT_ORDER = "INSERT `order`(user_id, date, description) VALUES (?,?,?)";
    public static final String UPDATE_ORDER_BY_ID = "UPDATE `order` SET date=?, description=? WHERE id=?";

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
        order.setDate(DateTime.parse(rs.getString("date")));
        order.setDescription(rs.getString("description"));
        return order;
    }

    @Override
    protected void setVariablesForPreparedStatementExceptId(Order order, PreparedStatement ps) throws SQLException {

        ps.setInt(1, order.getUser().getId());
        // TODO: 02.04.2016 Type conversation for Datetime 
//        ps.setDate(2, order.getDate().toDateTime());
        ps.setString(3, order.getDescription());
    }

    @Override
    protected String getTableName() {
        return "description";
    }
}
