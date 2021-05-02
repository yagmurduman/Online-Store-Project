package com.onlineStore.model;

import java.util.Date;

public class Review {

    private Integer rId;
    private Integer cId;
    private Integer pId;
    private String cName;
    private Integer rate;
    private String comment;
    private Date rDate;
    private boolean isApproved;

    public Review() {}

    public Review(Integer rId, Integer cId, Integer pId, String cName, Integer rate, String comment, Date rDate, boolean isApproved) {
        this.rId = rId;
        this.cId = cId;
        this.pId = pId;
        this.cName = cName;
        this.rate = rate;
        this.comment = comment;
        this.rDate = rDate;
        this.isApproved = isApproved;
    }


    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
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

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public Date getrDate() {
        return rDate;
    }

    public void setrDate(Date rDate) {
        this.rDate = rDate;
    }

}
