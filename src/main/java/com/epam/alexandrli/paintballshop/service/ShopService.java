package com.epam.alexandrli.paintballshop.service;

import com.epam.alexandrli.paintballshop.dao.DaoException;
import com.epam.alexandrli.paintballshop.dao.DaoFactory;
import com.epam.alexandrli.paintballshop.dao.GenericDao;
import com.epam.alexandrli.paintballshop.dao.jdbc.JdbcDaoFactory;
import com.epam.alexandrli.paintballshop.entity.*;

import java.util.Collections;
import java.util.List;

public class ShopService {

    public ShopService() {
    }

    public Order getOrder(Integer id) throws ServiceException {
        Order order;
        try (DaoFactory jdbcDaoFactory = new JdbcDaoFactory()) {
            GenericDao<Order> orderDao = jdbcDaoFactory.getDao(Order.class);
            GenericDao<User> userDao = jdbcDaoFactory.getDao(User.class);
            GenericDao<OrderStatus> orderStatusDao = jdbcDaoFactory.getDao(OrderStatus.class);
            GenericDao<OrderItem> orderItemDao = jdbcDaoFactory.getDao(OrderItem.class);
            GenericDao<Product> productDao = jdbcDaoFactory.getDao(Product.class);
            order = orderDao.findByPK(id);
            order.setUser(userDao.findByPK(order.getUser().getId()));
            order.setStatus(orderStatusDao.findByPK(order.getStatus().getId()));
            List<OrderItem> orderItems = orderItemDao.findAllByParams(Collections.singletonMap("order_id", String.valueOf(order.getId())));
            for (OrderItem orderItem : orderItems) {
                orderItem.setProduct(productDao.findByPK(orderItem.getProduct().getId()));
            }
            order.setOrderItems(orderItems);
        } catch (DaoException e) {
            throw new ServiceException("Could not get order", e);
        }
        return order;
    }

    public List<Gender> getAllGenders() throws ServiceException {
        List<Gender> genders;
        try (DaoFactory jdbcDaoFactory = new JdbcDaoFactory()) {
            GenericDao<Gender> genderDao = jdbcDaoFactory.getDao(Gender.class);
            genders = genderDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Could not get gender list", e);
        }
        return genders;
    }

    public User placeOrder(Order cart) throws ServiceException {
        User cartUser = cart.getUser();
        if (cartUser.getCash().isLessThan(cart.getPrice())) {
            throw new ServiceException("Not enough money");
        }
        try (DaoFactory jdbcDaoFactory = new JdbcDaoFactory()) {
            GenericDao<User> userDao = jdbcDaoFactory.getDao(User.class);
            GenericDao<OrderStatus> orderStatusDao = jdbcDaoFactory.getDao(OrderStatus.class);
            GenericDao<Order> orderDao = jdbcDaoFactory.getDao(Order.class);
            GenericDao<OrderItem> orderItemDao = jdbcDaoFactory.getDao(OrderItem.class);
            cartUser.spendCash(cart.getPrice());
            userDao.update(cartUser);
            cart.setStatus(orderStatusDao.findByPK(1));
            Order newOrder = orderDao.insert(cart);
            for (OrderItem orderItem : cart.getOrderItems()) {
                orderItem.setOrder(newOrder);
                orderItemDao.insert(orderItem);
            }
        } catch (DaoException e) {
            throw new ServiceException("Could not place order", e);
        }
        return cartUser;
    }
}
