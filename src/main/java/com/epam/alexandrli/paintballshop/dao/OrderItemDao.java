package com.epam.alexandrli.paintballshop.dao;

import com.epam.alexandrli.paintballshop.entity.OrderItem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderItemDao extends AbstractJdbcDao<OrderItem> {

    @Override
    protected OrderItem prepareObject(ResultSet rs) throws DaoException {
        return null;
    }

    @Override
    public void insert(OrderItem orderItem) throws SQLException {

    }

    @Override
    public List<OrderItem> readAll() {
        return null;
    }

    @Override
    public void update(OrderItem orderItem) {

    }

    @Override
    public void delete(OrderItem orderItem) {

    }
}
