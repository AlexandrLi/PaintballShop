package com.epam.alexandrli.paintballshop.service;

import com.epam.alexandrli.paintballshop.dao.DaoException;
import com.epam.alexandrli.paintballshop.dao.DaoFactory;
import com.epam.alexandrli.paintballshop.dao.GenericDao;
import com.epam.alexandrli.paintballshop.entity.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.epam.alexandrli.paintballshop.dao.DaoFactory.JDBC;
import static com.epam.alexandrli.paintballshop.dao.DaoFactory.getDaoFactory;

public class ProductService {

    public ProductService() {
    }

    public Product getProductById(String id) throws ServiceException {
        Product product;
        try (DaoFactory jdbcDaoFactory = getDaoFactory(JDBC)) {
            GenericDao<Product> productDao = jdbcDaoFactory.getDao(Product.class);
            product = productDao.findByPK(Integer.parseInt(id));
        } catch (DaoException e) {
            throw new ServiceException("Could not get product", e);
        }
        return product;
    }

    public List<Product> getFeaturedProducts() throws ServiceException {
        List<Product> featured;
        try (DaoFactory jdbcDaoFactory = getDaoFactory(JDBC)) {
            GenericDao<Product> productDao = jdbcDaoFactory.getDao(Product.class);
            List<Product> products = productDao.findAll();
            featured = products.subList(products.size() - 9, products.size());
        } catch (DaoException e) {
            throw new ServiceException("Could not get featured list", e);
        }
        return featured;
    }

    public Image getProductPreviewImage(String id) throws ServiceException {
        List<Image> images;
        try (DaoFactory jdbcDaoFactory = getDaoFactory(JDBC)) {
            GenericDao<Image> imageDao = jdbcDaoFactory.getDao(Image.class);
            images = imageDao.findAllByParams(Collections.singletonMap("product_id", id));
        } catch (DaoException e) {
            throw new ServiceException("Could not get product preview image", e);
        }
        return images.get(0);
    }

    public List<Product> getAllProductsByType(String typeId) throws ServiceException {
        List<Product> products;
        try (DaoFactory jdbcDaoFactory = getDaoFactory(JDBC)) {
            GenericDao<Product> productDao = jdbcDaoFactory.getDao(Product.class);
            products = productDao.findAllByParams(Collections.singletonMap("product_type_id", typeId));
            products = products.stream().filter(product -> !product.isDeleted()).collect(Collectors.toList());
        } catch (DaoException e) {
            throw new ServiceException("Could not get products by type", e);
        }
        return products;
    }

    public Product getFilledProduct(String id) throws ServiceException {
        Product product;
        try (DaoFactory jdbcDaoFactory = getDaoFactory(JDBC)) {
            try {
                jdbcDaoFactory.startTransaction();
                GenericDao<Product> productDao = jdbcDaoFactory.getDao(Product.class);
                GenericDao<ProductType> productTypeDao = jdbcDaoFactory.getDao(ProductType.class);
                GenericDao<Image> imageDao = jdbcDaoFactory.getDao(Image.class);
                GenericDao<CharacteristicItem> characteristicItemDao = jdbcDaoFactory.getDao(CharacteristicItem.class);
                GenericDao<Characteristic> characteristicDao = jdbcDaoFactory.getDao(Characteristic.class);
                product = productDao.findByPK(Integer.parseInt(id));
                ProductType productType = productTypeDao.findByPK(product.getType().getId());
                product.setType(productType);
                Map<String, String> productIdParam = Collections.singletonMap("product_id", id);
                List<Image> images = imageDao.findAllByParams(productIdParam);
                product.setImages(images);
                List<CharacteristicItem> characteristics = characteristicItemDao.findAllByParams(productIdParam);
                for (CharacteristicItem characteristicItem : characteristics) {
                    characteristicItem.setCharacteristic(characteristicDao.findByPK(characteristicItem.getCharacteristic().getId()));
                }
                characteristics = characteristics.stream().filter(item -> !item.isDeleted()).collect(Collectors.toList());
                product.setCharacteristics(characteristics);
                jdbcDaoFactory.commitTransaction();
            } catch (DaoException e) {
                jdbcDaoFactory.rollbackTransaction();
                throw new ServiceException("Could not get filled product", e);
            }
        } catch (DaoException e) {
            throw new ServiceException("Could not init jdbc factory", e);
        }
        return product;
    }

    public List<ProductType> getAllProductTypes() throws ServiceException {
        List<ProductType> productTypes;
        try (DaoFactory jdbcDaoFactory = getDaoFactory(JDBC)) {
            GenericDao<ProductType> productTypeDao = jdbcDaoFactory.getDao(ProductType.class);
            productTypes = productTypeDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Could not get product type list", e);
        }
        return productTypes;
    }

    public void updateProduct(Product product) throws ServiceException {
        try (DaoFactory jdbcDaoFactory = getDaoFactory(JDBC)) {
            GenericDao<Product> productDao = jdbcDaoFactory.getDao(Product.class);
            productDao.update(product);
        } catch (DaoException e) {
            throw new ServiceException("Could not update product", e);
        }
    }

    public void updateProductImage(Image image) throws ServiceException {
        try (DaoFactory jdbcDaoFactory = getDaoFactory(JDBC)) {
            GenericDao<Image> imageDao = jdbcDaoFactory.getDao(Image.class);
            imageDao.update(image);
        } catch (DaoException e) {
            throw new ServiceException("Could not update product image", e);
        }
    }

    public Product addProduct(Product product, Image productImage) throws ServiceException {
        try (DaoFactory jdbcDaoFactory = getDaoFactory(JDBC)) {
            try {
                jdbcDaoFactory.startTransaction();
                GenericDao<Product> productDao = jdbcDaoFactory.getDao(Product.class);
                GenericDao<Image> imageDao = jdbcDaoFactory.getDao(Image.class);
                Product insertedProduct = productDao.insert(product);
                productImage.setProduct(insertedProduct);
                imageDao.insert(productImage);
                jdbcDaoFactory.commitTransaction();
            } catch (DaoException e) {
                jdbcDaoFactory.rollbackTransaction();
                throw new ServiceException("Could not add product", e);
            }
        } catch (DaoException e) {
            throw new ServiceException("Could not init jdbc factory", e);
        }
        return product;
    }

    public void addProductOnStorage(Product product) throws ServiceException {
        try (DaoFactory jdbcDaoFactory = getDaoFactory(JDBC)) {
            GenericDao<StorageItem> storageItemDao = jdbcDaoFactory.getDao(StorageItem.class);
            StorageItem storageItem = new StorageItem();
            storageItem.setProduct(product);
            storageItem.setStorage(new Storage(1));
            storageItem.setAmount(0);
            storageItemDao.insert(storageItem);
        } catch (DaoException e) {
            throw new ServiceException("Could not add product", e);
        }
    }
}
