package com.onlineStore.repository;


import com.onlineStore.model.Customer;
import com.onlineStore.model.Delivery;
import com.onlineStore.model.Product;
import com.onlineStore.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class CustomerRepository extends JdbcDaoSupport {

    @Autowired
    DataSource dataSource;

    @Autowired
    UsersRepository usersRepository;

    @PostConstruct
    private void initiliaze(){
        setDataSource(dataSource);
    }

    public List<Customer> getAllCustomers() {
        String sql = "SELECT * FROM customer";
        List<Map<String,Object>> rows = getJdbcTemplate().queryForList(sql);

        List<Customer> result = new ArrayList<Customer>();
        for(Map<String, Object> row:rows){
            Customer cus = new Customer();
            cus.setcId((Integer)row.get("cId"));
            cus.setName((String)row.get("name"));
            cus.setSurname((String)row.get("surname"));
            cus.setAddress((String)row.get("address"));
            cus.setEmail((String) row.get("email"));
            result.add(cus);
        }

        return result;
    }

    public List<Customer> findCustomerbyCname(String userName) {

        String sql = "SELECT * FROM customer WHERE cName = '" +  ((userName == null) ? "" : userName)+"'";

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        List<Customer> customers = new ArrayList<Customer>();

        for(Map<String, Object> row:rows){

           Customer cus = new Customer();
            cus.setcId((Integer)row.get("cId"));
            cus.setcName((String)row.get("cName"));
            cus.setName((String)row.get("name"));
            cus.setSurname((String) row.get("surname"));
            cus.setPassword((String) row.get("password"));
            cus.setAddress((String) row.get("address"));
            cus.setEmail((String) row.get("email"));
            customers.add(cus);

        }
        return customers;
    }

    public Customer getCustomerById (Integer cId){
        String sql = "SELECT * FROM customer WHERE cId =? ";
        return (Customer)getJdbcTemplate().queryForObject(sql, new Object[]{cId}, new RowMapper<Object>() {
            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException {
                Customer cus = new Customer();
                cus.setcId(rs.getInt("cId"));
                cus.setcName(rs.getString("cName"));
                cus.setPassword(rs.getString("password"));
                cus.setName(rs.getString("name"));
                cus.setSurname(rs.getString("surname"));
                cus.setEmail(rs.getString("email"));
                cus.setAddress(rs.getString("address"));
                return cus;
            }
        });
    }


    public void insertCustomer(Customer cus){

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        Users user = new Users();
        user.setuId(cus.getcId());
        user.setType(1);
        user.setUserName(cus.getcName());
        user.setPassword(cus.getPassword());
        usersRepository.insertCus(user);


       String sqlId =  "SELECT uId FROM users WHERE userName = '"+  cus.getcName() +"'";
       Integer uId = getJdbcTemplate().queryForObject(sqlId, new Object[] {}, Integer.class);

       String sql = "INSERT INTO customer " + "(cId, cName, name,surname,password, address, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
       getJdbcTemplate().update(sql, new Object[] {uId, cus.getcName(),cus.getName(), cus.getSurname(),encoder.encode(cus.getPassword()), cus.getAddress(), cus.getEmail()});

    }

    public void updateAdressInfo(String adress, Integer cId){
        String sql = "UPDATE customer SET address = ? WHERE cId = ?";

        getJdbcTemplate().update(sql, adress, cId);

    }

    public void removeCustomerById(Integer cId){
        String sql = "DELETE FROM customer WHERE cId = ?";

        getJdbcTemplate().update(sql, cId);
    }

    public void updateNameInfo(String name, Integer cId) {

        String sql = "UPDATE customer SET name = ? WHERE cId = ?";
        getJdbcTemplate().update(sql, name, cId);
    }

    public void updateSurnameInfo(String surname, Integer cId) {

        String sql = "UPDATE customer SET surname = ? WHERE cId = ?";
        getJdbcTemplate().update(sql, surname, cId);

    }

    public void updatePassword(String password, Integer cId) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String sql = "UPDATE customer SET password = ? WHERE cId = ?";
        getJdbcTemplate().update(sql, encoder.encode(password), cId);

    }

    public List<Delivery> deliveryBycId(Integer cId){

        String sql = "SELECT * FROM delivery WHERE cId = " + cId;


        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        List<Delivery> deliveries = new ArrayList<Delivery>();

        for(Map<String, Object> row:rows){
            Delivery delivery = new Delivery();
            delivery.setcId((Integer)row.get("cId"));
            delivery.setdId((Integer)row.get("dId"));
            delivery.setpId((Integer)row.get("pId"));
            delivery.setDate((Date)row.get("dDate"));
            delivery.setQuantity((int)row.get("quantity"));
            delivery.setPrice((Float) row.get("price"));
            delivery.setDelivered(true);
            deliveries.add(delivery);
        }

        return deliveries;
    }


    public Customer getCustomerInfo(Integer cId) {

        String sql = "SELECT * FROM customer WHERE cId = ?";
        return (Customer)getJdbcTemplate().queryForObject(sql, new Object[]{cId}, new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet rs, int rwNumber) throws SQLException {

                Customer cus = new Customer();
                cus.setcId(rs.getInt("cId"));
                cus.setcName(rs.getString("cName"));
                cus.setName(rs.getString("name"));
                cus.setSurname(rs.getString("surname"));
                cus.setPassword(rs.getString("password"));
                cus.setAddress(rs.getString("address"));
                cus.setEmail(rs.getString("email"));
                return cus;

            }
        });

    }


    public String getEmailsByLocation(String location) {

        List<Map<String, Object>> rows;

        if(location.equalsIgnoreCase("all")){
            String sql = "SELECT email FROM customer";
           rows = getJdbcTemplate().queryForList(sql);
        }
        else {
            String sql = "SELECT email FROM customer WHERE address = ?";
           rows = getJdbcTemplate().queryForList(sql, location);
        }

        String emails = "";

        if(rows != null) {
            for (Map<String, Object> row : rows) {
                String email = (String) row.get("email");
                if (emails.equals("")) {
                    emails = email;
                } else
                    emails = emails + ", " + email;
            }
        }

        return emails;

    }
}
