package com.epam.alexandrli.paintballshop.entity;

public class StorageItem extends BaseEntity {
    private Storage storage;
    private Product product;
    private int amount;

    public StorageItem() {
    }

    @Override
    public String toString() {
        return "StorageItem{" + super.toString() +
                "storageId=" + storage.getId() +
                ", productId=" + product.getId() +
                ", amount=" + amount +
                '}';
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
