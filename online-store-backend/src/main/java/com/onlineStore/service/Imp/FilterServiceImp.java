package com.onlineStore.service.Imp;

import com.onlineStore.model.Filter;
import com.onlineStore.model.Product;
import com.onlineStore.repository.FilterRepository;
import com.onlineStore.repository.SearchRepository;
import com.onlineStore.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilterServiceImp implements FilterService {
    @Autowired
    FilterRepository filterRepository;

    @Override
    public List<Product> FilterByRate() {
        return filterRepository.FilterByRate();
    }

    @Override
    public List<Product> FilterByPopularity() {
        return filterRepository.FilterByPopularity();
    }

    @Override
    public List<Product> FilterByHighLow() {
        return filterRepository.FilterByHighLow();
    }

    @Override
    public List<Product> FilterByLowHigh() {
        return filterRepository.FilterByLowHigh();
    }


    @Override
    public List<Product> FilterProducts(Filter filter,  Integer key) {
        return filterRepository.FilterProducts(filter, key);
    }

    @Override
    public List<Product> FilterSearchProducts(String search, Filter filter, Integer key) {
        return filterRepository.FilterSearchProducts(search, filter, key);
    }

    @Override
    public List<Product> getBookBestSeller() {

        return filterRepository.getBookBestSeller();
    }

    @Override
    public List<Product> getMusicBestSeller() {
        return filterRepository.getMusicBestSeller();
    }

    @Override
    public List<Product> getDVDBestSeller() {
        return filterRepository.getDVDBestSeller();
    }
}
