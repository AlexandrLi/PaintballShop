package com.epam.alexandrli.paintballshop.dao.jdbc;

import com.epam.alexandrli.paintballshop.dao.DaoException;
import com.epam.alexandrli.paintballshop.entity.Product;
import com.epam.alexandrli.paintballshop.entity.Storage;
import com.epam.alexandrli.paintballshop.entity.StorageItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcStorageItemDao extends AbstractJdbcDao<StorageItem> {

    private static final String INSERT_ORDER_ITEM = "INSERT INTO storage_item(amount, storage_id, product_id) VALUES (?,?,?)";
    private static final String UPDATE_ORDER_ITEM_BY_ID = "UPDATE storage_item SET amount=?, storage_id=?, product_id=? WHERE id=?";

    @Override
    protected String getQueryForInsert() {
        return INSERT_ORDER_ITEM;
    }

    @Override
    protected String getQueryToUpdateById() {
        return UPDATE_ORDER_ITEM_BY_ID;
    }

    @Override
    protected StorageItem getObjectFromResultSet(ResultSet rs) throws DaoException {
        StorageItem storageItem = new StorageItem();
        try {
            storageItem.setId(rs.getInt("id"));
            storageItem.setAmount(rs.getInt("amount"));
            Storage storage = new Storage(rs.getInt("storage_id"));
            storageItem.setStorage(storage);
            Product product = new Product(rs.getInt("product_id"));
            storageItem.setProduct(product);
            storageItem.setDeleted(rs.getBoolean("deleted"));
        } catch (SQLException e) {
            throw new DaoException("Could not get object from result set", e);
        }
        return storageItem;
    }

    @Override
    protected void setVariablesForPreparedStatementExceptId(StorageItem storageItem, PreparedStatement ps) throws DaoException {
        try {
            ps.setInt(1, storageItem.getAmount());
            ps.setInt(2, storageItem.getStorage().getId());
            ps.setInt(3, storageItem.getProduct().getId());
        } catch (SQLException e) {
            throw new DaoException("Could not set variables for prepared statement", e);
        }
    }

    @Override
    protected String getTableName() {
        return "storage_item";
    }

}
