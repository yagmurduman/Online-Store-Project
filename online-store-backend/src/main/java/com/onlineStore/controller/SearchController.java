package com.onlineStore.controller;

import com.onlineStore.controller.api.SearchControllerApi;
import com.onlineStore.model.Customer;
import com.onlineStore.model.Pair;
import com.onlineStore.model.Product;
import com.onlineStore.service.SearchService;
import io.micrometer.core.instrument.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value="/search")
public class SearchController implements SearchControllerApi {

    @Autowired
    SearchService searchService;

    @Override
    public ResponseEntity<Pair> searchProducts(String search){
        final Pair products= searchService.searchProducts(search);
        if (products == null) {
            return new ResponseEntity<>((Pair) null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Product>> searchProductsLimit(String search, Integer key) {
        final List<Product> products= searchService.searchProductsLimit(search, key);
        if (products == null) {
            return new ResponseEntity<>((List<Product>) null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}


