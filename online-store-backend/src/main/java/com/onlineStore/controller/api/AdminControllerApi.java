package com.onlineStore.controller.api;

import com.onlineStore.model.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface AdminControllerApi {


    @PostMapping(value="/admin/registerSM")
    ResponseEntity<String> registerSM(@RequestBody Users sm);

    @PostMapping(value="/admin/registerPM")
    ResponseEntity<String> registerPM(@RequestBody Users pm);

    @GetMapping(value = "/admin/get-all-sm")
    ResponseEntity<List<Users>> getAllSM();

    @GetMapping(value = "/admin/get-all-pm")
    ResponseEntity<List<Users>> getAllPM();

    @DeleteMapping(value="/admin/deleteUser")
    ResponseEntity deleteUser(Integer uId);
}
