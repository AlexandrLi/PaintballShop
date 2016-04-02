package com.epam.alexandrli.paintballshop.dao;

import com.epam.alexandrli.paintballshop.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends AbstractJdbcDao<User> {

    public static final String INSERT_USER = "INSERT INTO user(email, password, first_name, last_name, phone_number, gender_id) VALUES (?,?,?,?,?,?)";
    public static final String UPDATE_USER_BY_ID = "UPDATE user SET email=?, password=?, first_name=?, last_name=?, gender_id=?, phone_number=? WHERE id=?";

    @Override
    protected User getObjectFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setPhoneNumber(rs.getString("phone_number"));
        return user;
    }

    @Override
    protected String getQueryForInsert() {
        return INSERT_USER;
    }

    @Override
    protected void setVariablesForPreparedStatementExceptId(User user, PreparedStatement ps) throws SQLException {
        ps.setString(1, user.getEmail());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getFirstName());
        ps.setString(4, user.getLastName());
        ps.setString(5, user.getPhoneNumber());
        ps.setInt(6, user.getGender().getId());
    }

    @Override
    protected String getQueryToUpdateById() {
        return UPDATE_USER_BY_ID;
    }

    @Override
    protected String getTableName() {
        return "user";
    }

}
