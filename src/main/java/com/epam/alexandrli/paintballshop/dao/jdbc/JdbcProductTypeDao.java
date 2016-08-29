package com.epam.alexandrli.paintballshop.dao.jdbc;

import com.epam.alexandrli.paintballshop.dao.DaoException;
import com.epam.alexandrli.paintballshop.entity.ProductType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcProductTypeDao extends AbstractJdbcDao<ProductType> {

    private static final String INSERT_PRODUCT_TYPE = "INSERT INTO product_type(name_ru, name_en) VALUES (?,?)";
    private static final String UPDATE_PRODUCT_TYPE_BY_ID = "UPDATE product_type SET name_ru=?,name_en=? WHERE id=?";

    @SuppressWarnings("Duplicates")
    @Override
    protected ProductType getObjectFromResultSet(ResultSet rs) throws DaoException {
        ProductType productType = new ProductType();
        try {
            productType.setId(rs.getInt("id"));
            productType.setNameRu(rs.getString("name_ru"));
            productType.setNameEn(rs.getString("name_en"));
            productType.setDeleted(rs.getBoolean("deleted"));
        } catch (SQLException e) {
            throw new DaoException("Could not get object from result set", e);
        }
        return productType;
    }

    @Override
    protected String getQueryForInsert() {
        return INSERT_PRODUCT_TYPE;
    }

    @Override
    protected void setVariablesForPreparedStatementExceptId(ProductType productType, PreparedStatement ps) throws DaoException {
        try {
            ps.setString(1, productType.getNameRu());
            ps.setString(2, productType.getNameEn());
        } catch (SQLException e) {
            throw new DaoException("Could not set variables for prepared statement", e);
        }
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
