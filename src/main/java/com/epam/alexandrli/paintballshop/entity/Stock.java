package com.epam.alexandrli.paintballshop.entity;

import java.util.HashMap;
import java.util.Map;

public class Stock extends NamedEntity {
    private Map<Product, Integer> stockItems = new HashMap<>();

    public Stock() {
    }

    public Map<Product, Integer> getStockItems() {
        return stockItems;
    }

    public void setStockItems(Map<Product, Integer> stockItems) {
        this.stockItems = stockItems;
    }
}
