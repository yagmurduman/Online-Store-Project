package com.onlineStore.service;

import com.onlineStore.model.Filter;
import com.onlineStore.model.Product;

import java.util.List;

public interface FilterService {

    List<Product> getBookBestSeller();

    List<Product> getMusicBestSeller();

    List<Product> getDVDBestSeller();

    List<Product> FilterByRate();

    List<Product> FilterByPopularity();

    List<Product> FilterByHighLow() ;

    List<Product> FilterByLowHigh() ;

    List<Product> FilterProducts(Filter filter, Integer key);

    List<Product> FilterSearchProducts(String search, Filter filter, Integer key);

}
