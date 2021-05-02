package com.onlineStore.repository;

import com.onlineStore.model.Campaign;
import com.onlineStore.model.Product;
import com.onlineStore.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.NullValue;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import javax.sql.RowSet;
import javax.validation.constraints.Null;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class ShoppingCartRepository extends JdbcDaoSupport {

    @Autowired
    DataSource dataSource;

    @Autowired
    ProductRepository productRepository;

    @PostConstruct
    private void initiliaze(){
        setDataSource(dataSource);
    }

    public List<ShoppingCart> getProductsByCustomerId(Integer cId){
        String sql = "SELECT * FROM shoppingCart WHERE cId = ?";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql, cId);

        List<ShoppingCart> result = new ArrayList<ShoppingCart>();
        for(Map<String, Object> row:rows){
           ShoppingCart cart = new ShoppingCart();
           cart.setcId((Integer) row.get("cId"));
           cart.setpId((Integer) row.get("pId"));
           Product product = productRepository.getProductById(cart.getpId());
           cart.setpName((String) row.get("pName"));
           cart.setQuantity((Integer) row.get("quantity"));
           cart.setPrice((Float) product.getSellingPrice() * cart.getQuantity());
           cart.setImage((String) product.getImage());
           cart.setDiscounted((Float) (product.getPrice() * product.getDiscount()) * cart.getQuantity());
           result.add(cart);
        }

        return result;

    }

    public Float getTotalDiscountByCustomerId(Integer cId){
        String sql = "SELECT SUM(discounted) FROM shoppingcart WHERE cId = '"+ cId +"'";
        Float totalDiscount = getJdbcTemplate().queryForObject(sql, new Object[] {}, Float.class);

        return totalDiscount;
    }

    public Float getTotalPriceByCustomerId(Integer cId){
        String sql = "SELECT SUM(price) FROM shoppingcart WHERE cId = '"+ cId +"'";
        Float totalPrice = getJdbcTemplate().queryForObject(sql, new Object[] {}, Float.class);

        return totalPrice;
    }

    public Boolean checkStock (ShoppingCart cart) {
        Product product = productRepository.getProductById(cart.getpId());
        int stock = product.getStock();
        if (stock >= cart.getQuantity())
            return true;
        else
            return false;
    }

    public void insertProductToCart(ShoppingCart cart){
        String existsSql = "SELECT count(*) FROM  shoppingcart WHERE cId = ? AND pId =?";
        int found  = getJdbcTemplate().queryForObject(existsSql,new Object[]{cart.getcId(), cart.getpId()}, Integer.class);
        Product product = productRepository.getProductById(cart.getpId());
        if(found > 0) {
            String findProduct = "SELECT * FROM shoppingcart WHERE cId =? AND pId =?";
            Map<String,Object> row = getJdbcTemplate().queryForMap(findProduct, cart.getcId(), cart.getpId());
            int quantity = (int )row.get("quantity") + cart.getQuantity();
            float price =  quantity * product.getSellingPrice();
            float discounted = (product.getPrice() * product.getDiscount()/100) * quantity;
            String updatesql = "UPDATE shoppingcart SET quantity = ?, price =?, discounted = ? WHERE cId = ? AND pId =?";
            getJdbcTemplate().update(updatesql, quantity, price, discounted, cart.getcId(), cart.getpId() );

//            row.replace("quantity", quantity);
//            row.replace("price", quantity * (float)row.get("price"));
        }
        else{
            String insertsql = "INSERT INTO shoppingcart" + "(cId, pId, pName, quantity,price, image, discounted) VALUES (?, ?, ?, ?, ?, ?, ?)";
            String name  = product.getpName();
            float totalPrice = product.getSellingPrice() * cart.getQuantity();
            cart.setpName(name);
            cart.setPrice(totalPrice);
            cart.setImage(product.getImage());
            cart.setDiscounted((product.getPrice() * product.getDiscount()/100) * cart.getQuantity());

            getJdbcTemplate().update(insertsql, new Object[]{cart.getcId(), cart.getpId(),cart.getpName(), cart.getQuantity(), cart.getPrice(), cart.getImage(), cart.getDiscounted()});
        }
    }

    public void deleteProductById(Integer cId, Integer pId){
        String sql = "DELETE FROM shoppingcart WHERE cId = ? AND pId = ?";

        getJdbcTemplate().update(sql, cId, pId);
    }

    public void deleteProductsByCusId(Integer cId) { //when checkout
        String sql = "DELETE FROM shoppingcart WHERE cId = ?";

        getJdbcTemplate().update(sql, cId);
    }

    public boolean checkCode (Integer code, Integer cId) {
        String sql = "SELECT * FROM campaign WHERE code = ?";

        Campaign cam = (Campaign) getJdbcTemplate().queryForObject(sql, new Object[]{code}, new RowMapper<Object>() {
            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException {
                Campaign campaign = new Campaign();
                campaign.setCamId(rs.getInt("camId"));
                campaign.setpId(rs.getInt("pId"));
                campaign.setDiscount(rs.getInt("discount"));
                campaign.setCode(rs.getInt("code"));
                campaign.setValid(rs.getBoolean("isValid"));
                return campaign;
            }
        });


        if (cam == null || !cam.isValid()){
            return false;
        }
        else{
            Product pro = productRepository.getProductById(cam.getpId());

            String quantitysql = "SELECT quantity FROM shoppingcart WHERE cID =? AND pId = ?";
            int quantity  = getJdbcTemplate().queryForObject(quantitysql,new Object[]{cId, cam.getpId()}, Integer.class);

            String changePrice = "UPDATE shoppingcart SET price = ? WHERE pId = ?";
            getJdbcTemplate().update(changePrice, (pro.getSellingPrice() * (100 - cam.getDiscount())/100) * quantity, cam.getpId());
            return true;

        }

    }


}
