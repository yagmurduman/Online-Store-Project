package com.onlineStore.controller;


import com.onlineStore.controller.api.UserApi;
import com.onlineStore.model.Users;
import com.onlineStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value="/user")
public class UserController implements  UserApi {

    @Autowired
    private UserService userService;

    public ResponseEntity<String> get() {
        return new ResponseEntity<>("User", HttpStatus.OK);
    }

    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> test = userService.getAllUsers();
        if (test == null) {
            return new ResponseEntity<>((List<Users>) null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

    public ResponseEntity<List<Integer>> login(String userName, String password) {

        final List<Integer> response= userService.login(userName,password);
        if (response == null) {
            return new ResponseEntity<>((List<Integer>) null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    public ResponseEntity<String> registerAdmin(@RequestBody Users admin){
        Integer id = userService.registerAdmin(admin);
        if(id == -1) {
            return new ResponseEntity<String>("User " + admin.getUserName() + " already exists.", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<String>("Admin " + admin.getUserName() + " registered successfully.", HttpStatus.OK);
    }

}
