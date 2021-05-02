package com.onlineStore.service.Imp;

import com.onlineStore.model.Users;
import com.onlineStore.repository.UsersRepository;
import com.onlineStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UsersRepository usersRepository;

    public UserServiceImp(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;

    }

    @Override
    public List<Users> getAllUsers() {
        return usersRepository.getAllUsers();
    }


    @Override
    public List<Integer> login(String userName, String password) {

        List<Users> userEntityList = usersRepository.findByUsername(userName);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        List<Integer> response = new ArrayList<>();

        // if user exists
        if(userEntityList.size() > 0){
            Users user = userEntityList.get(0);
            // if user password is wrong
            if (!encoder.matches(password,user.getPassword())) {
                response.add(-1);
                response.add(-1);
                return response;
            }
            else {
                response.add(user.getType());
                response.add(user.getuId());
                return response;
            }
        }

        // if user not exist
        response.add(-2);
        response.add(-2);
        return response ;

    }

    @Override
    public void deleteUser(Integer uId) {
        usersRepository.deleteUser(uId);
    }

    @Override
    public Integer registerAdmin(Users admin) {
        List<Users> list = usersRepository.findByUsername(admin.getUserName());

        if (list.size() > 0) {
            return -1;
        }

        usersRepository.insertAdmin(admin); ;
        return 1;
    }
}
