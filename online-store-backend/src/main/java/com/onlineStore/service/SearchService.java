package com.onlineStore.service;

import com.onlineStore.model.Product;
import com.onlineStore.model.Pair;

import java.util.List;

public interface SearchService {

    // get list of product that is searched
     Pair searchProducts(String search);

     List<Product> searchProductsLimit(String search, Integer key);

}
