package com.onlineStore.service.Imp;

import com.onlineStore.model.Category;
import com.onlineStore.model.Customer;
import com.onlineStore.model.Product;
import com.onlineStore.repository.CustomerRepository;
import com.onlineStore.repository.ProductRepository;
import com.onlineStore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    ProductRepository productRepository;

    @Autowired
    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts(Integer key) {
        return productRepository.getAllProducts(key);
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepository.getProductById(id);
    }

    @Override
    public List<Product> getProductByType(String type, Integer key) {
        return productRepository.getProductByType(type, key);
    }

    @Override
    public List<Product> getProductsByCategory(Integer catId, Integer key) {
        return productRepository.getProductsByCategory(catId, key);
    }

}
