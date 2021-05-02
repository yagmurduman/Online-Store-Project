package com.onlineStore.service;

import com.onlineStore.model.Category;
import com.onlineStore.model.ProCat;

import java.util.HashMap;
import java.util.List;

public interface CategoryService {

    //subcategory table
    List<Category> getAllSubCategories();
    List<Category> getSubCategoriesByType(String type);
    void insertCategory(Category cat);
    void deleteCategoryById (Integer catId);
    String getTypeByCatId(Integer catId);

    //product_category table
    List<ProCat>  getAllProductWithCategory();
    void insertProductWithCategory(ProCat proCat);
    void deleteProductWithCategory(Integer pId, Integer catId);
    List<ProCat> getAllSubcategoriesByProductId (Integer pId);

}
