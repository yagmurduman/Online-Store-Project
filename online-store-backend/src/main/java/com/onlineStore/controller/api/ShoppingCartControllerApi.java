package com.onlineStore.controller.api;

import com.onlineStore.model.Product;
import com.onlineStore.model.ShoppingCart;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ShoppingCartControllerApi {

    @GetMapping(value="/shoppingCart/get-products-By-Id")
    ResponseEntity<List<ShoppingCart>> getProductsByCustomerId(Integer id);

    @PostMapping(value="/shoppingCart/add-To-Cart")
    ResponseEntity<ShoppingCart> addProductToCart(@RequestBody ShoppingCart cart);

    @DeleteMapping(value="/shoppingCart/delete-By-Id")
    ResponseEntity deleteProductById( Integer cId, Integer pId);

    @DeleteMapping(value="/shoppingCart/{cId}")
    ResponseEntity deleteProductsByCusId(@PathVariable Integer cId);

    @GetMapping(value = "/shoppingCart/totalPrice/{cId}")
    ResponseEntity<Float> getTotalPriceByCustomerId(@PathVariable Integer cId);

    @GetMapping(value = "/shoppingCart/totalDiscount/{cId}")
    ResponseEntity<Float> getTotalDiscountByCustomerId(@PathVariable Integer cId);

    @GetMapping(value="/shoppingCart/checkCode/code={code}&cId={cId}")
    ResponseEntity<Boolean> checkCode(@PathVariable Integer code, @PathVariable Integer cId);
}
