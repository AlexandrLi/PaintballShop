package com.epam.alexandrli.paintballshop.dao;

import com.epam.alexandrli.paintballshop.entity.Gender;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GenderDao extends AbstractJdbcDao<Gender> {

    public static final String INSERT_GENDER = "INSERT INTO gender(name) VALUES (?)";
    public static final String UPDATE_GENDER_BY_ID = "UPDATE gender SET name=? WHERE id=?";

    @Override
    protected Gender getObjectFromResultSet(ResultSet rs) throws SQLException {
        Gender gender = new Gender();
        gender.setId(rs.getInt("id"));
        gender.setName(rs.getString("name"));
        return gender;
    }

    @Override
    protected String getQueryForInsert() throws DaoException {
        return INSERT_GENDER;
    }

    @Override
    protected void setVariablesForPreparedStatementExceptId(Gender gender, PreparedStatement ps) throws SQLException {
        ps.setString(1, gender.getName());
    }

    @Override
    protected String getTableName() {
        return "gender";
    }

    @Override
    protected String getQueryToUpdateById() {
        return UPDATE_GENDER_BY_ID;
    }

}
