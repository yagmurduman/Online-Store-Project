package com.onlineStore.controller;

import com.onlineStore.controller.api.ProductManagerControllerApi;
import com.onlineStore.model.Customer;
import com.onlineStore.model.Product;
import com.onlineStore.model.Users;
import com.onlineStore.service.ProductManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "/productManager")
public class ProductManagerController implements ProductManagerControllerApi {

    @Autowired
    ProductManagerService productManagerService;

    @Override
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        productManagerService.addProduct(product);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @Override
    public ResponseEntity removeProductById(Integer pId) {
        productManagerService.removeProductById(pId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Product> updateProduct(Product product, Integer pId) {
        productManagerService.updateProduct(product, pId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

//    @Override
//    public ResponseEntity updateStockofProduct(Integer pId, Integer stock) {
//        productManagerService.updateStockofProduct(pId, stock);
//        return new ResponseEntity(HttpStatus.OK);
//    }

    @Override
    public ResponseEntity updateIsApprovedOfReview(Integer cId, Integer pId, Boolean approval) {
        productManagerService.updateIsApprovedOfReview(cId,pId,approval);
        return new ResponseEntity(HttpStatus.OK);
    }


}
