package com.example.demo.repository.customer;

import com.example.demo.model.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class customerEditProfileRepo {
    @Autowired
    private JdbcTemplate template;
    public void editProfile(customer c){
        String sql = "update customer set username= ? , name= ? , dob= ? ,age= ? ,email= ? ,address= ? ,phoneNo = ? where customerId = ?";
        template.update(
            sql,
            c.getUsername(),
            c.getName(),
            c.getDob(),
            c.getAge(),
            c.getEmail(),
            c.getAddress(),
            c.getPhoneNo(),
            c.getCustomerId()
        );
    }
    public void updatePass(String s,int cid){
        String sql =  "update customer set password=? where customerId =?";
        template.update(sql, s,cid);
    }
}