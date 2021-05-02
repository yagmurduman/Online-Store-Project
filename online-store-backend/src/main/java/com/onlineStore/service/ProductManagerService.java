package com.onlineStore.service;

import com.onlineStore.model.Product;
import com.onlineStore.model.Users;

import java.util.List;

public interface ProductManagerService {

    void addProduct(Product product);
    void removeProductById(Integer pId);
    void updateProduct (Product product, Integer pId);
//    void updateStockofProduct (Integer pId, Integer stock);
    void updateIsApprovedOfReview (Integer cId, Integer pId, Boolean approval);
    Integer registerPM(Users pm);
    List<Users> getAllPM ();
}
