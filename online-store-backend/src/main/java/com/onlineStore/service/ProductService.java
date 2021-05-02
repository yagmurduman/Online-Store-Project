package com.onlineStore.service;

import com.onlineStore.model.Product;

import java.util.List;


public interface ProductService {
    //get all product data
    List<Product> getAllProducts(Integer key);

    //get all product data by id
    Product getProductById(Integer id);

    //get all product data by type
     List<Product> getProductByType(String type, Integer key);

     //get all products by category
    List<Product> getProductsByCategory (Integer catId, Integer key);

}
