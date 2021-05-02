package com.onlineStore.controller;


import com.onlineStore.controller.api.AdminControllerApi;
import com.onlineStore.model.Users;
import com.onlineStore.service.CategoryService;
import com.onlineStore.service.ProductManagerService;
import com.onlineStore.service.SalesManagerService;
import com.onlineStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "/admin")
public class AdminController implements AdminControllerApi {


    @Autowired
    SalesManagerService salesManagerService;

    @Autowired
    ProductManagerService productManagerService;

    @Autowired
    UserService userService;


    public ResponseEntity<String> registerSM(@RequestBody Users sm){
        Integer id = salesManagerService.registerSM(sm);
        if(id == -1) {
            return new ResponseEntity<String>("User " + sm.getUserName() + " already exists.", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<String>("SM " + sm.getUserName() + " registered successfully.", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Users>> getAllSM() {
        List<Users> test = salesManagerService.getAllSM();
        if (test == null) {
            return new ResponseEntity<>((List<Users>) null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

    public ResponseEntity<String> registerPM(@RequestBody Users pm){
        Integer id = productManagerService.registerPM(pm);
        if(id == -1) {
            return new ResponseEntity<String>("User " + pm.getUserName() + " already exists.", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<String>("PM " + pm.getUserName() + " registered successfully.", HttpStatus.OK);
    }



    @Override
    public ResponseEntity<List<Users>> getAllPM() {
        List<Users> test = productManagerService.getAllPM();
        if (test == null) {
            return new ResponseEntity<>((List<Users>) null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(test, HttpStatus.OK);
    }


    @Override
    public ResponseEntity deleteUser(Integer uId) {
        userService.deleteUser(uId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
