package com.onlineStore.controller;


import com.onlineStore.controller.api.FilterControllerApi;
import com.onlineStore.model.Filter;
import com.onlineStore.model.Product;
import com.onlineStore.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value="/filter")
public class FilterController implements FilterControllerApi {

    @Autowired
    FilterService filterService;

    @Override
    public ResponseEntity<List<Product>> filterByRate() {
        List<Product> products = filterService.FilterByRate();
        if (products == null) {
            return new ResponseEntity<>((List<Product>) null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Product>> FilterByHighLow() {
        List<Product> products = filterService.FilterByHighLow();
        if (products == null) {
            return new ResponseEntity<>((List<Product>) null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Product>> FilterByLowHigh() {
        List<Product> products = filterService.FilterByLowHigh();
        if (products == null) {
            return new ResponseEntity<>((List<Product>) null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<List<Product>> FilterByPopulartiy() {
        List<Product> products = filterService.FilterByPopularity();
        if (products == null) {
            return new ResponseEntity<>((List<Product>) null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Product>> getBookBestSeller() {
        List<Product> products = filterService.getBookBestSeller();
        if (products == null) {
            return new ResponseEntity<>((List<Product>) null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @Override
    public ResponseEntity<List<Product>> getMusicBestSeller() {
        List<Product> products = filterService.getMusicBestSeller();
        if (products == null) {
            return new ResponseEntity<>((List<Product>) null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<List<Product>> getDVDBestSeller() {

    List<Product> products = filterService.getDVDBestSeller();
    if (products == null) {
    return new ResponseEntity<>((List<Product>) null, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Product>> FilterProducts(Filter filter,  Integer key) {
        List<Product> products = filterService.FilterProducts(filter, key);
        if (products == null) {
            return new ResponseEntity<>((List<Product>) null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Product>> FilterSearchProducts(String search, Filter filter, Integer key) {
        List<Product> products = filterService.FilterSearchProducts(search, filter, key);
        if (products == null) {
            return new ResponseEntity<>((List<Product>) null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
