package com.epam.alexandrli.paintballshop.dao.jdbc;

import com.epam.alexandrli.paintballshop.entity.Address;
import com.epam.alexandrli.paintballshop.entity.Gender;
import com.epam.alexandrli.paintballshop.entity.User;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUserDao extends AbstractJdbcDao<User> {

    public static final String INSERT_USER = "INSERT INTO shopdb.user(email, password, role, first_name, last_name, phone_number, gender_id, cash, address_id) VALUES (?,?,?,?,?,?,?,?,?)";
    public static final String UPDATE_USER_BY_ID = "UPDATE shopdb.user SET email=?, password=?, role=?, first_name=?, last_name=?, phone_number=?, gender_id=?, cash=?, address_id=? WHERE id=?";

    @Override
    protected User getObjectFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setRole(User.Role.valueOf(rs.getString("role")));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setPhoneNumber(rs.getString("phone_number"));
        Gender userGender = new Gender();
        userGender.setId(rs.getInt("gender_id"));
        user.setGender(userGender);
        user.setCash(Money.of(CurrencyUnit.getInstance("KZT"), rs.getBigDecimal("cash")));
        Address address = new Address(rs.getInt("address_id"));
        user.setAddress(address);
        user.setDeleted(rs.getBoolean("deleted"));
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
        ps.setString(3, String.valueOf(user.getRole()));
        ps.setString(4, user.getFirstName());
        ps.setString(5, user.getLastName());
        ps.setString(6, user.getPhoneNumber());
        ps.setInt(7, user.getGender().getId());
        ps.setBigDecimal(8, user.getCash().getAmount());
        ps.setInt(9, user.getAddress().getId());
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
