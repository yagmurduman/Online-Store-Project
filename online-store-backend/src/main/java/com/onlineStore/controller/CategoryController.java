package com.onlineStore.controller;

import com.onlineStore.controller.api.CategoryControllerApi;
import com.onlineStore.model.Category;
import com.onlineStore.model.ProCat;
import com.onlineStore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController(value = "/category")
public class CategoryController implements CategoryControllerApi {

    @Autowired
    CategoryService categoryService;

    @Override
    public ResponseEntity<List<Category>> getAllSubCategories() {
        List<Category> subCats = categoryService.getAllSubCategories();
        if (subCats == null) {
            return new ResponseEntity<>((List<Category>) null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(subCats, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Category>> getSubcategoriesByType(String type) {
        List<Category> subCats = categoryService.getSubCategoriesByType(type);
        if (subCats == null) {
            return new ResponseEntity<>((List<Category>) null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(subCats, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Category> insertCategory(Category category) {
        categoryService.insertCategory(category);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteCategoryById(Integer catId) {
        categoryService.deleteCategoryById(catId);
        return new ResponseEntity<>("Category "+ catId+" is deleted",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> getTypeByCatId(Integer catId) {
        String type = categoryService.getTypeByCatId(catId);
        if(type == null){
            return new ResponseEntity<>("Category with id:"+ catId + "does not exist.",HttpStatus.BAD_REQUEST );
        }
        return new ResponseEntity<>(type, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProCat> > getAllProductWithCategory() {
        List<ProCat>  proCats = categoryService.getAllProductWithCategory();
        if(proCats == null){
            return new ResponseEntity<>((List<ProCat> ) null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(proCats, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProCat> insertProductWithCategory(ProCat proCat) {
        categoryService.insertProductWithCategory(proCat);
        return new ResponseEntity<>(proCat, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteProductWithCategory(Integer pId, Integer catId) {
        categoryService.deleteProductWithCategory(pId, catId);
        return new ResponseEntity<>("Product with category belongs to (pId, catId) ( "+ pId + "," + catId + ") is deleted", HttpStatus.OK );
    }

    @Override
    public ResponseEntity<List<ProCat>> getAllSubcategoriesByProductId(Integer pId) {
        List<ProCat> proCats = categoryService.getAllSubcategoriesByProductId(pId);
        if(proCats == null){
            return new ResponseEntity<>((List<ProCat>) null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(proCats, HttpStatus.OK);
    }
}
