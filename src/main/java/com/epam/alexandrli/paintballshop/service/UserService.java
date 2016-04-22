package com.epam.alexandrli.paintballshop.service;

import com.epam.alexandrli.paintballshop.dao.DaoException;
import com.epam.alexandrli.paintballshop.dao.DaoFactory;
import com.epam.alexandrli.paintballshop.dao.GenericDao;
import com.epam.alexandrli.paintballshop.dao.jdbc.JdbcDaoFactory;
import com.epam.alexandrli.paintballshop.entity.*;
import org.joda.money.Money;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.epam.alexandrli.paintballshop.dao.DaoFactory.JDBC;
import static com.epam.alexandrli.paintballshop.dao.DaoFactory.getDaoFactory;

public class UserService {

    public UserService() {
    }

    public User registerUser(User user, Address address) throws ServiceException {
        User registeredUser;
        try (DaoFactory jdbcDaoFactory = new JdbcDaoFactory()) {
            try {
                jdbcDaoFactory.beginTransaction();
                GenericDao<Address> addressDao = jdbcDaoFactory.getDao(Address.class);
                GenericDao<User> userDao = jdbcDaoFactory.getDao(User.class);
                Address registeredAddress = addressDao.insert(address);
                user.setAddress(registeredAddress);
                registeredUser = userDao.insert(user);
            } catch (DaoException e) {
                jdbcDaoFactory.rollback();
                throw new ServiceException("Could not register user", e);
            }
            jdbcDaoFactory.commit();
        } catch (DaoException e) {
            throw new ServiceException("Could not init factory", e);
        }
        return registeredUser;
    }

    public Address getUserAddress(User user) throws ServiceException {
        try (DaoFactory jdbcDaoFactory = new JdbcDaoFactory()) {
            GenericDao<Address> addressDao = jdbcDaoFactory.getDao(Address.class);
            return addressDao.findByPK(user.getAddress().getId());
        } catch (DaoException e) {
            throw new ServiceException("Could not read user address from db", e);
        }
    }

    public Gender getUserGender(User user) throws ServiceException {
        try (DaoFactory jdbcDaoFactory = new JdbcDaoFactory()) {
            GenericDao<Gender> genderDao = jdbcDaoFactory.getDao(Gender.class);
            return genderDao.findByPK(user.getGender().getId());
        } catch (DaoException e) {
            throw new ServiceException("Could not read user gender from db", e);
        }
    }

    public User performUserLogin(String email, String password) throws ServiceException {
        try (DaoFactory jdbcDaoFactory = new JdbcDaoFactory()) {
            Map<String, String> params = new HashMap<>();
            params.put("email", email);
            params.put("password", password);
            GenericDao<User> userDao = jdbcDaoFactory.getDao(User.class);
            List<User> users = userDao.findAllByParams(params);
            if (!users.isEmpty() && !users.get(0).isDeleted()) {
                return users.get(0);
            }
        } catch (DaoException e) {
            throw new ServiceException("Could not read user from db", e);
        }
        return null;
    }

    public User getUserById(Integer id) throws ServiceException {
        User user;
        try (DaoFactory jdbcDaoFactory = getDaoFactory(JDBC)) {
            GenericDao<User> userDao = jdbcDaoFactory.getDao(User.class);
            GenericDao<Gender> genderDao = jdbcDaoFactory.getDao(Gender.class);
            GenericDao<Address> addressDao = jdbcDaoFactory.getDao(Address.class);
            user = userDao.findByPK(id);
            user.setGender(genderDao.findByPK(user.getGender().getId()));
            user.setAddress(addressDao.findByPK(user.getAddress().getId()));
        } catch (DaoException e) {
            throw new ServiceException("Could not read user from db", e);
        }
        return user;
    }

    public User updateUserProfile(User user) throws ServiceException {
        try (DaoFactory jdbcDaoFactory = getDaoFactory(JDBC)) {
            GenericDao<User> userDao = jdbcDaoFactory.getDao(User.class);
            userDao.update(user);
        } catch (DaoException e) {
            throw new ServiceException("Could not update user", e);
        }
        return user;
    }

    public User refillBalance(User user, String cashAmount) throws ServiceException {
        try (DaoFactory jdbcDaoFactory = getDaoFactory(JDBC)) {
            GenericDao<User> userDao = jdbcDaoFactory.getDao(User.class);
            Money totalCash = user.getCash().plus(Money.parse("KZT " + cashAmount));
            user.setCash(totalCash);
            userDao.update(user);
        } catch (DaoException e) {
            throw new ServiceException("Could not transfer cash", e);
        }
        return user;
    }

    public List<Order> getUserOrders(Integer id) throws ServiceException {
        List<Order> orders;
        try (DaoFactory jdbcDaoFactory = getDaoFactory(JDBC)) {
            GenericDao<Order> orderDao = jdbcDaoFactory.getDao(Order.class);
            GenericDao<OrderStatus> orderStatusDao = jdbcDaoFactory.getDao(OrderStatus.class);
            GenericDao<OrderItem> orderItemDao = jdbcDaoFactory.getDao(OrderItem.class);
            GenericDao<Product> productDao = jdbcDaoFactory.getDao(Product.class);
            orders = orderDao.findAllByParams(Collections.singletonMap("user_id", String.valueOf(id)));
            for (Order order : orders) {
                order.setStatus(orderStatusDao.findByPK(order.getStatus().getId()));
                List<OrderItem> orderItems = orderItemDao.findAllByParams(Collections.singletonMap("order_id", String.valueOf(order.getId())));
                for (OrderItem orderItem : orderItems) {
                    orderItem.setProduct(productDao.findByPK(orderItem.getProduct().getId()));
                }
                order.setOrderItems(orderItems);
            }
        } catch (DaoException e) {
            throw new ServiceException("Could not get user orders", e);
        }
        return orders;
    }

    public Address updateUserAddress(Address address) throws ServiceException {
        try (DaoFactory jdbcDaoFactory = getDaoFactory(JDBC)) {
            GenericDao<Address> addressDao = jdbcDaoFactory.getDao(Address.class);
            addressDao.update(address);
        } catch (DaoException e) {
            throw new ServiceException("Could not update user", e);
        }
        return address;

    }
}
