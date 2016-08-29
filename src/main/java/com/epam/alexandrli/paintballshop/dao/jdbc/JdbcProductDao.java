package com.epam.alexandrli.paintballshop.dao.jdbc;

import com.epam.alexandrli.paintballshop.dao.DaoException;
import com.epam.alexandrli.paintballshop.entity.Product;
import com.epam.alexandrli.paintballshop.entity.ProductType;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcProductDao extends AbstractJdbcDao<Product> {

    private static final String INSERT_PRODUCT_ITEM = "INSERT INTO product(name, price, description_ru, description_en, product_type_id) VALUES (?,?,?,?,?)";
    private static final String UPDATE_PRODUCT_BY_ID = "UPDATE product SET name=?, price=?, description_ru=?,description_en=?, product_type_id=? WHERE id=?";

    @Override
    protected String getQueryForInsert() {
        return INSERT_PRODUCT_ITEM;
    }

    @Override
    protected String getQueryToUpdateById() {
        return UPDATE_PRODUCT_BY_ID;
    }

    @Override
    protected Product getObjectFromResultSet(ResultSet rs) throws DaoException {
        Product product = new Product();
        try {
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setPrice(Money.of(CurrencyUnit.getInstance("KZT"), rs.getBigDecimal("price")));
            product.setDescriptionRu(rs.getString("description_ru"));
            product.setDescriptionEn(rs.getString("description_en"));
            ProductType type = new ProductType(rs.getInt("product_type_id"));
            product.setType(type);
            product.setDeleted(rs.getBoolean("deleted"));
        } catch (SQLException e) {
            throw new DaoException("Could not get object from result set", e);
        }
        return product;
    }

    @Override
    protected void setVariablesForPreparedStatementExceptId(Product product, PreparedStatement ps) throws DaoException {
        try {
            ps.setString(1, product.getName());
            ps.setBigDecimal(2, product.getPrice().getAmount());
            ps.setString(3, product.getDescriptionRu());
            ps.setString(4, product.getDescriptionEn());
            ps.setInt(5, product.getType().getId());
        } catch (SQLException e) {
            throw new DaoException("Could not set variables for prepared statement", e);
        }
    }

    @Override
    protected String getTableName() {
        return "product";
    }

}
