package com.onlineStore.repository;

import com.onlineStore.model.Customer;
import com.onlineStore.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class UsersRepository  extends JdbcDaoSupport {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initiliaze(){
        setDataSource(dataSource);
    }


    public List<Users> getAllUsers() {
        String sql = "SELECT * FROM users";
        List<Map<String,Object>> rows = getJdbcTemplate().queryForList(sql);

        List<Users> result = new ArrayList<Users>();
        for(Map<String, Object> row:rows){
            Users user = new Users();
            user.setuId((Integer)row.get("uId"));
            user.setUserName((String)row.get("userName"));
            user.setPassword((String)row.get("password"));

            result.add(user);
        }
        return result;
    }
    public List<Users> findByUsername(String userName) {

        String sql = "SELECT * FROM users WHERE userName = '" +  ((userName == null) ? "" : userName)+"'";

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        List<Users> users = new ArrayList<Users>();

        for(Map<String, Object> row:rows){

            Users user =  new Users();
            user.setuId((Integer)row.get("uId"));
            user.setType((Integer)row.get("type"));
            user.setUserName((String)row.get("userName"));
            user.setPassword((String) row.get("password"));
            users.add(user);

        }
        return users;
    }

    public void insertCus(Users user){

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String sql = "INSERT INTO users " + "(uId, type, userName,password ) VALUES (?, ?, ?, ?)";
        getJdbcTemplate().update(sql, new Object[] {user.getuId(),user.getType(), user.getUserName(),encoder.encode(user.getPassword())});

    }


    public void deleteUser(Integer uId){
        String sql = "DELETE FROM users WHERE uId = ?";
        getJdbcTemplate().update(sql, uId);
    }


    public void insertAdmin(Users admin){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String sql = "INSERT INTO users " + "(uId, type, userName, password) VALUES (?, ?, ?, ?)";

        getJdbcTemplate().update(sql, new Object[] {admin.getuId(), 4, admin.getUserName(), encoder.encode(admin.getPassword())});
    }
}
