package com.onlineStore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Date;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Delivery {

    private Integer dId;
    private Integer cId;
    private Integer pId;
    private String pName;
    private Date dDate;
    private int quantity;
    private float price; //quantity *pPrice
    private boolean isDelivered = false;

    public Delivery() {}

    public Delivery(Integer dId, Integer cId, Integer pId, String pName, Date dDate, int quantity, float price, boolean isDelivered) {
        this.dId = dId;
        this.cId = cId;
        this.pId = pId;
        this.pName = pName;
        this.dDate = dDate;
        this.quantity = quantity;
        this.price = price;
        this.isDelivered = isDelivered;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public Date getdDate() {
        return dDate;
    }

    public void setdDate(Date dDate) {
        this.dDate = dDate;
    }

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
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

    public Date getDate() {
        return dDate;
    }

    public void setDate(Date dDate) {
        this.dDate = dDate;
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

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }
}
