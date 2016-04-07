package com.epam.alexandrli.paintballshop.dao.jdbc;

import com.epam.alexandrli.paintballshop.entity.Product;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcProductDao extends AbstractJdbcDao<Product> {

    public static final String INSERT_PRODUCT_ITEM = "INSERT product(name, price, description_ru, description_en, product_type_id) VALUES (?,?,?,?,?)";
    public static final String UPDATE_PRODUCT_BY_ID = "UPDATE product SET name=?, price=?, description_ru=?,description_en=? WHERE id=?";

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
        product.setDescriptionRu(rs.getString("description_ru"));
        product.setDescriptionEn(rs.getString("description_en"));
        return product;
    }

    @Override
    protected void setVariablesForPreparedStatementExceptId(Product product, PreparedStatement ps) throws SQLException {
        ps.setString(1, product.getName());
        ps.setBigDecimal(2, product.getPrice().getAmount());
        ps.setString(3, product.getDescriptionRu());
        ps.setString(4, product.getDescriptionEn());
        ps.setInt(5, product.getType().getId());
    }

    @Override
    protected String getTableName() {
        return "product";
    }

}
