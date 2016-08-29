package com.epam.alexandrli.paintballshop.dao.jdbc;

import com.epam.alexandrli.paintballshop.dao.DaoException;
import com.epam.alexandrli.paintballshop.entity.Characteristic;
import com.epam.alexandrli.paintballshop.entity.ProductType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcCharacteristicDao extends AbstractJdbcDao<Characteristic> {

    private static final String INSERT_CHARACTERISTIC = "INSERT INTO characteristic(name_ru, name_en, product_type_id) VALUES (?,?,?)";
    private static final String UPDATE_CHARACTERISTIC_BY_ID = "UPDATE characteristic SET name_ru=?, name_en=?, product_type_id=?  WHERE id=?";

    @Override
    protected String getQueryForInsert() {
        return INSERT_CHARACTERISTIC;
    }

    @Override
    protected String getQueryToUpdateById() {
        return UPDATE_CHARACTERISTIC_BY_ID;
    }

    @Override
    protected Characteristic getObjectFromResultSet(ResultSet rs) throws DaoException {
        Characteristic characteristic = new Characteristic();
        try {
            characteristic.setId(rs.getInt("id"));
            characteristic.setNameRu(rs.getString("name_ru"));
            characteristic.setNameEn(rs.getString("name_en"));
            characteristic.setDeleted(rs.getBoolean("deleted"));
            ProductType type = new ProductType(rs.getInt("product_type_id"));
            characteristic.setType(type);
        } catch (SQLException e) {
            throw new DaoException("Could not get object from result set", e);
        }
        return characteristic;
    }

    @Override
    protected void setVariablesForPreparedStatementExceptId(Characteristic characteristic, PreparedStatement ps) throws DaoException {
        try {
            ps.setString(1, characteristic.getNameRu());
            ps.setString(2, characteristic.getNameEn());
            ps.setInt(3, characteristic.getType().getId());
        } catch (SQLException e) {
            throw new DaoException("Could not set variables for prepared statement", e);
        }
    }

    @Override
    protected String getTableName() {
        return "characteristic";
    }

}
