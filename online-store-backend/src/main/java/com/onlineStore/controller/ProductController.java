package com.onlineStore.controller;

import com.onlineStore.controller.api.ProductControllerApi;
import com.onlineStore.model.Category;
import com.onlineStore.model.Product;
import com.onlineStore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "/product")
public class ProductController implements ProductControllerApi {

    private ProductService productService;

    @Autowired
    private ProductController (ProductService productService) {this.productService = productService;}

    @Override
    public ResponseEntity<List<Product>> getAllProducts(Integer key) {
        List<Product> products = productService.getAllProducts(key);
        if (products == null) {
            return new ResponseEntity<>((List<Product>) null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Product> getProductById(Integer id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return new ResponseEntity<>((Product) null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Product>> getProductByType(String type, Integer key){

        List<Product> products = productService.getProductByType(type, key);
        if (products == null) {
            return new ResponseEntity<>((List<Product>) null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Product>> getProductsByCategory(Integer catId, Integer key) {
        List<Product> products = productService.getProductsByCategory(catId, key);
        if (products == null) {
            return new ResponseEntity<>((List<Product>) null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


}
