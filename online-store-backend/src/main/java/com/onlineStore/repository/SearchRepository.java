package com.onlineStore.repository;



import com.onlineStore.model.Pair;
import com.onlineStore.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class SearchRepository extends JdbcDaoSupport {

    @Autowired
    DataSource dataSource;

    @Autowired
    ProductRepository productRepository;

    @PostConstruct
    private void initiliaze(){
        setDataSource(dataSource);
    }

    int count = 8;


    public List<Product>  getAllSearch (String search){

        String sql = "SELECT  * FROM product WHERE pName LIKE '%" +  ((search == null) ? "" : search)+"%'"
            + " OR (publisher LIKE '%" +  ((search == null) ? "" : search)+"%') ";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        List<Product> products = new ArrayList<Product>();

        for(Map<String, Object> row:rows){
            Product product = new Product();
            product = productRepository.getProductById(((Integer) row.get("pId")));
            products.add(product);
        }
        return products;

    }
    public Pair searchProducts(String search){

        String sql = "SELECT  * FROM product WHERE pName LIKE '%" +  ((search == null) ? "" : search)+"%'"
            + " OR (publisher LIKE '%" +  ((search == null) ? "" : search)+"%') LIMIT " + count;
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        List<Product> products = new ArrayList<Product>();

        for(Map<String, Object> row:rows){
            Product product = new Product();
            product = productRepository.getProductById(((Integer) row.get("pId")));
            products.add(product);
        }
        return new Pair(search, products);

    }

    public List<Product> searchProductsLimit(String search,Integer key){
        String sql = "SELECT  * FROM product WHERE pName LIKE '%" +  ((search == null) ? "" : search)+"%'"
            + " OR (publisher LIKE '%" +  ((search == null) ? "" : search)+"%')";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        List<Product> products = new ArrayList<Product>();

        for(Map<String, Object> row:rows){
            Product product = new Product();
            product = productRepository.getProductById(((Integer) row.get("pId")));
            products.add(product);
        }

        List<Product> finalProducts = new ArrayList<Product>();

        int a = 0;
        for(int i = key ; i < i+ count && i<products.size() && a < count ; i++ ) {
            finalProducts.add(products.get(i));
            a++;
        }

        return finalProducts;

    }
}
