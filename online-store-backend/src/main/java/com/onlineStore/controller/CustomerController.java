package com.onlineStore.controller;
import com.onlineStore.controller.api.CustomerControllerApi;
import com.onlineStore.model.Customer;
import com.onlineStore.model.Delivery;
import com.onlineStore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value="/customer")
public class CustomerController implements CustomerControllerApi {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    public ResponseEntity<String> get() {
        return new ResponseEntity<>("Customer", HttpStatus.OK);
    }

    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> test = customerService.getAllCustomers();
        if (test == null) {
            return new ResponseEntity<>((List<Customer>) null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

    public ResponseEntity<String> registerCustomer(@RequestBody Customer cus){
        Integer id = customerService.registerCustomer(cus);
        if(id == -1) {
            return new ResponseEntity<String>("User " + cus.getcName() + " already exists.", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<String>("Customer " + cus.getcName() + " registered successfully.", HttpStatus.OK);
    }

    @Override
    public ResponseEntity updateName(String name, Integer cId) {
        customerService.updateNameInfo(name,cId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity updateSurname(String surname, Integer cId) {
        customerService.updateSurnameInfo(surname, cId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity updateAdress(String adress, Integer cId) {
        customerService.updateAdressInfo(adress,cId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity updatePassword(String password, Integer cId) {
        customerService.updatePassword(password, cId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Delivery>> getDeliveryBycId(Integer cId) {
        List<Delivery> test = customerService.deliveryBycId(cId);
        if (test == null) {
            return new ResponseEntity<>((List<Delivery>) null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Customer> getCustomerInfo(Integer cId) {

        Customer test = customerService.getCustomerInfo(cId);
        if (test == null) {
            return new ResponseEntity<>((Customer) null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(test, HttpStatus.OK);
    }
}
