package com.onlineStore.model;

public class Campaign {

    private Integer camId;
    private Integer pId;
    private Integer discount;
    private Integer code;
    private boolean isValid;
    private String location;

    public Campaign() {}

    public Campaign(Integer camId, Integer pId, Integer discount, Integer code, boolean isValid, String location) {
        this.camId = camId;
        this.pId = pId;
        this.discount = discount;
        this.code = code;
        this.isValid = isValid;
        this.location = location;
    }

    public Integer getCamId() {
        return camId;
    }

    public void setCamId(Integer camId) {
        this.camId = camId;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
