package com.epam.alexandrli.paintballshop.service;

import com.epam.alexandrli.paintballshop.dao.DaoException;
import com.epam.alexandrli.paintballshop.dao.DaoFactory;
import com.epam.alexandrli.paintballshop.dao.GenericDao;
import com.epam.alexandrli.paintballshop.entity.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.epam.alexandrli.paintballshop.dao.DaoFactory.JDBC;
import static com.epam.alexandrli.paintballshop.dao.DaoFactory.getDaoFactory;

public class ShopService {

    public ShopService() {
    }

    public Order getOrder(Integer id) throws ServiceException {
        Order order;
        try (DaoFactory jdbcDaoFactory = getDaoFactory(JDBC)) {
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
        try (DaoFactory jdbcDaoFactory = getDaoFactory(JDBC)) {
            GenericDao<Gender> genderDao = jdbcDaoFactory.getDao(Gender.class);
            genders = genderDao.findAll();
            genders = genders.stream().filter(gender -> !gender.isDeleted()).collect(Collectors.toList());
        } catch (DaoException e) {
            throw new ServiceException("Could not get gender list", e);
        }
        return genders;
    }

    public List<OrderStatus> getAllOrderStatuses() throws ServiceException {
        List<OrderStatus> statuses;
        try (DaoFactory jdbcDaoFactory = getDaoFactory(JDBC)) {
            GenericDao<OrderStatus> orderStatusDao = jdbcDaoFactory.getDao(OrderStatus.class);
            statuses = orderStatusDao.findAll();
            statuses = statuses.stream().filter(status -> !status.isDeleted()).collect(Collectors.toList());
        } catch (DaoException e) {
            throw new ServiceException("Could not get status list", e);
        }
        return statuses;
    }

    public List<ProductType> getAllProductTypes() throws ServiceException {
        List<ProductType> types;
        try (DaoFactory jdbcDaoFactory = getDaoFactory(JDBC)) {
            GenericDao<ProductType> productTypeDao = jdbcDaoFactory.getDao(ProductType.class);
            types = productTypeDao.findAll();
            types = types.stream().filter(productType -> !productType.isDeleted()).collect(Collectors.toList());
        } catch (DaoException e) {
            throw new ServiceException("Could not get product type list", e);
        }
        return types;
    }

    public User buyCart(Order cart) throws ServiceException {
        User cartUser = cart.getUser();
        if (cartUser.getCash().isLessThan(cart.getPrice())) {
            throw new ServiceException("Not enough money");
        }
        try (DaoFactory jdbcDaoFactory = getDaoFactory(JDBC)) {
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

    public List<User> getAllUsersOnPage(int pageNumber, int pageSize) throws ServiceException {
        List<User> users;
        try (DaoFactory jdbcDaoFactory = getDaoFactory(JDBC)) {
            GenericDao<User> userDao = jdbcDaoFactory.getDao(User.class);
            GenericDao<Gender> genderDao = jdbcDaoFactory.getDao(Gender.class);
            users = userDao.findAll(pageNumber, pageSize);
            for (User user : users) {
                user.setGender(genderDao.findByPK(user.getGender().getId()));
            }
        } catch (DaoException e) {
            throw new ServiceException("Could not get users", e);
        }
        return users;
    }

    public List<Product> getAllProductsOnPage(int pageNumber, int pageSize) throws ServiceException {
        List<Product> products;
        try (DaoFactory jdbcDaoFactory = getDaoFactory(JDBC)) {
            GenericDao<Product> productDao = jdbcDaoFactory.getDao(Product.class);
            GenericDao<ProductType> productTypeDao = jdbcDaoFactory.getDao(ProductType.class);
            products = productDao.findAll(pageNumber, pageSize);
            for (Product product : products) {
                product.setType(productTypeDao.findByPK(product.getType().getId()));
            }
        } catch (DaoException e) {
            throw new ServiceException("Could not get products", e);
        }
        return products;
    }

    public List<StorageItem> getAllStorageItemsOnPage(int pageNumber, int pageSize) throws ServiceException {
        List<StorageItem> storageItems;
        try (DaoFactory jdbcDaoFactory = getDaoFactory(JDBC)) {
            GenericDao<StorageItem> storageItemDao = jdbcDaoFactory.getDao(StorageItem.class);
            GenericDao<Product> productDao = jdbcDaoFactory.getDao(Product.class);
            GenericDao<Storage> storageDao = jdbcDaoFactory.getDao(Storage.class);
            storageItems = storageItemDao.findAll(pageNumber, pageSize);
            for (StorageItem storageItem : storageItems) {
                storageItem.setProduct(productDao.findByPK(storageItem.getProduct().getId()));
                storageItem.setStorage(storageDao.findByPK(storageItem.getStorage().getId()));
            }
        } catch (DaoException e) {
            throw new ServiceException("Could not get storage items", e);
        }
        return storageItems;
    }

    public List<Order> getAllOrdersOnPage(int pageNumber, int pageSize) throws ServiceException {
        List<Order> orders;
        try (DaoFactory jdbcDaoFactory = getDaoFactory(JDBC)) {
            GenericDao<Order> orderDao = jdbcDaoFactory.getDao(Order.class);
            GenericDao<User> userDao = jdbcDaoFactory.getDao(User.class);
            GenericDao<OrderStatus> orderStatusDao = jdbcDaoFactory.getDao(OrderStatus.class);
            GenericDao<OrderItem> orderItemDao = jdbcDaoFactory.getDao(OrderItem.class);
            GenericDao<Product> productDao = jdbcDaoFactory.getDao(Product.class);
            orders = orderDao.findAll(pageNumber, pageSize);
            for (Order order : orders) {
                order.setUser(userDao.findByPK(order.getUser().getId()));
                order.setStatus(orderStatusDao.findByPK(order.getStatus().getId()));
                List<OrderItem> orderItems = orderItemDao.findAllByParams(Collections.singletonMap("order_id", String.valueOf(order.getId())));
                orderItems = orderItems.stream().filter(orderItem -> !orderItem.isDeleted()).collect(Collectors.toList());
                for (OrderItem orderItem : orderItems) {
                    orderItem.setProduct(productDao.findByPK(orderItem.getProduct().getId()));
                }
                order.setOrderItems(orderItems);
            }
        } catch (DaoException e) {
            throw new ServiceException("Could not get orders", e);
        }
        return orders;
    }

    public int getUsersCount() throws ServiceException {
        try (DaoFactory jdbcDaoFactory = getDaoFactory(JDBC)) {
            GenericDao<User> userDao = jdbcDaoFactory.getDao(User.class);
            return userDao.getNotDeletedCount();
        } catch (DaoException e) {
            throw new ServiceException("Could not get users count", e);
        }
    }

    public int getProductsCount() throws ServiceException {
        try (DaoFactory jdbcDaoFactory = getDaoFactory(JDBC)) {
            GenericDao<Product> productDao = jdbcDaoFactory.getDao(Product.class);
            return productDao.getNotDeletedCount();
        } catch (DaoException e) {
            throw new ServiceException("Could not get products count", e);
        }
    }

    public int getOrdersCount() throws ServiceException {
        try (DaoFactory jdbcDaoFactory = getDaoFactory(JDBC)) {
            GenericDao<Order> orderDao = jdbcDaoFactory.getDao(Order.class);
            return orderDao.getNotDeletedCount();
        } catch (DaoException e) {
            throw new ServiceException("Could not get orders count", e);
        }
    }

    public int getStorageItemsCount() throws ServiceException {
        try (DaoFactory jdbcDaoFactory = getDaoFactory(JDBC)) {
            GenericDao<StorageItem> storageItemDao = jdbcDaoFactory.getDao(StorageItem.class);
            return storageItemDao.getNotDeletedCount();
        } catch (DaoException e) {
            throw new ServiceException("Could not get storage items count", e);
        }
    }

    public void deleteUserById(String id) throws ServiceException {
        try (DaoFactory jdbcDaoFactory = getDaoFactory(JDBC)) {
            GenericDao<User> userDao = jdbcDaoFactory.getDao(User.class);
            userDao.delete(Integer.valueOf(id));
        } catch (DaoException e) {
            throw new ServiceException("Could not delete user", e);
        }
    }

    public void deleteProductById(String id) throws ServiceException {
        try (DaoFactory jdbcDaoFactory = getDaoFactory(JDBC)) {
            GenericDao<Product> productDao = jdbcDaoFactory.getDao(Product.class);
            productDao.delete(Integer.valueOf(id));
        } catch (DaoException e) {
            throw new ServiceException("Could not delete user", e);
        }
    }

    public void deleteOrderById(String id) throws ServiceException {
        try (DaoFactory jdbcDaoFactory = getDaoFactory(JDBC)) {
            GenericDao<Order> orderDao = jdbcDaoFactory.getDao(Order.class);
            orderDao.delete(Integer.valueOf(id));
        } catch (DaoException e) {
            throw new ServiceException("Could not delete order", e);
        }
    }

    public void updateOrderStatus(String orderId, String statusId) throws ServiceException {
        try (DaoFactory jdbcDaoFactory = getDaoFactory(JDBC)) {
            GenericDao<Order> orderDao = jdbcDaoFactory.getDao(Order.class);
            Order order = orderDao.findByPK(Integer.valueOf(orderId));
            order.getStatus().setId(Integer.valueOf(statusId));
            orderDao.update(order);
        } catch (DaoException e) {
            throw new ServiceException("Could not update order status", e);
        }
    }

    public void deleteStorageItemById(String id) throws ServiceException {
        try (DaoFactory jdbcDaoFactory = getDaoFactory(JDBC)) {
            GenericDao<StorageItem> storageItemDao = jdbcDaoFactory.getDao(StorageItem.class);
            storageItemDao.delete(Integer.valueOf(id));
        } catch (DaoException e) {
            throw new ServiceException("Could not delete storage item", e);
        }
    }

    public void updateStorageItemAmount(String itemId, String amount) throws ServiceException {
        try (DaoFactory jdbcDaoFactory = getDaoFactory(JDBC)) {
            GenericDao<StorageItem> storageItemDao = jdbcDaoFactory.getDao(StorageItem.class);
            StorageItem item = storageItemDao.findByPK(Integer.valueOf(itemId));
            item.setAmount(Integer.parseInt(amount));
            storageItemDao.update(item);
        } catch (DaoException e) {
            throw new ServiceException("Could not update storage item amount", e);
        }
    }
}
