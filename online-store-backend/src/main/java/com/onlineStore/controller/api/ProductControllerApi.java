package com.onlineStore.controller.api;

import com.onlineStore.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface ProductControllerApi {

    @PostMapping(value="/product/get-all-products")
    ResponseEntity<List<Product>> getAllProducts(Integer key);

    @GetMapping(value="/product/get-by-id/")
    ResponseEntity<Product> getProductById(Integer id);

    @PostMapping(value="/product/get-by-type/")
    ResponseEntity<List<Product>> getProductByType(String type, Integer key);

    @PostMapping(value="/product/get-by-category={catId}")
    ResponseEntity<List<Product>> getProductsByCategory (@PathVariable Integer catId, Integer key);

}
