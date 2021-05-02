package com.onlineStore.repository;

import com.onlineStore.model.*;
import com.zaxxer.hikari.util.SuspendResumeLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class FilterRepository extends JdbcDaoSupport  {

    @Autowired
    DataSource dataSource;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    SearchRepository searchRepository;


    @Autowired
    ReviewRepository reviewRepository;

    @PostConstruct
    private void initiliaze(){
        setDataSource(dataSource);
    }


    public List<Product> getBookBestSeller() {
        String sql = "SELECT * FROM review ORDER BY rate DESC" ;

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        List<Product> books = new ArrayList<Product>();

        for(Map<String, Object> row:rows){
            if((Boolean)row.get("isApproved") == true){
                Product product = new Product();
                product = productRepository.getProductById(((Integer) row.get("pId")));
                if(product.getType().equals("book")  && books.size() < 5){
                    books.add(product);
                }
            }
        }
        return books;
    }

    public List<Product> getMusicBestSeller() {
        String sql = "SELECT * FROM review ORDER BY rate DESC" ;

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        List<Product> musics = new ArrayList<Product>();

        for(Map<String, Object> row:rows){
            if((Boolean)row.get("isApproved") == true){
                Product product = new Product();
                product = productRepository.getProductById(((Integer) row.get("pId")));
                if(product.getType().equals("music")   && musics.size() < 5){
                    musics.add(product);
                }
            }

        }
        return musics;
    }


    public List<Product> getDVDBestSeller() {
        String sql = "SELECT * FROM review ORDER BY rate DESC" ;

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        List<Product> dvds = new ArrayList<Product>();

        for(Map<String, Object> row:rows){
            if((Boolean)row.get("isApproved") == true){
                Product product = new Product();
                product = productRepository.getProductById(((Integer) row.get("pId")));
                if(product.getType().equals("dvd")   && dvds.size() < 5){
                    dvds.add(product);
                }
            }

        }
        return dvds;
    }
    public List<Product> FilterByRate() {

        String sql = "SELECT * FROM review ORDER BY rate DESC" ;

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        List<Product> products = new ArrayList<Product>();

        for(Map<String, Object> row:rows){
            if((Boolean)row.get("isApproved") == true){
                Product product = new Product();
                product = productRepository.getProductById(((Integer) row.get("pId")));
                products.add(product);
            }

        }
        return products;
    }

    public List<Product> FilterByHighLow() {

        String sql = "SELECT * FROM product ORDER BY sellingPrice DESC" ;
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<Product> products = new ArrayList<Product>();
        for(Map<String, Object> row:rows){
            Product pro = new Product();
            pro = productRepository.getProductById((Integer) row.get("pId"));
            products.add(pro);
        }

        return products;
    }

    public List<Product> FilterByLowHigh() {

        String sql = "SELECT * FROM product ORDER BY sellingPrice ASC" ;

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        List<Product> products = new ArrayList<Product>();

        for(Map<String, Object> row:rows){
            Product pro = new Product();
            pro = productRepository.getProductById((Integer) row.get("pId"));
            products.add(pro);
        }
        return products;

    }


    public List<Product> FilterByPopularity() {

        String sql = "SELECT pId, COUNT(*) as count FROM delivery GROUP BY pId ORDER BY count DESC";

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        List<Product> products = new ArrayList<Product>();
        for(Map<String, Object> row:rows){
                Product product = new Product();
                product = productRepository.getProductById(((Integer) row.get("pId")));
                products.add(product);

        }
        return products;
    }

   public List<Product> FilterProductsByPriceReview(Filter filter) {

        List<Product> products = new ArrayList<Product>();

        if (filter.getPrice() != 0 && filter.getRate() != 0) {
            String sql = "SELECT *, AVG(rate) FROM review WHERE isApproved = 1 GROUP BY pId HAVING AVG(rate) >= "+ filter.getRate();
            List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
            for (Map<String, Object> row : rows) {
                if ((Boolean) row.get("isApproved") == true) {
                    Product product = productRepository.getProductsByPrice(((Integer) row.get("pId")), filter.getPrice());
                    if (product != null)
                        products.add(product);
                }

            }
        }
        else if(filter.getPrice() == 0 && filter.getRate() != 0){
            String sql = "SELECT *, AVG(rate) FROM review WHERE isApproved = 1 GROUP BY pId HAVING AVG(rate) >= "+ filter.getRate();
            List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
            for (Map<String, Object> row : rows) {
                if ((Boolean) row.get("isApproved") == true) {
                    Product product =  productRepository.getProductById(((Integer) row.get("pId")));
                    if (product != null)
                    products.add(product);
                }

            }
        }
        else if(filter.getPrice() != 0 && filter.getRate() == 0){
            String sql = "SELECT * FROM product WHERE sellingPrice <= " + filter.getPrice();
            List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
            for (Map<String, Object> row : rows) {
                    Product product =  productRepository.getProductById(((Integer) row.get("pId")));
                    if (product != null)
                        products.add(product);
                }
            }
        else
            products = productRepository.getAllProducts();
        return products;
    }


    int count = 8;

    public List<Product> FilterProducts(Filter filter, Integer key) {

        List<Product> products = new ArrayList<Product>();
        if (filter.getOrderType().equals(1)) {
            List<Product> tempProducts = new ArrayList<Product>();
            tempProducts = FilterByPopularity();
            for (Product p : tempProducts) {
                if(filter.getCategory().equals(0) && p.getType().equals(filter.getType())){
                    products.add(p);
                }
                else{
                    List<ProCat> cats = categoryRepository.getAllSubCategoriesByProductId(p.getpId());
                    for (ProCat proCat : cats) {
                        if (p.getType().equals(filter.getType()) && proCat.getCatId().equals(filter.getCategory())) {
                            products.add(p);
                        }
                    }
                }
            }
        } else if (filter.getOrderType().equals(2)) {
            List<Product> tempProducts = new ArrayList<Product>();
            tempProducts = FilterByRate();
            for (Product p : tempProducts) {
                if(filter.getCategory().equals(0) && p.getType().equals(filter.getType())){
                    products.add(p);
                }
                else{
                    List<ProCat> cats = categoryRepository.getAllSubCategoriesByProductId(p.getpId());
                    for (ProCat proCat : cats) {
                        if (p.getType().equals(filter.getType()) && proCat.getCatId().equals(filter.getCategory())) {
                            products.add(p);
                        }
                    }
                }
            }
        } else if (filter.getOrderType().equals(3)) {
            List<Product> tempProducts = new ArrayList<Product>();
            tempProducts = FilterByLowHigh();
            for (Product p : tempProducts) {
                if(filter.getCategory().equals(0) && p.getType().equals(filter.getType())){
                    products.add(p);
                }
                else{
                    List<ProCat> cats = categoryRepository.getAllSubCategoriesByProductId(p.getpId());
                    for (ProCat proCat : cats) {
                        if (p.getType().equals(filter.getType()) && proCat.getCatId().equals(filter.getCategory())) {
                            products.add(p);
                        }
                    }
                }
            }
        } else if (filter.getOrderType().equals(4)) {
            List<Product> tempProducts = new ArrayList<Product>();
            tempProducts = FilterByHighLow();
            for (Product p : tempProducts) {
                if(filter.getCategory().equals(0) && p.getType().equals(filter.getType())){
                    products.add(p);
                }
                else{
                    List<ProCat> cats = categoryRepository.getAllSubCategoriesByProductId(p.getpId());
                    for (ProCat proCat : cats) {
                        if (p.getType().equals(filter.getType()) && proCat.getCatId().equals(filter.getCategory())) {
                            products.add(p);
                        }
                    }
                }
            }
        }
        else {
            List<Product> tempProducts = new ArrayList<Product>();
            tempProducts = productRepository.getAllProducts();
            for (Product p : tempProducts) {
                if(filter.getCategory().equals(0) && p.getType().equals(filter.getType())){
                    products.add(p);
                }
                else{
                    List<ProCat> cats = categoryRepository.getAllSubCategoriesByProductId(p.getpId());
                    for (ProCat proCat : cats) {
                        if (p.getType().equals(filter.getType()) && proCat.getCatId().equals(filter.getCategory())) {
                            products.add(p);
                        }
                    }
                }
            }
        }

        List<Product> temp2Products = FilterProductsByPriceReview(filter);
        List<Product> temp3Products = new ArrayList<Product>();
        for (Product p1 : products) {
            for (Product p2 : temp2Products) {
                if (p1.getpId().equals(p2.getpId())) {
                    temp3Products.add(p1);
                }
            }
        }

        List<Product> finalProducts = new ArrayList<Product>();
        int a = 0;
        for(int i = key ; i < i+ count && i<temp3Products.size() && a < count ; i++ ) {
            finalProducts.add(temp3Products.get(i));
            a++;
        }
        return finalProducts;
    }

    public List<Product> FilterSearchProducts(String search, Filter filter, Integer key) {

        List<Product> products = new ArrayList<Product>();
        if (filter.getOrderType().equals(1)) {
            List<Product> tempProducts = new ArrayList<Product>();
            tempProducts = FilterByPopularity();
            for (Product p : tempProducts) {
                if( p.getType().equals(filter.getType()) || filter.getType().equals("all")){
                    products.add(p);
                }
            }
        } else if (filter.getOrderType().equals(2)) {
            List<Product> tempProducts = new ArrayList<Product>();
            tempProducts = FilterByRate();
            for (Product p : tempProducts) {
                if(p.getType().equals(filter.getType()) || filter.getType().equals("all")){
                    products.add(p);
                }
            }
        } else if (filter.getOrderType().equals(3)) {
            List<Product> tempProducts = new ArrayList<Product>();
            tempProducts = FilterByLowHigh();
            for (Product p : tempProducts) {
                if(p.getType().equals(filter.getType()) || filter.getType().equals("all")){
                    products.add(p);
                }
            }
        } else if (filter.getOrderType().equals(4)) {
            List<Product> tempProducts = new ArrayList<Product>();
            tempProducts = FilterByHighLow();
            for (Product p : tempProducts) {
                if(p.getType().equals(filter.getType()) || filter.getType().equals("all")) {
                    products.add(p);
                }
            }
        }
        else {
            List<Product> tempProducts = new ArrayList<Product>();
            tempProducts = productRepository.getAllProducts();
            for (Product p : tempProducts) {
                if(p.getType().equals(filter.getType()) || filter.getType().equals("all")){
                    products.add(p);
                }
            }
        }

        List<Product> temp2Products = FilterProductsByPriceReview(filter);
        List<Product> temp3Products = new ArrayList<Product>();
        for (Product p1 : products) {
            for (Product p2 : temp2Products) {
                if (p1.getpId().equals(p2.getpId())) {
                    temp3Products.add(p1);
                }
            }
        }

        List<Product> temp4Products = searchRepository.getAllSearch(search);
        List<Product> tempFinalProducts = new ArrayList<Product>();
        for (Product p1 : temp3Products) {
            for (Product p2 : temp4Products) {
                if (p1.getpId().equals(p2.getpId())) {
                    tempFinalProducts.add(p1);
                }
            }
        }

        List<Product> finalProducts = new ArrayList<Product>();
        int a = 0;
        for(int i = key ; i < i+ count && i<tempFinalProducts.size() && a < count ; i++ ) {
            finalProducts.add(tempFinalProducts.get(i));
            a++;
        }
        return finalProducts;
    }
}
