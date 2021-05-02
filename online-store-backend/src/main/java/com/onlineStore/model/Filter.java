package com.onlineStore.model;

import java.util.PrimitiveIterator;

public class Filter {

    private Integer orderType;
    private String type;
    private Integer category;
    private Float price;
    private Integer rate;

    public Filter(Integer orderType, String type, Integer category, Float price, Integer rate) {
        this.orderType = orderType;
        this.type = type;
        this.category = category;
        this.price = price;
        this.rate = rate;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
}
