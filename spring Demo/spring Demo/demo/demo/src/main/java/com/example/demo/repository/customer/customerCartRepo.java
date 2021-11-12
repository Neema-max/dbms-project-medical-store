package com.example.demo.repository.customer;

import java.util.List;

import com.example.demo.model.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class customerCartRepo {
    @Autowired
    private JdbcTemplate template;
    public List<cart> getCartItems(int id){
        String sql = "select * from cart where customerId = ?";
        return  template.query(sql,new BeanPropertyRowMapper<>(cart.class),id);
    }
    public void updateCart(int itemId , int quantity ,int customerId){
        String sql = "update cart set units = ? where itemId = ? and customerId = ?";
        template.update(sql, quantity , itemId , customerId);
    }
}