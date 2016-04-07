package com.epam.alexandrli.paintballshop.entity;

import org.joda.time.DateTime;

public class Image extends BaseEntity {
    private String name;
    // TODO: 02.04.2016 можно и getContentType к сущности прицепить, который будет смотреть на расширение и возвращать соответствующее значение типа image/jpeg из мапы.
    private DateTime modified;
    private String contentType;

    public Image() {
        modified = DateTime.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DateTime getModified() {
        return modified;
    }

    public void setModified(DateTime modified) {
        this.modified = modified;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
