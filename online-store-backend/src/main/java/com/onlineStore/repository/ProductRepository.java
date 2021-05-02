package com.onlineStore.repository;

import com.onlineStore.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


@Repository
public class ProductRepository extends JdbcDaoSupport {

    @Autowired
    DataSource dataSource;

    @Autowired
    ReviewRepository reviewRepository;

    @PostConstruct
    private void initiliaze(){
        setDataSource(dataSource);
    }

    int count = 8;

    public List<Product> getAllProducts(Integer key){
        String sql = "SELECT  * FROM product";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<Product> result = new ArrayList<Product>();
        for(Map<String, Object> row:rows){
            Product pro = new Product();
            pro.setpId((Integer) row.get("pId"));
            pro.setType((String)row.get("type"));
            pro.setpName((String)row.get("pName"));
            pro.setPrice((float) row.get("price"));
            pro.setStock((int)row.get("stock"));
            pro.setImage((String)row.get("image"));
            pro.setPurchasedPrice((float) row.get("purchasedPrice"));
            pro.setDiscount((int)row.get("discount"));
            pro.setSellingPrice((float)row.get("sellingPrice"));
            pro.setDescription((String) row.get("description"));
            pro.setPublisher((String)row.get("publisher"));
            pro.setPublishedDate((Date) row.get("publishedDate"));
            pro.setAvgRate(reviewRepository.getAvgRateOfProductById((Integer) row.get("pId")));
            result.add(pro);
        }

        List<Product> finalProducts = new ArrayList<Product>();
        int a = 0;
        for(int i = key ; i < i + count && i < result.size() && a < count ; i++ ) {
            finalProducts.add(result.get(i));
            a++;
        }

        return finalProducts;
    }

    public List<Product> getAllProducts(){
        String sql = "SELECT  * FROM product";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<Product> result = new ArrayList<Product>();
        for(Map<String, Object> row:rows){
            Product pro = new Product();
            pro.setpId((Integer) row.get("pId"));
            pro.setType((String)row.get("type"));
            pro.setpName((String)row.get("pName"));
            pro.setPrice((float) row.get("price"));
            pro.setStock((int)row.get("stock"));
            pro.setImage((String)row.get("image"));
            pro.setPurchasedPrice((float) row.get("purchasedPrice"));
            pro.setDiscount((int)row.get("discount"));
            pro.setSellingPrice((float)row.get("sellingPrice"));
            pro.setDescription((String) row.get("description"));
            pro.setPublisher((String)row.get("publisher"));
            pro.setPublishedDate((Date) row.get("publishedDate"));
            pro.setAvgRate(reviewRepository.getAvgRateOfProductById((Integer) row.get("pId")));
            result.add(pro);
        }

        return result;
    }

    public Product getProductById(Integer pId){
        String sql = "SELECT * FROM product WHERE pId = ?";
        return (Product)getJdbcTemplate().queryForObject(sql, new Object[]{pId}, new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet rs, int rwNumber) throws SQLException {
                Product pro = new Product();
                pro.setpId(rs.getInt("pId"));
                pro.setType(rs.getString("type"));
                pro.setpName(rs.getString("pName"));
                pro.setPrice(rs.getFloat("price"));
                pro.setStock(rs.getInt("stock"));
                pro.setImage(rs.getString("image"));
                pro.setPurchasedPrice(rs.getFloat("purchasedPrice"));
                pro.setDiscount(rs.getInt("discount"));
                pro.setSellingPrice(rs.getFloat("sellingPrice"));
                pro.setDescription(rs.getString("description"));
                pro.setPublisher(rs.getString("publisher"));
                pro.setPublishedDate(rs.getDate("publishedDate"));
                pro.setAvgRate(reviewRepository.getAvgRateOfProductById(rs.getInt("pId")));
                return pro;
            }
        });
    }

    public List<Product> getProductByType(String type, Integer key){
        String sql = "SELECT * FROM product WHERE type ='"+ type +"'";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        List<Product> products = new ArrayList<Product>();

        for(Map<String, Object> row:rows){
            Product product = new Product();
            product.setpId((Integer)row.get("pId"));
            product.setType((String) row.get("type"));
            product.setpName((String)row.get("pName"));
            product.setPrice((float)row.get("price"));
            product.setStock((int)row.get("stock"));
            product.setImage((String) row.get("image"));
            product.setPurchasedPrice((float)row.get("purchasedPrice"));
            product.setDiscount((int)row.get("discount"));
            product.setSellingPrice((float)row.get("sellingPrice"));
            product.setDescription((String)row.get("description"));
            product.setPublisher((String) row.get("publisher"));
            product.setPublishedDate((Date)row.get("publishedDate"));
            product.setAvgRate(reviewRepository.getAvgRateOfProductById((Integer) row.get("pId")));

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

    public void addProduct(Product pro) {
        String sql = "INSERT INTO product " + "(type, pName, price, stock, image, purchasedPrice, discount, description, publisher, publishedDate, sellingPrice, avgRate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?)";

        getJdbcTemplate().update(sql, new Object[] {pro.getType(), pro.getpName(), pro.getPrice(), pro.getStock(), pro.getImage(), pro.getPurchasedPrice(),
                pro.getDiscount(), pro.getDescription(), pro.getPublisher(), pro.getPublishedDate(), ( pro.getPrice() - (pro.getPrice()* pro.getDiscount() /100)), 0});
    }

    public void removeProductById(Integer pId){
        String sql = "DELETE FROM product WHERE pId = ?";

        getJdbcTemplate().update(sql, pId);
    }

    public void updateProductForPM(Product pro, Integer pId) { //without price, purchasedPrice and discount
        Product product = getProductById(pId);

        if(product != null) {

            if(pro.getType() == "") pro.setType(product.getType());
            if(pro.getpName() == "") pro.setpName(product.getpName());
            if(pro.getStock() == 0) pro.setStock(product.getStock());
            if(pro.getImage() == "") pro.setImage(product.getImage());
            if(pro.getDescription() == "") pro.setDescription(product.getDescription());
            if(pro.getPublisher() == "") pro.setPublisher(product.getPublisher());
            if(pro.getPublishedDate() == null) pro.setPublishedDate(product.getPublishedDate());

            String sql = "UPDATE product SET type = ?, pName =?, stock =?, image =?, description =?, publisher =?, publishedDate =? WHERE pId = ? ";

            getJdbcTemplate().update(sql, pro.getType(), pro.getpName(), pro.getStock(), pro.getImage(),
                    pro.getDescription(), pro.getPublisher(), pro.getPublishedDate(), pId);
        }
    }

    public void updateStockofProduct(Integer pId, Integer stock) {
        String sql = "UPDATE product SET stock = ? WHERE pId = ?";

        getJdbcTemplate().update(sql, stock, pId);
    }

    public void updatePriceOfProduct(Integer pId, Float price) {
        Product pro = getProductById(pId);

        String sql = "UPDATE product SET price = ?, sellingPrice = ? WHERE pId = ?";

        Float sellingPrice =  ( price - (price * pro.getDiscount() /100));

        getJdbcTemplate().update(sql, price, sellingPrice, pId);



    }

    public void updateDiscountOfProduct(Integer pId, Integer discount) {
        Product pro = getProductById(pId);

        String sql = "UPDATE product SET discount = ?, sellingPrice =? WHERE pId = ?";

        Float sellingPrice =  ( pro.getPrice() - (pro.getPrice() * discount /100));

        getJdbcTemplate().update(sql, discount,sellingPrice, pId);

    }

    public List<Product> getProductsByCategory (Integer catId, Integer key){
        String sql = "SELECT * FROM product, product_category WHERE product.pId = product_category.pId AND product_category.catId = ?";

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql, catId);

        List<Product> result = new ArrayList<Product>();
        for(Map<String, Object> row:rows){
            Product pro = new Product();
            pro.setpId((Integer) row.get("pId"));
            pro.setType((String)row.get("type"));
            pro.setpName((String)row.get("pName"));
            pro.setPrice((float) row.get("price"));
            pro.setStock((int)row.get("stock"));
            pro.setImage((String)row.get("image"));
            pro.setPurchasedPrice((float) row.get("purchasedPrice"));
            pro.setDiscount((int)row.get("discount"));
            pro.setSellingPrice((float) row.get("sellingPrice"));
            pro.setDescription((String) row.get("description"));
            pro.setPublisher((String)row.get("publisher"));
            pro.setPublishedDate((Date) row.get("publishedDate"));
            pro.setAvgRate(reviewRepository.getAvgRateOfProductById((Integer) row.get("pId")));
            result.add(pro);
        }

        List<Product> finalProducts = new ArrayList<Product>();

        int a = 0;
        for(int i = key ; i < i+ count && i<result.size() && a < count ; i++ ) {
            finalProducts.add(result.get(i));
            a++;
        }

        return finalProducts;
    }


    public Product getProductsByPrice (Integer pId, Float price) {
        String sql = "SELECT * FROM product WHERE sellingPrice <= ? AND pId = ?";

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql, price, pId);

        List<Product> result = new ArrayList<Product>();
        for (Map<String, Object> row : rows) {
            Product pro = new Product();
            pro.setpId((Integer) row.get("pId"));
            pro.setType((String) row.get("type"));
            pro.setpName((String) row.get("pName"));
            pro.setPrice((float) row.get("price"));
            pro.setStock((int) row.get("stock"));
            pro.setImage((String) row.get("image"));
            pro.setPurchasedPrice((float) row.get("purchasedPrice"));
            pro.setDiscount((int) row.get("discount"));
            pro.setSellingPrice((float) row.get("sellingPrice"));
            pro.setDescription((String) row.get("description"));
            pro.setPublisher((String) row.get("publisher"));
            pro.setPublishedDate((Date) row.get("publishedDate"));
            pro.setAvgRate(reviewRepository.getAvgRateOfProductById((Integer) row.get("pId")));
            result.add(pro);
        }

        if (result.isEmpty()) {
            return null;
        }

        return result.get(0);
    }
}

