package com.epam.alexandrli.paintballshop.dao;

import com.epam.alexandrli.paintballshop.entity.Storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StorageDao extends AbstractJdbcDao<Storage> {

    @Override
    protected Storage prepareObject(ResultSet rs) throws DaoException {
        return null;
    }

    @Override
    public void insert(Storage storage) throws SQLException {

    }

    @Override
    public List<Storage> readAll() {
        return null;
    }

    @Override
    public void update(Storage storage) {

    }

    @Override
    public void delete(Storage storage) {

    }
}
