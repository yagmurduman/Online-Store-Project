package com.onlineStore.controller.api;

import com.onlineStore.model.Customer;
import com.onlineStore.model.Delivery;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CustomerControllerApi {

    @GetMapping(value="/customer/get-all-customers")
    ResponseEntity<List<Customer>> getAllCustomers();

    @PostMapping(value="/customer/register")
    ResponseEntity<String> registerCustomer(@RequestBody Customer cus);

    @PutMapping(value="/customer/updateName")
    ResponseEntity updateName (String name, Integer cId);

    @PutMapping(value="/customer/updateSurname")
    ResponseEntity updateSurname (String surname, Integer cId);

    @PutMapping(value="/customer/updateAdress")
    ResponseEntity updateAdress (String adress, Integer cId);

    @PutMapping(value="/customer/updatePassword")
    ResponseEntity updatePassword (String password, Integer cId);

    @GetMapping(value="/customer/deliveries")
    ResponseEntity<List<Delivery>> getDeliveryBycId(Integer cId);

    @PostMapping(value="/customer/get-info")
    ResponseEntity<Customer> getCustomerInfo(Integer cId);

}
