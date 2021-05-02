package com.onlineStore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ShoppingCart {

    private Integer cId;
    private Integer pId;
    private String pName;
    private int quantity;
    private float price;
    private String image;
    private float discounted;

    public ShoppingCart() {}

    public ShoppingCart(Integer cId, Integer pId, int quantity) {
        this.cId = cId;
        this.pId = pId;
        this.quantity = quantity;
    }

    public ShoppingCart(Integer cId, Integer pId, String pName, int quantity, float price, String image, float discounted) {
        this.cId = cId;
        this.pId = pId;
        this.pName = pName;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
        this.discounted = discounted;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getDiscounted() {
        return discounted;
    }

    public void setDiscounted(float discounted) {
        this.discounted = discounted;
    }
}
