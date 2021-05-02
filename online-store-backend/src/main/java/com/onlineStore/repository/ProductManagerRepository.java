package com.onlineStore.repository;

import com.onlineStore.model.Product;
import com.onlineStore.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ProductManagerRepository extends JdbcDaoSupport {

    @Autowired
    DataSource dataSource;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @PostConstruct
    private void initiliaze(){
        setDataSource(dataSource);
    }

    public void addProduct (Product product) {productRepository.addProduct(product);}

    public void removeProductById (Integer pId) {productRepository.removeProductById(pId);}

    public void updateProduct (Product product, Integer pId) {productRepository.updateProductForPM(product, pId);}

//    public void updateStockofProduct (Integer pId, Integer stock) {productRepository.updateStockofProduct(pId, stock);}

    public void updateIsApprovedOfReview (Integer cId, Integer pId, Boolean approval) {reviewRepository.updateIsApproved(cId, pId, approval);}


    public List<Users> getAllProductManagers() {
        String sql = "SELECT * FROM users";
        List<Map<String,Object>> rows = getJdbcTemplate().queryForList(sql);

        List<Users> result = new ArrayList<Users>();
        for(Map<String, Object> row:rows){
            Users pm = new Users();
            pm.setuId((Integer)row.get("uId"));
            pm.setUserName((String)row.get("userName"));
            pm.setPassword((String)row.get("password"));
            result.add(pm);
        }

        return result;
    }

    public List<Users> findByUsername(String userName) {

        String sql = "SELECT * FROM users WHERE userName = '" +  ((userName == null) ? "" : userName)+"'";

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
            List<Users> pms = new ArrayList<Users>();

        for(Map<String, Object> row:rows){

            Users pm =  new Users();
            pm.setuId((Integer)row.get("uId"));
            pm.setType((Integer)row.get("type"));
            pm.setUserName((String)row.get("userName"));
            pm.setPassword((String) row.get("password"));
            pms.add(pm);

        }
        return pms;
    }

    public void insertPM(Users pm){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String sql = "INSERT INTO users " + "(uId, type, userName, password) VALUES (?, ?, ?, ?)";

        getJdbcTemplate().update(sql, new Object[] {pm.getuId(), 3, pm.getUserName(), encoder.encode(pm.getPassword())});
    }

    public List<Users> getAllPM(){
        String sql = "SELECT * FROM users WHERE type = 3";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        List<Users> users = new ArrayList<Users>();

        for(Map<String, Object> row:rows){

            Users user =  new Users();
            user.setuId((Integer)row.get("uId"));
            user.setType((Integer)row.get("type"));
            user.setUserName((String)row.get("userName"));
            user.setPassword((String) row.get("password"));
            users.add(user);

        }
        return users;

    }
}
