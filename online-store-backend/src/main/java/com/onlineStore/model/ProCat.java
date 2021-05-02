package com.onlineStore.model;

public class ProCat {

    private Integer pId;
    private Integer catId;

    public ProCat() {}

    public ProCat(Integer pId, Integer catId) {
        this.pId = pId;
        this.catId = catId;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }
}
