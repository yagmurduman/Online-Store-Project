package com.onlineStore.controller.api;

import com.onlineStore.model.Delivery;
import com.onlineStore.model.Filter;
import com.onlineStore.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface FilterControllerApi {


    @GetMapping(value="/filter/rate")
    ResponseEntity<List<Product>> filterByRate();

    @GetMapping(value="/filter/price-high-low")
    ResponseEntity<List<Product>> FilterByHighLow();

    @GetMapping(value="/filter/price-low-high")
    ResponseEntity<List<Product>> FilterByLowHigh();

    @GetMapping(value="/filter/popularity")
    ResponseEntity<List<Product>> FilterByPopulartiy();

    @GetMapping(value="/filter/bestseller-book")
    ResponseEntity<List<Product>> getBookBestSeller();

    @GetMapping(value="/filter/bestseller-music")
    ResponseEntity<List<Product>> getMusicBestSeller();

    @GetMapping(value="/filter/bestseller-dvd")
    ResponseEntity<List<Product>> getDVDBestSeller();

    @PostMapping(value="/filter/extended-filter")
    ResponseEntity<List<Product>> FilterProducts(Filter filter,  Integer key);

    @PostMapping(value="/filter/search-extended-filter")
    ResponseEntity<List<Product>> FilterSearchProducts(String search, Filter filter,  Integer key);

}
