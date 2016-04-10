package com.epam.alexandrli.paintballshop.entity;

public class StorageItem extends BaseEntity {
    private int amount;
    private Product product;
    private Storage storage;

    public StorageItem() {
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}
