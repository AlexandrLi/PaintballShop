package com.epam.alexandrli.paintballshop.dao;

import com.epam.alexandrli.paintballshop.entity.ProductType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductTypeDao extends AbstractJdbcDao<ProductType> {

    @Override
    protected ProductType prepareObject(ResultSet rs) throws DaoException {
        return null;
    }

    @Override
    public void insert(ProductType productType) throws SQLException {

    }

    @Override
    public List<ProductType> readAll() {
        return null;
    }

    @Override
    public void update(ProductType productType) {

    }

    @Override
    public void delete(ProductType productType) {

    }
}
