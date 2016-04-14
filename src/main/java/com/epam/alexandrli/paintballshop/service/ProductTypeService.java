package com.epam.alexandrli.paintballshop.service;

import com.epam.alexandrli.paintballshop.dao.DaoFactory;
import com.epam.alexandrli.paintballshop.dao.GenericDao;
import com.epam.alexandrli.paintballshop.dao.jdbc.JdbcDaoFactory;
import com.epam.alexandrli.paintballshop.entity.ProductType;

import java.sql.SQLException;
import java.util.List;

public class ProductTypeService {
    private DaoFactory jdbcDaoFactory;
    private GenericDao<ProductType> productTypeDao;

    public ProductTypeService() throws SQLException {
        jdbcDaoFactory = new JdbcDaoFactory();
        this.productTypeDao = jdbcDaoFactory.getDao(ProductType.class);
        jdbcDaoFactory.close();
    }

    public List<ProductType> getAllProductTypes() throws SQLException {
        jdbcDaoFactory.getConnection();
        List<ProductType> productTypes = productTypeDao.findAll();
        jdbcDaoFactory.close();
        return productTypes;
    }

    public ProductType getProductTypeById(Integer id) throws SQLException {
        jdbcDaoFactory.getConnection();
        ProductType productType = productTypeDao.findByPK(id);
        jdbcDaoFactory.close();
        return productType;
    }
}
