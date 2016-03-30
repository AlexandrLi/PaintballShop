package com.epam.alexandrli.paintballshop.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T> {

    void insert(T t) throws SQLException;

    T read(int id) throws DaoException;

    List<T> readAll();

    void update(T t);

    void delete(T t);
}
