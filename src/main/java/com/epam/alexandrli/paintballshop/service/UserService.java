package com.epam.alexandrli.paintballshop.service;

import com.epam.alexandrli.paintballshop.dao.DaoFactory;
import com.epam.alexandrli.paintballshop.dao.GenericDao;
import com.epam.alexandrli.paintballshop.dao.jdbc.JdbcDaoFactory;
import com.epam.alexandrli.paintballshop.entity.Address;
import com.epam.alexandrli.paintballshop.entity.BaseEntity;
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
        jdbcDaoFactory = new JdbcDaoFactory();
    }

    private void initUserDao() {
        if (userDao == null) {
            userDao = jdbcDaoFactory.getDao(User.class);
        }
    }

    private void initGenderDao() {
        if (genderDao == null) {
            genderDao = jdbcDaoFactory.getDao(Gender.class);
        }
    }

    private void initAddressDao() {
        if (addressDao == null) {
            addressDao = jdbcDaoFactory.getDao(Address.class);
        }
    }

    public void registerUser(User user) {
        initUserDao();
        userDao.insert(user);
    }

    public User performUserLogin(String email, String password) {
        Map<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
        List<User> users = userDao.findAllByParams(params);
        if (!users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }

    public User getUserById(Integer id) {
        initUserDao();
        User user = userDao.findByPK(id);
        initGenderDao();
        user.setGender(genderDao.findByPK(user.getGender().getId()));
        initAddressDao();
        Map<String, String> userIdParam = new HashMap<>();
        userIdParam.put("user_id", String.valueOf(user.getId()));
        user.setAddressList(addressDao.findAllByParams(userIdParam));
        return user;
    }

    private <T extends BaseEntity> boolean hasNullId(T entity) {
        return entity.getId() == null;
    }
}
