package com.example.demo.repository.admin;

import com.example.demo.model.items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class adminItemRepository {
    @Autowired
    private JdbcTemplate template;
    public int getLastItemID(){
        String sql = "select max(itemId) from items"; 
        Integer x =  (int)template.queryForList(sql).get(0).get("max(itemId)");
        return x;
    }
    public int lastItemImage(){
        String sql = "select max(image) from itemImage"; 
        String x = template.queryForList(sql).get(0).get("max(image)").toString();
        int k = x.lastIndexOf('.');
        int u =  Integer.parseInt(x.substring(0,k));
        return u;
    }
    public void addItem(items i){
        String  sql = "insert into items values(?,?,?,?,?,?,?)";
        template.update(
            sql,
            i.getItemId(),
            i.getItemDescription(),
            i.getItemName(),
            i.getSellingPrice(),
            i.getBuyingPrice(),
            i.getCategoryId(),
            i.getQuantity()
        );
    }
    public void updateItem(items i){
        String sql = "update items set itemDescription =? ,itemName=? , sellingPrice=? , buyingPrice=? , categoryId=?,quantity=? where itemId = ?";
        template.update(
            sql,
            i.getItemDescription(),
            i.getItemName(),
            i.getSellingPrice(),
            i.getBuyingPrice(),
            i.getCategoryId(),
            i.getQuantity(),
            i.getItemId()
        );
    }
}