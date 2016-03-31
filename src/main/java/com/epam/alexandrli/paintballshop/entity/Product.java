package com.epam.alexandrli.paintballshop.entity;

import org.joda.money.Money;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Product extends NamedEntity {

    private Money price;
    private ProductType type;
    private Map<Characteristic, String> characteristics = new HashMap<>();
    private List<Image> images = new ArrayList<>();
    private String description;

    public Product() {
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

    public Map<Characteristic, String> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(Map<Characteristic, String> characteristics) {
        this.characteristics = characteristics;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
