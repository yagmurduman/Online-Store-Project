package com.onlineStore.controller.api;

import com.onlineStore.model.Pair;
import com.onlineStore.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface SearchControllerApi {

    @PostMapping(value="/search/")
    ResponseEntity<Pair> searchProducts(String search);

    @PostMapping(value="/search/search-limit")
    ResponseEntity<List<Product>> searchProductsLimit(String search, Integer key);
}
