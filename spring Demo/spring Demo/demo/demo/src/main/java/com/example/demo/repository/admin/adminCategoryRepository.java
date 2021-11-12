package com.example.demo.repository.admin;

import com.example.demo.model.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class adminCategoryRepository {
    @Autowired
    private JdbcTemplate template;
    public void addCategory(category c){
        String  sql = "insert into category values(?,?,?)";
        template.update(
            sql,
            c.getCatId(),
            c.getCategory(),
            c.getImage()
        );
    }
    public int getLastCategoryID(){
        String sql = "select max(catId) from category"; 
        int x = 0;
        try{
            x =  (int)template.queryForList(sql).get(0).get("max(catId)");
        }catch(Exception e){
            return x;
        }
        return x;
    }
    public void updateCat(int catId , String cat){
        String sql = "update category set category = ? where catId = ?";
        template.update(
            sql,
            cat,
            catId
        );
    }
}