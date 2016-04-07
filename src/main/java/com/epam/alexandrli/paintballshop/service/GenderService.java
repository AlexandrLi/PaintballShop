package com.epam.alexandrli.paintballshop.service;

import com.epam.alexandrli.paintballshop.dao.DaoFactory;
import com.epam.alexandrli.paintballshop.dao.GenericDao;
import com.epam.alexandrli.paintballshop.dao.jdbc.JdbcDaoFactory;
import com.epam.alexandrli.paintballshop.entity.Gender;

import java.sql.SQLException;
import java.util.List;

public class GenderService {
    private DaoFactory jdbcDaoFactory;
    private GenericDao<Gender> genderDao;

    public GenderService() throws SQLException {
        initDaoFactory();
        this.genderDao = jdbcDaoFactory.getDao(Gender.class);
    }

    private void initDaoFactory() throws SQLException {
        if (jdbcDaoFactory == null) {
            jdbcDaoFactory = new JdbcDaoFactory();
        }
    }

    public List<Gender> getAllGenders() throws SQLException {
        initDaoFactory();
        List<Gender> genders = genderDao.findAll();
        jdbcDaoFactory.close();
        return genders;
    }

}
