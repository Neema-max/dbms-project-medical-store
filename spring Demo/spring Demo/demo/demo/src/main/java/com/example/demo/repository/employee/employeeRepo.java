package com.example.demo.repository.employee;

import com.example.demo.model.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class employeeRepo {
    @Autowired
    private JdbcTemplate template;
    public void editProfile(employee c){
        String sql = "update employee set username= ? , name= ? , dob= ? ,age= ? ,email= ? ,address= ? ,phoneNo = ? where empId = ?";
        template.update(
            sql,
            c.getUsername(),
            c.getName(),
            c.getDob(),
            c.getAge(),
            c.getEmail(),
            c.getAddress(),
            c.getPhoneNo(),
            c.getEmpId()
        );
    }
    public void updatePass(String s,int cid){
        String sql =  "update employee set password=? where empId =?";
        template.update(sql, s,cid);
    }
}