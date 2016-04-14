package com.epam.alexandrli.paintballshop.dao.jdbc;

import com.epam.alexandrli.paintballshop.dao.DaoException;
import com.epam.alexandrli.paintballshop.entity.ProductType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcProductTypeDao extends AbstractJdbcDao<ProductType> {

    public static final String INSERT_PRODUCT_TYPE = "INSERT INTO shopdb.product_type(name_ru, name_en) VALUES (?,?)";
    public static final String UPDATE_PRODUCT_TYPE_BY_ID = "UPDATE shopdb.product_type SET name_ru=?,name_en=? WHERE id=?";

    @Override
    protected ProductType getObjectFromResultSet(ResultSet rs) throws SQLException {
        ProductType productType = new ProductType();
        productType.setId(rs.getInt("id"));
        productType.setNameRu(rs.getString("name_ru"));
        productType.setNameEn(rs.getString("name_en"));
        productType.setDeleted(rs.getBoolean("deleted"));
        return productType;
    }

    @Override
    protected String getQueryForInsert() throws DaoException {
        return INSERT_PRODUCT_TYPE;
    }

    @Override
    protected void setVariablesForPreparedStatementExceptId(ProductType productType, PreparedStatement ps) throws SQLException {
        ps.setString(1, productType.getNameRu());
        ps.setString(2, productType.getNameEn());
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
