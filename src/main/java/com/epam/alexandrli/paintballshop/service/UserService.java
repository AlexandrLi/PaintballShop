package com.epam.alexandrli.paintballshop.service;

import com.epam.alexandrli.paintballshop.dao.DaoFactory;
import com.epam.alexandrli.paintballshop.dao.GenericDao;
import com.epam.alexandrli.paintballshop.dao.jdbc.JdbcDaoFactory;
import com.epam.alexandrli.paintballshop.entity.Address;
import com.epam.alexandrli.paintballshop.entity.Gender;
import com.epam.alexandrli.paintballshop.entity.User;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
    private DaoFactory jdbcDaoFactory;
    private GenericDao<User> userDao;
    private GenericDao<Gender> genderDao;
    private GenericDao<Address> addressDao;

    public UserService() throws SQLException {
        initDaoFactory();
        this.userDao = jdbcDaoFactory.getDao(User.class);
        this.genderDao = jdbcDaoFactory.getDao(Gender.class);
        this.addressDao = jdbcDaoFactory.getDao(Address.class);
    }

    private void initDaoFactory() throws SQLException {
        if (jdbcDaoFactory == null) {
            jdbcDaoFactory = new JdbcDaoFactory();
        }
    }

    public User registerUser(User user) throws SQLException {
        initDaoFactory();
        User registeredUser = userDao.insert(user);
        jdbcDaoFactory.close();
        return registeredUser;
    }

    public User performUserLogin(String email, String password) throws SQLException {
        initDaoFactory();
        Map<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
        List<User> users = userDao.findAllByParams(params);
        jdbcDaoFactory.close();
        if (!users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }

    public User getUserById(Integer id) throws SQLException {
        initDaoFactory();
        User user = userDao.findByPK(id);
        user.setGender(genderDao.findByPK(user.getGender().getId()));
        Map<String, String> userIdParam = new HashMap<>();
        userIdParam.put("user_id", String.valueOf(user.getId()));
        user.setAddressList(addressDao.findAllByParams(userIdParam));
        jdbcDaoFactory.close();
        return user;
    }

}
