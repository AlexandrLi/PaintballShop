package com.epam.alexandrli.paintballshop.entity;

import java.util.HashMap;
import java.util.Map;

public class Storage extends DescriptionedEnRuEntity {
    private String name;
    private Map<Product, Integer> storageItems = new HashMap<>();

    public Storage() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
