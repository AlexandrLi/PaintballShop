package com.epam.alexandrli.paintballshop.dao;

import com.epam.alexandrli.paintballshop.entity.BaseEntity;

import java.util.List;
import java.util.Map;

public interface GenericDao<T extends BaseEntity> {

    T insert(T t) throws DaoException;

    T findByPK(Integer id) throws DaoException;

    List<T> findAllByParams(Map<String, String> params) throws DaoException;

    List<T> findAll() throws DaoException;

    Integer findColumnByPK(String columnName, Integer id) throws DaoException;

    List<T> findAll(int pageNumber, int pageSize) throws DaoException;

    void update(T t) throws DaoException;

    void delete(Integer id) throws DaoException;

}
