package com.epam.alexandrli.paintballshop.entity;

public class CharacteristicItem extends BaseEntity {
    private Product product;
    private Characteristic characteristic;
    private String value;

    public CharacteristicItem() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Characteristic getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(Characteristic characteristic) {
        this.characteristic = characteristic;
    }

    @Override
    public String toString() {
        return "CharacteristicItem{" + super.toString() +
                "productId=" + product.getId() +
                ", characteristicId=" + characteristic.getId() +
                ", value='" + value + '\'' +
                '}';
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
