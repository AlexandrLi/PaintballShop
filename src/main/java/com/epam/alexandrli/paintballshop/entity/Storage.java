package com.epam.alexandrli.paintballshop.entity;

import java.util.ArrayList;
import java.util.List;

public class Storage extends DescriptionedEntity {
    private String name;
    private List<StorageItem> storageItems = new ArrayList<>();

    public Storage() {
    }

    public Storage(Integer id) {
        setId(id);
    }

    public List<StorageItem> getStorageItems() {
        return storageItems;
    }

    public void setStorageItems(List<StorageItem> storageItems) {
        this.storageItems = storageItems;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean addProduct(StorageItem storageItem) {
        return storageItems.add(storageItem);
    }
}
