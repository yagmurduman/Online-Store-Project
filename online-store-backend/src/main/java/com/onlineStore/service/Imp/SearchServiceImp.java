package com.onlineStore.service.Imp;

import com.onlineStore.model.Product;
import com.onlineStore.model.Pair;
import com.onlineStore.repository.SearchRepository;
import com.onlineStore.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImp implements SearchService {


    @Autowired
    SearchRepository searchRepository;

    @Override
    public Pair searchProducts(String search) {
        return searchRepository. searchProducts(search);
    }

    @Override
    public List<Product> searchProductsLimit(String search, Integer key) {
        List<Product>  nsearch = searchRepository. searchProductsLimit(search, key);
        return nsearch;
    }
}
