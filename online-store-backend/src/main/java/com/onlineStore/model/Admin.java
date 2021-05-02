package com.onlineStore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Admin {

    private Integer aId;
    private String userName;
    private String password;

    public Admin() {}

    public Admin(Integer aId, String userName, String password) {
        this.aId = aId;
        this.userName = userName;
        this.password = password;
    }

    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        this.aId = aId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
