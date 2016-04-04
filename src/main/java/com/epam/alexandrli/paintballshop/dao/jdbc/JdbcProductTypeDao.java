package com.epam.alexandrli.paintballshop.dao.jdbc;

import com.epam.alexandrli.paintballshop.dao.DaoException;
import com.epam.alexandrli.paintballshop.entity.ProductType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcProductTypeDao extends AbstractJdbcDao<ProductType> {

    public static final String INSERT_PRODUCT_TYPE = "INSERT INTO product_type(name) VALUES (?)";
    public static final String UPDATE_PRODUCT_TYPE_BY_ID = "UPDATE product_type SET name=? WHERE id=?";

    @Override
    protected ProductType getObjectFromResultSet(ResultSet rs) throws SQLException {
        ProductType productType = new ProductType();
        productType.setId(rs.getInt("id"));
        productType.setName(rs.getString("name"));
        return productType;
    }

    @Override
    protected String getQueryForInsert() throws DaoException {
        return INSERT_PRODUCT_TYPE;
    }

    @Override
    protected void setVariablesForPreparedStatementExceptId(ProductType productType, PreparedStatement ps) throws SQLException {
        ps.setString(1, productType.getName());
    }

    @Override
    protected String getTableName() {
        return "product_type";
    }

    @Override
    protected String getQueryToUpdateById() {
        return UPDATE_PRODUCT_TYPE_BY_ID;
    }

}
