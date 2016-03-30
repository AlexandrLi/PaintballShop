package com.epam.alexandrli.paintballshop.dao;

import com.epam.alexandrli.paintballshop.entity.Image;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ImageDao extends AbstractJdbcDao<Image> {

    @Override
    protected Image prepareObject(ResultSet rs) throws DaoException {
        return null;
    }

    @Override
    public void insert(Image image) throws SQLException {

    }

    @Override
    public List<Image> readAll() {
        return null;
    }

    @Override
    public void update(Image image) {

    }

    @Override
    public void delete(Image image) {

    }
}
