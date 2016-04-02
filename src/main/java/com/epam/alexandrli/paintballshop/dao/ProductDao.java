package com.epam.alexandrli.paintballshop.dao;

import com.epam.alexandrli.paintballshop.entity.Product;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDao extends AbstractJdbcDao<Product> {

    public static final String INSERT_PRODUCT_ITEM = "INSERT product(name, price, description, product_type_id) VALUES (?,?,?,?)";
    public static final String UPDATE_PRODUCT_BY_ID = "UPDATE product SET name=?, price=?, description=? WHERE id=?";

    @Override
    protected String getQueryForInsert() {
        return INSERT_PRODUCT_ITEM;
    }

    @Override
    protected String getQueryToUpdateById() {
        return UPDATE_PRODUCT_BY_ID;
    }

    @Override
    protected Product getObjectFromResultSet(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setPrice(Money.of(CurrencyUnit.getInstance("KZT"), rs.getBigDecimal("price")));
        product.setDescription(rs.getString("description"));
        return product;
    }

    @Override
    protected void setVariablesForPreparedStatementExceptId(Product product, PreparedStatement ps) throws SQLException {
        ps.setString(1, product.getName());
        ps.setBigDecimal(2, product.getPrice().getAmount());
        ps.setString(3, product.getDescription());
        ps.setInt(4, product.getType().getId());
    }

    @Override
    protected String getTableName() {
        return "product";
    }
}
