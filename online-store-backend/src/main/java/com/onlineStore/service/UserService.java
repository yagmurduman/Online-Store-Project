package com.onlineStore.service;

import com.onlineStore.model.Users;

import java.util.List;

public interface UserService{

    List<Users> getAllUsers();

    List<Integer> login (String userName, String password);

    void deleteUser(Integer uId);

    Integer registerAdmin(Users admin);

}
