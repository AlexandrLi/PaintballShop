package com.epam.alexandrli.paintballshop.dao;

import com.epam.alexandrli.paintballshop.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductDao extends AbstractJdbcDao<Product> {

    @Override
    protected Product prepareObject(ResultSet rs) throws DaoException {
        return null;
    }

    @Override
    public void insert(Product product) throws SQLException {

    }

    @Override
    public List<Product> readAll() {
        return null;
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(Product product) {

    }
}
