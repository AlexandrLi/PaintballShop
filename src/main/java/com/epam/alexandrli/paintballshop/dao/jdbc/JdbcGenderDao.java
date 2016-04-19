package com.epam.alexandrli.paintballshop.dao.jdbc;

import com.epam.alexandrli.paintballshop.dao.DaoException;
import com.epam.alexandrli.paintballshop.entity.Gender;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcGenderDao extends AbstractJdbcDao<Gender> {

    public static final String INSERT_GENDER = "INSERT INTO shopdb.gender(name_ru, name_en) VALUES (?,?)";
    public static final String UPDATE_GENDER_BY_ID = "UPDATE shopdb.gender SET name_ru=?, name_en=? WHERE id=?";

    @SuppressWarnings("Duplicates")
    @Override
    protected Gender getObjectFromResultSet(ResultSet rs) throws DaoException {
        Gender gender = new Gender();
        try {
            gender.setId(rs.getInt("id"));
            gender.setNameRu(rs.getString("name_ru"));
            gender.setNameEn(rs.getString("name_en"));
            gender.setDeleted(rs.getBoolean("deleted"));
        } catch (SQLException e) {
            throw new DaoException("Could not get object from result set", e);
        }
        return gender;
    }

    @Override
    protected String getQueryForInsert() {
        return INSERT_GENDER;
    }

    @Override
    protected void setVariablesForPreparedStatementExceptId(Gender gender, PreparedStatement ps) throws DaoException {
        try {
            ps.setString(1, gender.getNameRu());
            ps.setString(2, gender.getNameEn());
        } catch (SQLException e) {
            throw new DaoException("Could not set variables for prepared statment", e);
        }
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
