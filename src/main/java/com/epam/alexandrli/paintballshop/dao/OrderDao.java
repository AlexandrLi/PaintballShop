package com.epam.alexandrli.paintballshop.dao;

import com.epam.alexandrli.paintballshop.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDao extends AbstractJdbcDao<Order> {

    @Override
    protected Order prepareObject(ResultSet rs) throws DaoException {
        return null;
    }

    @Override
    public void insert(Order order) throws SQLException {

    }

    @Override
    public List<Order> readAll() {
        return null;
    }

    @Override
    public void update(Order order) {

    }

    @Override
    public void delete(Order order) {

    }
}
