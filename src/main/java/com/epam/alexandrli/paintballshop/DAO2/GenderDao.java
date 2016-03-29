package com.epam.alexandrli.paintballshop.DAO2;

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
    public Gender read(int id) throws SQLException {
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM gender WHERE id=" + id);
        Gender gender = new Gender();
        while (rs.next()) {
            gender.setId(rs.getInt("id"));
            gender.setName(rs.getString("name"));
        }
        return gender;
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
}
