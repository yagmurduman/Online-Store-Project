package com.onlineStore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Customer {

    private Integer cId;
    private String cName;
    private String name;
    private String surname;
    private String password;
    private String address;
    private String email;


    public Customer() {}

    public Customer(Integer cId, String cName,String name, String surname, String password, String address, String email) {
        this.cId = cId;
        this.cName = cName;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.address = address;
        this.email = email;
    }


    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
