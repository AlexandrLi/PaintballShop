package com.epam.alexandrli.paintballshop.service;

import com.epam.alexandrli.paintballshop.dao.DaoFactory;
import com.epam.alexandrli.paintballshop.dao.GenericDao;
import com.epam.alexandrli.paintballshop.dao.jdbc.JdbcDaoFactory;
import com.epam.alexandrli.paintballshop.entity.*;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ShopService {
    private DaoFactory jdbcDaoFactory;
    private GenericDao<User> userDao;
    private GenericDao<Gender> genderDao;
    private GenericDao<Address> addressDao;
    private GenericDao<Order> orderDao;
    private GenericDao<OrderItem> orderItemDao;
    private GenericDao<Product> productDao;
    private GenericDao<StorageItem> storageItemDao;
    private GenericDao<OrderStatus> orderStatusDao;

    public ShopService() throws SQLException {
        jdbcDaoFactory = new JdbcDaoFactory();
        this.userDao = jdbcDaoFactory.getDao(User.class);
        this.genderDao = jdbcDaoFactory.getDao(Gender.class);
        this.addressDao = jdbcDaoFactory.getDao(Address.class);
        this.orderDao = jdbcDaoFactory.getDao(Order.class);
        this.orderItemDao = jdbcDaoFactory.getDao(OrderItem.class);
        this.orderStatusDao = jdbcDaoFactory.getDao(OrderStatus.class);
        this.productDao = jdbcDaoFactory.getDao(Product.class);
        this.storageItemDao = jdbcDaoFactory.getDao(StorageItem.class);
        jdbcDaoFactory.close();
    }

    public Order getOrder(Integer id) {
        Order order = orderDao.findByPK(id);
        order.setUser(userDao.findByPK(order.getUser().getId()));
        order.setStatus(orderStatusDao.findByPK(order.getStatus().getId()));
        List<OrderItem> orderItems = orderItemDao.findAllByParams(Collections.singletonMap("order_id", String.valueOf(order.getId())));
        for (OrderItem orderItem : orderItems) {
            orderItem.setProduct(productDao.findByPK(orderItem.getProduct().getId()));
        }
        order.setOrderItems(orderItems);
        return order;
    }

    public User placeOrder(Order cart) throws ShopServiceException {
        if (cart.getUser().getCash().isLessThan(cart.getPrice())) {
            throw new ShopServiceException("Not enough money");
        }
        User cartUser = cart.getUser();
        cartUser.spendCash(cart.getPrice());
        userDao.update(cartUser);
        cart.setStatus(orderStatusDao.findByPK(1));
        Order newOrder = orderDao.insert(cart);
        for (OrderItem orderItem : cart.getOrderItems()) {
            orderItem.setOrder(newOrder);
            orderItemDao.insert(orderItem);
        }
        return cartUser;
    }
}
