package com.epam.alexandrli.paintballshop.entity;

import org.joda.money.Money;

import java.util.ArrayList;
import java.util.List;

public class Product extends DescriptionedEntity {

    private String name;
    private Money price;
    private ProductType type;
    private List<CharacteristicItem> characteristics = new ArrayList<>();
    private List<Image> images = new ArrayList<>();

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public List<CharacteristicItem> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<CharacteristicItem> characteristics) {
        this.characteristics = characteristics;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

}
