package com.epam.alexandrli.paintballshop.service;

import com.epam.alexandrli.paintballshop.dao.DaoFactory;
import com.epam.alexandrli.paintballshop.dao.GenericDao;
import com.epam.alexandrli.paintballshop.dao.jdbc.JdbcDaoFactory;
import com.epam.alexandrli.paintballshop.entity.*;
import org.joda.money.Money;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
    private DaoFactory jdbcDaoFactory;
    private GenericDao<User> userDao;
    private GenericDao<Gender> genderDao;
    private GenericDao<Address> addressDao;
    private GenericDao<Order> orderDao;
    private GenericDao<OrderItem> orderItemDao;
    private GenericDao<Product> productDao;
    private GenericDao<OrderStatus> orderStatusDao;

    public UserService() throws SQLException {
        jdbcDaoFactory = new JdbcDaoFactory();
        this.userDao = jdbcDaoFactory.getDao(User.class);
        this.genderDao = jdbcDaoFactory.getDao(Gender.class);
        this.addressDao = jdbcDaoFactory.getDao(Address.class);
        this.orderDao = jdbcDaoFactory.getDao(Order.class);
        this.orderItemDao = jdbcDaoFactory.getDao(OrderItem.class);
        this.orderStatusDao = jdbcDaoFactory.getDao(OrderStatus.class);
        this.productDao = jdbcDaoFactory.getDao(Product.class);
        jdbcDaoFactory.close();
    }

    public User registerUser(User user) throws SQLException {
        jdbcDaoFactory.getConnection();
        User registeredUser = userDao.insert(user);
        jdbcDaoFactory.close();
        return registeredUser;
    }

    public User performUserLogin(String email, String password) throws SQLException {
        jdbcDaoFactory.getConnection();
        Map<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
        List<User> users = userDao.findAllByParams(params);
        jdbcDaoFactory.close();
        if (!users.isEmpty() && !users.get(0).isDeleted()) {
            return users.get(0);
        }
        return null;
    }

    public User getUserById(Integer id) throws SQLException {
        jdbcDaoFactory.getConnection();
        User user = userDao.findByPK(id);
        user.setGender(genderDao.findByPK(user.getGender().getId()));
        user.setAddress(addressDao.findByPK(user.getAddress().getId()));
        jdbcDaoFactory.close();
        return user;
    }

    public User updateUserProfile(User user) throws SQLException {
        jdbcDaoFactory.getConnection();
        userDao.update(user);
        jdbcDaoFactory.close();
        return user;
    }

    public User transferCash(User user, String cashAmount) {
        Money totalCash = user.getCash().plus(Money.parse("KZT " + cashAmount));
        user.setCash(totalCash);
        userDao.update(user);
        return user;
    }

    public List<Order> getUserOrders(Integer id) {
        List<Order> orders = orderDao.findAllByParams(Collections.singletonMap("user_id", String.valueOf(id)));
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
        return orders;
    }
}
