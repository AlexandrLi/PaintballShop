package com.epam.alexandrli.paintballshop.dao;

import com.epam.alexandrli.paintballshop.entity.Gender;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class GenderDao extends AbstractJdbcDao<Gender> {
    @Override
    public void insert(Gender gender) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO gender(name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, gender.getName());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        while (rs.next()) {
            gender.setId(rs.getInt(1));
        }
    }

    @Override
    public List<Gender> readAll() {
        return null;
    }

    @Override
    public void update(Gender gender) {

    }

    @Override
    public void delete(Gender gender) {

    }

    @Override
    protected Gender prepareObject(ResultSet rs) throws DaoException {
        Gender gender = new Gender();
        try {
            while (rs.next()) {
                gender.setId(rs.getInt("id"));
                gender.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new DaoException("Couldn't set object variables from db", e);
        }
        return gender;
    }
}
