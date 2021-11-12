package com.example.demo.repository.admin;

import com.example.demo.model.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class adminEditProfileRepo {
    @Autowired
    private JdbcTemplate template;
    public void editProfile(admin c){
        String sql = "update admin set username= ? , name= ? , dob= ? ,age= ? ,email= ? ,address= ? ,phoneNo = ? where adminId = ?";
        template.update(
            sql,
            c.getUsername(),
            c.getName(),
            c.getDob(),
            c.getAge(),
            c.getEmail(),
            c.getAddress(),
            c.getPhoneNo(),
            c.getAdminId()
        );
    }
    public void updatePass(String s,int cid){
        String sql =  "update admin set password=? where adminId =?";
        template.update(sql, s,cid);
    }
}