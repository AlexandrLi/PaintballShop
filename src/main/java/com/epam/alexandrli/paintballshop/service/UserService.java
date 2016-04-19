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

public class UserService {

    public UserService() {

    }

    public User registerUser(User user) throws ServiceException {
        User registeredUser;
        try (DaoFactory jdbcDaoFactory = new JdbcDaoFactory()) {
            GenericDao<User> userDao = jdbcDaoFactory.getDao(User.class);
            registeredUser = userDao.insert(user);

        } catch (DaoException e) {
            throw new ServiceException("Could not register user", e);
        }
        return registeredUser;
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
        try (DaoFactory jdbcDaoFactory = new JdbcDaoFactory()) {
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
        try (DaoFactory jdbcDaoFactory = new JdbcDaoFactory()) {
            GenericDao<User> userDao = jdbcDaoFactory.getDao(User.class);
            userDao.update(user);
        } catch (DaoException e) {
            throw new ServiceException("Could not update user", e);
        }
        return user;
    }

    public User transferCash(User user, String cashAmount) throws ServiceException {
        try (DaoFactory jdbcDaoFactory = new JdbcDaoFactory()) {
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
        try (DaoFactory jdbcDaoFactory = new JdbcDaoFactory()) {
            GenericDao<Order> orderDao = jdbcDaoFactory.getDao(Order.class);
            GenericDao<OrderStatus> orderStatusDao = jdbcDaoFactory.getDao(OrderStatus.class);
            GenericDao<OrderItem> orderItemDao = jdbcDaoFactory.getDao(OrderItem.class);
            GenericDao<Product> productDao = jdbcDaoFactory.getDao(Product.class);
            orders = orderDao.findAllByParams(Collections.singletonMap("user_id", String.valueOf(id)));
            for (Order order : orders) {
                OrderStatus orderStatus = orderStatusDao.findByPK(orderDao.findColumnByPK("order_status_id", order.getId()));
                List<OrderItem> orderItems = orderItemDao.findAllByParams(Collections.singletonMap("order_id", String.valueOf(order.getId())));
                for (OrderItem orderItem : orderItems) {
                    Product product = productDao.findByPK(orderItemDao.findColumnByPK("product_id", orderItem.getId()));
                    orderItem.setProduct(product);
                }
                order.setStatus(orderStatus);
                order.setOrderItems(orderItems);
            }
        } catch (DaoException e) {
            throw new ServiceException("Could not get user orders", e);
        }
        return orders;
    }
}
