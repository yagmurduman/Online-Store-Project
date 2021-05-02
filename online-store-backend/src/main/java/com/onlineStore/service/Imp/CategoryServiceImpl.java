package com.onlineStore.service.Imp;

import com.onlineStore.model.Category;
import com.onlineStore.model.ProCat;
import com.onlineStore.repository.CategoryRepository;
import com.onlineStore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllSubCategories() {
        return categoryRepository.getAllSubCategories();
    }

    @Override
    public List<Category> getSubCategoriesByType(String type) {
        return categoryRepository.getSubcategoriesByType(type);
    }

    @Override
    public void insertCategory(Category cat) {
        categoryRepository.insertCategory(cat);
    }

    @Override
    public void deleteCategoryById(Integer catId) {
        categoryRepository.deleteCategoryById(catId);
    }

    @Override
    public String getTypeByCatId(Integer catId) {
        return categoryRepository.getTypeByCatId(catId);
    }

    @Override
    public List<ProCat>  getAllProductWithCategory() {
        return categoryRepository.getAllProductWithCategory();
    }

    @Override
    public void insertProductWithCategory(ProCat proCat) {
        categoryRepository.insertProductWithCategory(proCat);
    }

    @Override
    public void deleteProductWithCategory(Integer pId, Integer catId) {
        categoryRepository.deleteProductWithCategory(pId,catId);
    }

    @Override
    public List<ProCat> getAllSubcategoriesByProductId(Integer pId) {
        return categoryRepository.getAllSubCategoriesByProductId(pId);
    }
}
