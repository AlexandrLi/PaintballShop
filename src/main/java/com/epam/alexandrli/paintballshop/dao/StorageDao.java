package com.epam.alexandrli.paintballshop.dao;

import com.epam.alexandrli.paintballshop.entity.Storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StorageDao extends AbstractJdbcDao<Storage> {

    public static final String INSERT_STORAGE = "INSERT storage(name, description) VALUES (?,?)";
    public static final String UPDATE_STORAGE_BY_ID = "UPDATE storage SET name=?, description=? WHERE id=?";

    @Override
    protected String getQueryForInsert() {
        return INSERT_STORAGE;
    }

    @Override
    protected String getQueryToUpdateById() {
        return UPDATE_STORAGE_BY_ID;
    }

    @Override
    protected Storage getObjectFromResultSet(ResultSet rs) throws SQLException {
        Storage storage = new Storage();
        storage.setId(rs.getInt("id"));
        storage.setName(rs.getString("name"));
        storage.setDescription(rs.getString("description"));
        return storage;
    }

    @Override
    protected void setVariablesForPreparedStatementExceptId(Storage storage, PreparedStatement ps) throws SQLException {

        ps.setString(1, storage.getName());
        ps.setString(2, storage.getDescription());
    }

    @Override
    protected String getTableName() {
        return "description";
    }
}
