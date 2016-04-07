package com.epam.alexandrli.paintballshop.dao.jdbc;

import com.epam.alexandrli.paintballshop.dao.DaoException;
import com.epam.alexandrli.paintballshop.entity.Gender;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcGenderDao extends AbstractJdbcDao<Gender> {

    public static final String INSERT_GENDER = "INSERT INTO gender(name_ru, name_en) VALUES (?,?)";
    public static final String UPDATE_GENDER_BY_ID = "UPDATE gender SET name_ru=?, name_en=? WHERE id=?";

    @Override
    protected Gender getObjectFromResultSet(ResultSet rs) throws SQLException {
        Gender gender = new Gender();
        gender.setId(rs.getInt("id"));
        gender.setNameRu(rs.getString("name_ru"));
        gender.setNameEn(rs.getString("name_en"));
        return gender;
    }

    @Override
    protected String getQueryForInsert() throws DaoException {
        return INSERT_GENDER;
    }

    @Override
    protected void setVariablesForPreparedStatementExceptId(Gender gender, PreparedStatement ps) throws SQLException {
        ps.setString(1, gender.getNameRu());
        ps.setString(2, gender.getNameEn());
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
