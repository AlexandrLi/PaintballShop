package com.epam.alexandrli.paintballshop.dao;

import com.epam.alexandrli.paintballshop.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao extends AbstractJdbcDao<User> {

    @Override
    protected User prepareObject(ResultSet rs) throws DaoException {
        return null;
    }

    @Override
    public void insert(User user) throws SQLException {

    }

    @Override
    public List<User> readAll() {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }
}
