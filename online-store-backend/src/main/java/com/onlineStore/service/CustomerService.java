package com.onlineStore.service;

import com.onlineStore.model.Customer;
import com.onlineStore.model.Delivery;
import com.onlineStore.model.Product;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public interface CustomerService {
    //get all customer data
    List<Customer> getAllCustomers();

    //customer login
    Integer loginCustomer(String username,String password);

    //customer register
    Integer registerCustomer(Customer cus);

    void updateAdressInfo(String adress, Integer cId);

    void removeCustomerById(Integer cId);

    void updateNameInfo(String name, Integer cId);

    void updateSurnameInfo(String surname, Integer cId);

    void updatePassword(String password, Integer cId);

    List<Delivery> deliveryBycId(Integer cId);

    Customer getCustomerInfo(Integer cId);


}
