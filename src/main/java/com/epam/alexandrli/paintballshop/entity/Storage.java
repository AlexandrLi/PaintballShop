package com.epam.alexandrli.paintballshop.entity;

import java.util.HashMap;
import java.util.Map;

public class Storage extends NamedEntity {
    private Map<Product, Integer> storageItems = new HashMap<>();
    private String description;

    public Storage() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<Product, Integer> getStorageItems() {
        return storageItems;
    }

    public void setStorageItems(Map<Product, Integer> storageItems) {
        this.storageItems = storageItems;
    }

    public Integer addProduct(Product product, Integer quantity) {
        return storageItems.put(product, quantity);
    }
}
