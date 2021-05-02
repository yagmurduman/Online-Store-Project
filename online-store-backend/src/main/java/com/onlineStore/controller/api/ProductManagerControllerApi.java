package com.onlineStore.controller.api;

import com.onlineStore.model.Product;
import com.onlineStore.model.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ProductManagerControllerApi {

    @PostMapping(value="productManager/add-product")
    ResponseEntity<Product> addProduct(@RequestBody Product product);

    @DeleteMapping(value="productManager/remove-product={pId}")
    ResponseEntity removeProductById(@PathVariable Integer pId);

    @PutMapping(value="productManager/update-product={pId}")
    ResponseEntity<Product> updateProduct (@RequestBody Product product, @PathVariable Integer pId);

//    @PutMapping(value="productManager/update-stock/pId={pId}&stock={stock}")
//    ResponseEntity updateStockofProduct(@PathVariable Integer pId, @PathVariable Integer stock);

    @PutMapping(value="productManager/update-isApproved/cId={cId}&pId={pId}&approval={approval}")
    ResponseEntity updateIsApprovedOfReview (@PathVariable Integer cId, @PathVariable Integer pId, @PathVariable Boolean approval);


}
