package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.admin;
import com.example.demo.model.wishlist;
import com.example.demo.model.cart;
import com.example.demo.model.category;
import com.example.demo.model.customer;
import com.example.demo.model.employee;
import com.example.demo.model.itemImage;
import com.example.demo.model.items;
import com.example.demo.model.reviews;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class authRepo {
        // find____ByEmail
        // find____ByUsername
    @Autowired
    private JdbcTemplate template;
    // check customer by username 
    public customer findCustomer(String username){
        try{
            String  sql = "select * from customer where username  = ? ";
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(customer.class), new Object[] {username});
        }catch(EmptyResultDataAccessException  e){
            return null;
        }
    }
    public String getCatById(int id){
        try{
            String sql = "select * from  category where catId = ?";
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(category.class), new Object[] {id}).getCategory();
        }catch(Exception e){
            e.printStackTrace();
            return "error while loading";
        }
    }
    public List<items> getCatItems(int id){
        String sql = "select * from items where categoryId = ?";
        return template.query(sql,new BeanPropertyRowMapper<>(items.class),id);
    }
    // check employee by username
    public employee findEmployee(String username)            {
        try{
            String  sql = "select * from employee where username  = ? ";
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(employee.class), new Object[] {username});
        }catch(EmptyResultDataAccessException  e){
            return null;
        }
    }
    // check admin by ----,,-----
    public admin findAdmin(String username){
        try{
            String  sql = "select * from admin where username  = ? ";
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(admin.class), new Object[] {username});
        }catch(EmptyResultDataAccessException  e){
            return null;
        }
    }

    public category getCategoryById(int catId){
        String sql = "select * from category where catId=?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(category.class), new Object[] {catId});
    }



    public customer findCustomerByEmail(String email){
        try{
            String  sql = "select * from customer where email  = ? ";
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(customer.class), new Object[] {email});
        }catch(EmptyResultDataAccessException  e){
            return null;
        }
    }
    public employee findEmployeeByEmail(String email){
        try{
            String  sql = "select * from employee where email  = ? ";
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(employee.class), new Object[] {email});
        }catch(EmptyResultDataAccessException  e){
           // e.printStackTrace();
            return null;
        }
    }
    public admin findAdminByEmail(String email){
        try{
            String  sql = "select * from admin where email  = ? ";
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(admin.class), new Object[] {email});
        }catch(EmptyResultDataAccessException  e){
            return null;
        }
    }
    public customer findCustomerById(int id){
        try{
            String  sql = "select * from customer where customerId  = ? ";
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(customer.class), new Object[] {id});
        }catch(EmptyResultDataAccessException  e){
            return null;
        }
    }
    public employee findEmployeeById(int id){
        try{
            String  sql = "select * from employee where empId  = ? ";
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(employee.class), new Object[] {id});
        }catch(EmptyResultDataAccessException  e){
           // e.printStackTrace();
            return null;
        }
    }
    public admin findAdminById(int id){
        try{
            String  sql = "select * from admin where adminId  = ? ";
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(admin.class), new Object[] {id});
        }catch(EmptyResultDataAccessException  e){
            return null;
        }
    }
    public List<category> getAllCategories(){
        String sql = "select * from category";
        List<category> l= template.query(sql,new BeanPropertyRowMapper<>(category.class));
        return l;
    }
    public List<items> getSearchResult(String s){
        String sql = "select * from items where itemName like ? or itemDescription like ? ";
        s = "%" + s + "%";
        return template.query(sql,new BeanPropertyRowMapper<>(items.class),s,s);
    }
    public items getItem(int id){
        String sql = "select * from items where itemId = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(items.class), new Object[] {id});
    }
    public List<items> getAllItems(){
        String sql = "select * from items";
        return template.query(sql,new BeanPropertyRowMapper<>(items.class));
    }
    public Boolean existInCart(int cid , int iid){
        try{
            String sql = "select * from cart where customerId = ? and itemId = ?";
            cart c= template.queryForObject(sql, new BeanPropertyRowMapper<>(cart.class), new Object[] {cid,iid});
            c.getClass();
            return true;
        }catch(EmptyResultDataAccessException  e){
            return false;
        }
    }
    //existInWishlist
    public Boolean existInWishlist(int cid , int iid){
        try{
            String sql = "select * from wishlist where customerId = ? and itemId = ?";
            wishlist c= template.queryForObject(sql, new BeanPropertyRowMapper<>(wishlist.class), new Object[] {cid,iid});
            c.getClass();
            return true;
        }catch(EmptyResultDataAccessException  e){
            return false;
        }
    }
    public void addToCart(int cid , int iid,int quantity){
        String sql = "insert into cart values(?,?,?)";
        template.update(
            sql,
            cid,
            iid,
            quantity
        );
    }
    public void addToWishlist(int cid , int iid){
        String sql = "insert into wishlist values(?,?)";
        template.update(
            sql,
            cid,
            iid
        );
    }
    //removeFromCart
    public void removeFromCart(int cid , int iid){
        String sql = "delete from cart where customerId = ? and itemId = ?";
        template.update(
            sql,
            cid,
            iid
        );
    }
    public void removeFromWishlist(int cid , int iid){
        String sql = "delete from wishlist where customerId = ? and itemId = ?";
        template.update(
            sql,
            cid,
            iid
        );
    }
    // clearWishlist
    public void clearWishlist(int cid ){
        String sql = "delete from wishlist where customerId = ?";
        template.update(
            sql,
            cid
        );
    }
    public void clearCart(int cid ){
        String sql = "delete from cart where customerId = ?";
        template.update(
            sql,
            cid
        );
    }
    public List<itemImage> getItemImage(int itemId){
        String sql = "select * from itemImage where itemId = ?";
        return template.query(sql,new BeanPropertyRowMapper<>(itemImage.class),itemId);
    }
    public List<employee> getAllEmployee(){
        String sql = "select * from employee";
        return template.query(sql,new BeanPropertyRowMapper<>(employee.class));
    }
    public List<customer> getAllCustomer(){
        String sql = "select * from customer";
        return template.query(sql,new BeanPropertyRowMapper<>(customer.class));
    }
    public void remove(String table , int id , String column){
        String sql = "delete from "+table+" where "+column +"= ?";
        template.update(
            sql,
            id
        );
    }
    public List<reviews> getReviews(int id){
        String sql = "select * from reviews where itemId = ?";
        return template.query(sql,new BeanPropertyRowMapper<>(reviews.class),id);
    }
    public void addReview(reviews r){
        String sql = "insert into reviews values(?,?,?,?)";
        template.update(sql, r.getCustomerId(),r.getItemId(),r.getReviewDescription(),r.getRating());
    }
}