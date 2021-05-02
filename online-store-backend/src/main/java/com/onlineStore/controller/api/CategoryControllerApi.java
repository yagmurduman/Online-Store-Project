package com.onlineStore.controller.api;

import com.onlineStore.model.Category;
import com.onlineStore.model.ProCat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

public interface CategoryControllerApi {

    //category
    @GetMapping(value="/category/get-all-subcategories")
    ResponseEntity<List<Category>> getAllSubCategories ();

    @GetMapping(value="/category/get-subcategories-by-type={type}")
    ResponseEntity<List<Category>> getSubcategoriesByType (@PathVariable String type);

    @PostMapping(value="category/insert-category")
    ResponseEntity<Category> insertCategory (@RequestBody Category category);

    @DeleteMapping(value="category/delete-category={catId}")
    ResponseEntity<String> deleteCategoryById (@PathVariable Integer catId);

    @GetMapping(value="category/get-type-by-catId={catId}")
    ResponseEntity<String> getTypeByCatId(@PathVariable Integer catId);

    //product_category
    @GetMapping(value="/category/get-all-product-with-category")
    ResponseEntity<List<ProCat> > getAllProductWithCategory ();

    @PostMapping(value="/category/insert-product-with-category")
    ResponseEntity<ProCat> insertProductWithCategory(@RequestBody ProCat proCat);

    @DeleteMapping(value="/category/delete-product-with-category/pId={pId}&catId={catId}")
    ResponseEntity<String> deleteProductWithCategory(@PathVariable Integer pId, @PathVariable Integer catId);

    @GetMapping(value="/category/get-all-subcategories-by-pId={pId}")
    ResponseEntity<List<ProCat>> getAllSubcategoriesByProductId (@PathVariable Integer pId);
}
