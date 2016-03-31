package com.epam.alexandrli.paintballshop.dao;

import java.util.List;
import java.util.Map;

public interface GenericDao<T> {

    void insert(T t) throws DaoException;

    T findByPK(int id) throws DaoException;

    List<T> findAllByParams(Map<String, String> params) throws DaoException;

    List<T> findAll() throws DaoException;

    List<T> findAll(int pageNumber, int pageSize) throws DaoException;

    void update(T t) throws DaoException;

    void delete(int id) throws DaoException;
}
