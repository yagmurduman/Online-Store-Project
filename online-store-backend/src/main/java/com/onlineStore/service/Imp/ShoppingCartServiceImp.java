package com.onlineStore.service.Imp;

import com.onlineStore.model.Product;
import com.onlineStore.model.ShoppingCart;
import com.onlineStore.repository.ShoppingCartRepository;
import com.onlineStore.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImp implements ShoppingCartService {

    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartServiceImp (ShoppingCartRepository repo) {this.shoppingCartRepository = repo;}


    @Override
    public List<ShoppingCart> getProductsByCustomerId(Integer id) {
        return shoppingCartRepository.getProductsByCustomerId(id);
    }

    @Override
    public void insertProductToCart(ShoppingCart cart) {
        shoppingCartRepository.insertProductToCart(cart);
    }

    @Override
    public void deleteProductById(Integer cId, Integer pId) {
        shoppingCartRepository.deleteProductById(cId, pId);
    }

    @Override
    public void deleteProductsByCusId(Integer cId) {
        shoppingCartRepository.deleteProductsByCusId(cId);
    }

    @Override
    public Float getTotalPriceByCustomerId(Integer cId) {
        return shoppingCartRepository.getTotalPriceByCustomerId(cId);
    }

    @Override
    public Float getTotalDiscountByCustomerId(Integer cId) {
        return shoppingCartRepository.getTotalDiscountByCustomerId(cId);
    }

    @Override
    public boolean checkCode(Integer code, Integer cId) {
        return shoppingCartRepository.checkCode(code, cId);
    }

    @Override
    public Boolean checkStockOfProduct(ShoppingCart cart) {
        return shoppingCartRepository.checkStock(cart);
    }
}
