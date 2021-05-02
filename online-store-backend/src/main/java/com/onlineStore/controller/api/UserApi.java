package com.onlineStore.controller.api;

import com.onlineStore.model.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserApi {

    @PostMapping(value="/user/login")
    ResponseEntity<List<Integer>> login(String userName, String password);

    @GetMapping(value="/user/get-all-users")
    ResponseEntity<List<Users>> getAllUsers();

    @PostMapping(value="/user/registerAdmin")
    ResponseEntity<String> registerAdmin(@RequestBody Users admin);

}
