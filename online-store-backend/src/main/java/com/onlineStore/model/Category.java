package com.onlineStore.model;

public class Category {

    private Integer catId;
    private String catName;
    private String type;

    public Category() {}

    public Category(Integer catId, String catName, String type) {
        this.catId = catId;
        this.catName = catName;
        this.type = type;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
