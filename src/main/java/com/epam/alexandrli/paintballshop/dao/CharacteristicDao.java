package com.epam.alexandrli.paintballshop.dao;

import com.epam.alexandrli.paintballshop.entity.Characteristic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CharacteristicDao extends AbstractJdbcDao<Characteristic> {
    @Override
    public void insert(Characteristic characteristic) throws SQLException {

    }


    @Override
    protected Characteristic prepareObject(ResultSet rs) throws DaoException {
        Characteristic characteristic = new Characteristic();
        try {
            while (rs.next()) {
                characteristic.setId(rs.getInt("id"));
                characteristic.setName(rs.getString("name"));
//                characteristic.setType(rs.getString("type"));
            }
        } catch (SQLException e) {
            throw new DaoException("Couldn't set object variables from db", e);
        }
        return characteristic;
    }


    @Override
    public List<Characteristic> readAll() {
        return null;
    }

    @Override
    public void update(Characteristic characteristic) {

    }

    @Override
    public void delete(Characteristic characteristic) {

    }
}
