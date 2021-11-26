package com.example.demo.repository.admin;

import com.example.demo.model.itemImage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class itemImageRepo {
    @Autowired
    private JdbcTemplate template;
    public void save(itemImage i){
        String  sql = "insert into itemimage values(?,?)";
        template.update(
            sql,
            i.getItemId(),
            i.getImage()
        );
    }
}