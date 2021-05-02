package com.onlineStore.repository;

import com.onlineStore.model.Category;
import com.onlineStore.model.ProCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CategoryRepository extends JdbcDaoSupport {

    @Autowired
    DataSource dataSource;

    @Autowired
    UsersRepository usersRepository;

    @PostConstruct
    private void initiliaze(){
        setDataSource(dataSource);
    }

    public List<Category> getAllSubCategories (){
        String sql = "SELECT * FROM category";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<Category> result = new ArrayList<Category>();
        for(Map<String, Object> row:rows){
            Category cat = new Category();
            cat.setCatId((Integer) row.get("catId"));
            cat.setCatName((String) row.get("catName"));
            cat.setType((String)row.get("type"));

            result.add(cat);
        }

        return result;
    }

    public List<Category> getSubcategoriesByType (String type){
        String sql = "SELECT * FROM category WHERE type = ?";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql, type);

        List<Category> result = new ArrayList<Category>();
        for(Map<String, Object> row:rows){
            Category cat = new Category();
            cat.setCatId((Integer) row.get("catId"));
            cat.setCatName((String) row.get("catName"));
            cat.setType((String)row.get("type"));

            result.add(cat);
        }

        return result;
    }

    public String getTypeByCatId(Integer catId){
        String sql = "SELECT type FROM  category WHERE catId = ? ";
        String type  = getJdbcTemplate().queryForObject(sql,new Object[]{catId}, String.class);

        return type;
    }



    public void insertCategory (Category cat) {
        String sql = "INSERT INTO category (catName, type) VALUES (?, ?)";
        getJdbcTemplate().update(sql, new Object[]{cat.getCatName(), cat.getType()});
    }

    public void deleteCategoryById (Integer catId){
        String sql = "DELETE FROM category WHERE catId = ?";
        getJdbcTemplate().update(sql, catId);
    }

    public List<ProCat> getAllProductWithCategory(){
        String sql = "SELECT * FROM product_category";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<ProCat> result = new ArrayList<ProCat>();
        for(Map<String, Object> row:rows){
            ProCat proCat = new ProCat();
            proCat.setpId((Integer) row.get("pId"));
            proCat.setCatId((Integer) row.get("catId"));

            result.add(proCat);
        }

        return result;
    }

    public void insertProductWithCategory(ProCat proCat) {
        String sql = "INSERT INTO product_category (pId, catId) VALUES (?,?)";
        getJdbcTemplate().update(sql, new Object[]{proCat.getpId(), proCat.getCatId()});
    }

    public void deleteProductWithCategory(Integer pId, Integer catId){
        String sql = "DELETE FROM product_category WHERE pId=? AND catId =?";
        getJdbcTemplate().update(sql, pId, catId);
    }

    public List<ProCat> getAllSubCategoriesByProductId (Integer pId){
        String sql = "SElECT * FROM product_category WHERE pId = ?";

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql, pId);

        List<ProCat> result = new ArrayList<ProCat>();
        for(Map<String, Object> row:rows){
            ProCat proCat = new ProCat();
            proCat.setpId((Integer) row.get("pId"));
            proCat.setCatId((Integer) row.get("catId"));

            result.add(proCat);
        }

        return result;

    }
}
