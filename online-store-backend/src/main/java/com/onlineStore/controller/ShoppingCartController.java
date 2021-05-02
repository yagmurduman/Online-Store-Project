package com.onlineStore.controller;

import com.onlineStore.controller.api.ShoppingCartControllerApi;
import com.onlineStore.model.Product;
import com.onlineStore.model.ShoppingCart;
import com.onlineStore.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "/shoppingCart")
public class ShoppingCartController implements ShoppingCartControllerApi {

    private ShoppingCartService shoppingCartService;

    @Autowired
    private ShoppingCartController (ShoppingCartService shoppingCartService) {this.shoppingCartService = shoppingCartService;}

    @Override
    public ResponseEntity<List<ShoppingCart>> getProductsByCustomerId(Integer id) {
        List<ShoppingCart> carts = shoppingCartService.getProductsByCustomerId(id);
        if (carts == null) {
            return new ResponseEntity<>((List<ShoppingCart>) null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ShoppingCart> addProductToCart(@RequestBody ShoppingCart cart) {
        Boolean stockExist = shoppingCartService.checkStockOfProduct(cart);
        if(stockExist){
            shoppingCartService.insertProductToCart(cart);
            return new ResponseEntity<>(cart, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity deleteProductById(Integer cId, Integer pId) {
        shoppingCartService.deleteProductById(cId,pId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity deleteProductsByCusId(Integer cId) {
        shoppingCartService.deleteProductsByCusId(cId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Float> getTotalPriceByCustomerId(Integer cId) {
        Float totalPrice = shoppingCartService.getTotalPriceByCustomerId(cId);
        if(totalPrice == null){ return new ResponseEntity<>((Float) null, HttpStatus.BAD_REQUEST);}
        return new ResponseEntity<>(totalPrice, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Float> getTotalDiscountByCustomerId(Integer cId) {
        Float totalDiscount = shoppingCartService.getTotalDiscountByCustomerId(cId);
        if(totalDiscount == null){ return new ResponseEntity<>((Float) null, HttpStatus.BAD_REQUEST);}
        return new ResponseEntity<>(totalDiscount, HttpStatus.OK);
    }

    //returns 0 if the code is not valid, otherwise returns the discounted price
    @Override
    public ResponseEntity<Boolean> checkCode(Integer code, Integer cId) {
        Boolean isValid = shoppingCartService.checkCode(code, cId);
        return new ResponseEntity<>(isValid, HttpStatus.OK);
    }
}
