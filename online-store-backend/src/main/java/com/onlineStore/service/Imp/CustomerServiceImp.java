package com.onlineStore.service.Imp;

import com.onlineStore.model.Customer;
import com.onlineStore.model.Delivery;
import com.onlineStore.model.Users;

import com.onlineStore.repository.CustomerRepository;
import com.onlineStore.repository.UsersRepository;
import com.onlineStore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImp implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    UsersRepository usersRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    public Integer registerCustomer(Customer cus) {


        List<Users> list = usersRepository.findByUsername(cus.getcName());


        if (list.size() > 0) {
            return -1;
        }

        customerRepository.insertCustomer(cus) ;
        return 1;

    }


    public Integer loginCustomer(String username,String password) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        List<Customer> userEntityList = customerRepository.findCustomerbyCname(username);
        if(userEntityList.size() <= 0) {
            return -2;
        }
        Customer cust = userEntityList.get(0);
        if (!encoder.matches(password,cust.getPassword())) {
            return -1;
        }
        return 1;
    }


    @Override
    public void updateAdressInfo(String adress, Integer cId) {

        customerRepository.updateAdressInfo(adress,cId);

    }

    @Override
    public void removeCustomerById(Integer cId) {

        customerRepository.removeCustomerById(cId);


    }

    @Override
    public void updateNameInfo(String name, Integer cId) {

        customerRepository.updateNameInfo(name,cId);


    }

    @Override
    public void updateSurnameInfo(String surname, Integer cId) {

        customerRepository.updateSurnameInfo(surname,cId);

    }

    @Override
    public void updatePassword(String password, Integer cId) {

        customerRepository.updatePassword(password,cId);


    }

    @Override
    public List<Delivery> deliveryBycId(Integer cId) {

        List<Delivery> deliveries = customerRepository.deliveryBycId(cId);
        return deliveries;

    }


    @Override
    public Customer getCustomerInfo(Integer cId) {
        return customerRepository.getCustomerInfo(cId);
    }
}
