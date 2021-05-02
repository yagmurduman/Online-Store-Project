package com.onlineStore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product {

    private Integer pId;
    private String type;
    private String pName;
    private float price;
    private int stock;
    private String image;
    private float purchasedPrice;
    private float sellingPrice;
    private int discount;
    private String description;
    private String publisher;
    private Date publishedDate;
    private Float avgRate;

    public Product() {}

    public Product(Integer pId, String type, String pName, float price, int stock, String image, float purchasedPrice, float sellingPrice, int discount, String description, String publisher, Date publishedDate, Float avgRate) {
        this.pId = pId;
        this.type = type;
        this.pName = pName;
        this.price = price;
        this.stock = stock;
        this.image = image;
        this.purchasedPrice = purchasedPrice;
        this.sellingPrice = sellingPrice;
        this.discount = discount;
        this.description = description;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.avgRate = avgRate;
    }

    public Float getAvgRate() {
        return avgRate;
    }

    public void setAvgRate(Float avgRate) {
        this.avgRate = avgRate;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPurchasedPrice() {
        return purchasedPrice;
    }

    public void setPurchasedPrice(float purchasedPrice) {
        this.purchasedPrice = purchasedPrice;
    }

    public float getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(float selligPrice) {
        this.sellingPrice = selligPrice;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }
}
