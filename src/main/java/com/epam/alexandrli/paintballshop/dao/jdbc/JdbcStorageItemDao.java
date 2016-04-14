package com.epam.alexandrli.paintballshop.dao.jdbc;

import com.epam.alexandrli.paintballshop.entity.Product;
import com.epam.alexandrli.paintballshop.entity.StorageItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcStorageItemDao extends AbstractJdbcDao<StorageItem> {

    public static final String INSERT_ORDER_ITEM = "INSERT INTO shopdb.storage_item(amount, storage_id, product_id) VALUES (?,?,?)";
    public static final String UPDATE_ORDER_ITEM_BY_ID = "UPDATE shopdb.storage_item SET amount=?, storage_id=?, product_id=? WHERE id=?";

    @Override
    protected String getQueryForInsert() {
        return INSERT_ORDER_ITEM;
    }

    @Override
    protected String getQueryToUpdateById() {
        return UPDATE_ORDER_ITEM_BY_ID;
    }

    @Override
    protected StorageItem getObjectFromResultSet(ResultSet rs) throws SQLException {
        StorageItem storageItem = new StorageItem();
        storageItem.setId(rs.getInt("id"));
        storageItem.setAmount(rs.getInt("amount"));
        Product product = new Product(rs.getInt("product_id"));
        storageItem.setProduct(product);
        storageItem.setDeleted(rs.getBoolean("deleted"));
        return storageItem;
    }

    @Override
    protected void setVariablesForPreparedStatementExceptId(StorageItem storageItem, PreparedStatement ps) throws SQLException {
        ps.setInt(1, storageItem.getAmount());
        ps.setInt(2, storageItem.getStorage().getId());
        ps.setInt(3, storageItem.getProduct().getId());
    }

    @Override
    protected String getTableName() {
        return "storage_item";
    }

}
