package com.epam.alexandrli.paintballshop.entity;

import org.joda.time.DateTime;

import java.io.InputStream;

public class Image extends BaseEntity {
    private String name;
    private DateTime modified;
    private Product product;
    private String contentType;
    private InputStream content;

    public Image() {

    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public InputStream getContent() {
        return content;
    }

    public void setContent(InputStream content) {
        this.content = content;
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
