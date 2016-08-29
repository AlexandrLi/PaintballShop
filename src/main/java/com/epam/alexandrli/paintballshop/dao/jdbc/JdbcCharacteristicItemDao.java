package com.epam.alexandrli.paintballshop.dao.jdbc;

import com.epam.alexandrli.paintballshop.dao.DaoException;
import com.epam.alexandrli.paintballshop.entity.Characteristic;
import com.epam.alexandrli.paintballshop.entity.CharacteristicItem;
import com.epam.alexandrli.paintballshop.entity.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcCharacteristicItemDao extends AbstractJdbcDao<CharacteristicItem> {

    private static final String INSERT_CHARACTERISTIC = "INSERT INTO characteristic_item(value, characteristic_id, product_id) VALUES (?,?,?)";
    private static final String UPDATE_CHARACTERISTIC_BY_ID = "UPDATE characteristic_item SET value=?, characteristic_id=?, product_id=?  WHERE id=?";

    @Override
    protected String getQueryForInsert() {
        return INSERT_CHARACTERISTIC;
    }

    @Override
    protected String getQueryToUpdateById() {
        return UPDATE_CHARACTERISTIC_BY_ID;
    }

    @Override
    protected CharacteristicItem getObjectFromResultSet(ResultSet rs) throws DaoException {
        CharacteristicItem characteristicItem = new CharacteristicItem();
        try {
            characteristicItem.setId(rs.getInt("id"));
            characteristicItem.setValue(rs.getString("value"));
            Characteristic characteristic = new Characteristic(rs.getInt("characteristic_id"));
            characteristicItem.setCharacteristic(characteristic);
            Product product = new Product(rs.getInt("product_id"));
            characteristicItem.setProduct(product);
            characteristicItem.setDeleted(rs.getBoolean("deleted"));
        } catch (SQLException e) {
            throw new DaoException("Could not get object from result set", e);
        }
        return characteristicItem;
    }

    @Override
    protected void setVariablesForPreparedStatementExceptId(CharacteristicItem characteristicItem, PreparedStatement ps) throws DaoException {
        try {
            ps.setString(1, characteristicItem.getValue());
            ps.setInt(2, characteristicItem.getCharacteristic().getId());
            ps.setInt(3, characteristicItem.getProduct().getId());
        } catch (SQLException e) {
            throw new DaoException("Could not set variables for prepared statement", e);
        }
    }

    @Override
    protected String getTableName() {
        return "characteristic_item";
    }

}
