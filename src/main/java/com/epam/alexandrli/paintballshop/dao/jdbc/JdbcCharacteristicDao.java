package com.epam.alexandrli.paintballshop.dao.jdbc;

import com.epam.alexandrli.paintballshop.entity.Characteristic;
import com.epam.alexandrli.paintballshop.entity.ProductType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcCharacteristicDao extends AbstractJdbcDao<Characteristic> {

    public static final String INSERT_CHARACTERISTIC = "INSERT INTO shopdb.characteristic(name_ru, name_en, product_type_id) VALUES (?,?,?)";
    public static final String UPDATE_CHARACTERISTIC_BY_ID = "UPDATE shopdb.characteristic SET name_ru=?, name_en=?, product_type_id=?  WHERE id=?";

    @Override
    protected String getQueryForInsert() {
        return INSERT_CHARACTERISTIC;
    }

    @Override
    protected String getQueryToUpdateById() {
        return UPDATE_CHARACTERISTIC_BY_ID;
    }

    @Override
    protected Characteristic getObjectFromResultSet(ResultSet rs) throws SQLException {
        Characteristic characteristic = new Characteristic();
        characteristic.setId(rs.getInt("id"));
        characteristic.setNameRu(rs.getString("name_ru"));
        characteristic.setNameEn(rs.getString("name_en"));
        characteristic.setDeleted(rs.getBoolean("deleted"));
        ProductType type = new ProductType(rs.getInt("product_type_id"));
        characteristic.setType(type);
        return characteristic;
    }

    @Override
    protected void setVariablesForPreparedStatementExceptId(Characteristic characteristic, PreparedStatement ps) throws SQLException {
        ps.setString(1, characteristic.getNameRu());
        ps.setString(2, characteristic.getNameEn());
        ps.setInt(3, characteristic.getType().getId());
    }

    @Override
    protected String getTableName() {
        return "characteristic";
    }

}
