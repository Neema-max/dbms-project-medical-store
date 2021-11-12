package com.example.demo.repository.customer;

import com.example.demo.model.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class customerRegisterRepo {
    @Autowired
    private JdbcTemplate template;
    public int getLastCustomerID(){
        String sql = "select max(customerId) from customer"; 
        int x  =0 ;
        try{
            x =  (int)template.queryForList(sql).get(0).get("max(customerId)");
        }catch(Exception e){
            return x;
        }
        return x;
    }
    public void addCustomer(customer c1){
         String  sql = "insert into customer values(?,?,?,?,?,?,?,?,?,?)";
        template.update(
            sql,
            c1.getCustomerId(),
            c1.getName(),
            c1.getAddress(),
            c1.getEmail(),
            c1.getUsername(),
            c1.getPassword(),
            c1.getPhoneNo(),
            c1.getDob(),
            c1.getAge(),
            c1.getImage()
        );
    }
}