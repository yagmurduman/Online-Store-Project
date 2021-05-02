package com.onlineStore.service;

import com.onlineStore.model.Product;
import com.onlineStore.model.ShoppingCart;
import io.swagger.models.auth.In;

import java.util.List;

public interface ShoppingCartService {

    List<ShoppingCart> getProductsByCustomerId(Integer id);
    Boolean checkStockOfProduct (ShoppingCart cart);
    void insertProductToCart(ShoppingCart cart);
    void deleteProductById(Integer cId, Integer pId);
    void deleteProductsByCusId(Integer cId);
    Float getTotalPriceByCustomerId(Integer cId);
    Float getTotalDiscountByCustomerId(Integer cId);
    boolean checkCode (Integer code, Integer cId);
}
