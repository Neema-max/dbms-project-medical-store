package com.example.demo.repository.admin;

import java.util.List;

import com.example.demo.model.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class adminOrderRepo {
    @Autowired
    private JdbcTemplate template;
    public List<order> getAllOrders(){
        String sql = "select * from orders";
        return  template.query(sql,new BeanPropertyRowMapper<>(order.class));
    }
    public void increaseOrderStatus(int orderId){
        String sql = "update orders set orderStatus = orderStatus +1 where orderId = ?;";
        template.update(sql, orderId);
    }
    public void cancelOrder(int orderId){
        String sql = "update orders set orderStatus = -1 where orderId = ?;";
        template.update(sql, orderId);
    }
}