package com.epam.alexandrli.paintballshop.entity;

public class CharacteristicItem extends BaseEntity {
    private Characteristic characteristic;
    private String value;

    public CharacteristicItem() {
    }

    public Characteristic getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(Characteristic characteristic) {
        this.characteristic = characteristic;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
