package com.epam.alexandrli.paintballshop.dao;

import com.epam.alexandrli.paintballshop.entity.Gender;
import com.epam.alexandrli.paintballshop.entity.User;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Runner {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        JdbcDaoFactory jdbcDaoFactory = new JdbcDaoFactory();
        GenericDao<Gender> genderDao = jdbcDaoFactory.getDao(Gender.class);
        GenericDao<User> userDao = jdbcDaoFactory.getDao(User.class);
        Map<String, String> params = new HashMap<>();
        params.put("id", "3");
        List<Gender> allGenders = genderDao.findAll();
        List<Gender> allGendersOnPage = genderDao.findAll(2, 2);
        List<Gender> allGenderByParams = genderDao.findAllByParams(params);
        Gender gender = new Gender();
        gender.setName("undefined");
        genderDao.insert(gender);
        genderDao.delete(7);
        Gender genderByPK = genderDao.findByPK(3);
        gender.setName("none");
        genderDao.update(gender);

    }
}
