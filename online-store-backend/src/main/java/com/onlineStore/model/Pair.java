package com.onlineStore.model;

import com.onlineStore.model.Product;

import java.util.List;

public class Pair {

    private String key;
    private List<Product> value;

    public Pair(String key, List<Product> value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<Product> getValue() {
        return value;
    }

    public void setValue(List<Product> value) {
        this.value = value;
    }
}

