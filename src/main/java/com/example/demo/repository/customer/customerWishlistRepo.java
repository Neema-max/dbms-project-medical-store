package com.example.demo.repository.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class customerWishlistRepo {
    @Autowired
    private JdbcTemplate template;
    public List<Integer> getwishlistItems(int id){
        String sql = "select itemId from wishlist where customerId = ?";
        return  template.queryForList(sql ,Integer.class,  id);
    }
}