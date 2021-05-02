package com.onlineStore.service.Imp;

import com.onlineStore.model.Product;
import com.onlineStore.model.Users;
import com.onlineStore.repository.ProductManagerRepository;
import com.onlineStore.repository.UsersRepository;
import com.onlineStore.service.ProductManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductManagerImp implements ProductManagerService {

    @Autowired
    ProductManagerRepository productManagerRepository;

    @Autowired
    UsersRepository usersRepository;

    @Override
    public void addProduct(Product product) {
        productManagerRepository.addProduct(product);
    }

    @Override
    public void removeProductById(Integer pId) {
        productManagerRepository.removeProductById(pId);
    }

    @Override
    public void updateProduct(Product product, Integer pId) {
        productManagerRepository.updateProduct(product, pId);
    }
//
//    @Override
//    public void updateStockofProduct(Integer pId, Integer stock) {
//        productManagerRepository.updateStockofProduct(pId, stock);
//    }

    @Override
    public void updateIsApprovedOfReview(Integer cId, Integer pId, Boolean approval) {
        productManagerRepository.updateIsApprovedOfReview(cId, pId, approval);
    }

    public Integer registerPM(Users pm) {

        List<Users> list = usersRepository.findByUsername(pm.getUserName());

        if (list.size() > 0) {
            return -1;
        }

        productManagerRepository.insertPM(pm); ;
        return 1;

    }

    @Override
    public List<Users> getAllPM() {
        return productManagerRepository.getAllPM();
    }
}
